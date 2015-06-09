package br.com.ecoder.taxis.application;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.ecoder.taxis.model.Driver;
import br.com.ecoder.taxis.repository.GoogleMapsRepository;

import com.google.maps.model.DistanceMatrixElement;

@Service
public class BestChoiceApplication {

    @Autowired
    GoogleMapsRepository googlemaps;

    public List<Driver> sortBestDrivers(Double lat, Double lng, List<Driver> drivers) {

        // cria lista com a heuristica para escolha do melhor motorista
        // leva em consideração o menor tempo de chegada ou a menor distancia percorrida
        LinkedList<BestChoiceHeuristic> heuristics = createHeuristicList(lat, lng, drivers);

        // ordena a lista de heuristicas
        // a menor heuritica primeiro (menor tempo ou menor distancia)
        Collections.sort(heuristics);

        // cria uma lista de motoristas ordenada conforme a heuristica de cada um
        LinkedList<Driver> result = createDriversList(heuristics);

        return result;
    }

    private LinkedList<BestChoiceHeuristic> createHeuristicList(Double lat, Double lng, List<Driver> drivers) {

        LinkedList<BestChoiceHeuristic> heuristics = new LinkedList<BestChoiceHeuristic>();
        for (Driver driver : drivers) {

            long heuristic = getHeuristic(lat, lng, driver);
            heuristics.add(new BestChoiceHeuristic(driver, heuristic));
        }

        return heuristics;
    }

    private long getHeuristic(Double lat, Double lng, Driver driver) {

        long heuristic = 0L;
        try {

            // Tenta calcular tempo de chegada usando API do google
            heuristic = getArriveTime(lat, lng, driver);

            if (heuristic == 0L) {
                heuristic = getDistance(lat, lng, driver);
            }

        } catch (Exception ex) {

            // Calcula distancia entre o centro e o driver
            heuristic = getDistance(lat, lng, driver);
        }

        return heuristic;
    }

    private long getArriveTime(Double lat, Double lng, Driver driver) {

        DistanceMatrixElement distance = googlemaps.distance(driver.getLatitude(), driver.getLongitude(), lat, lng);

        if (distance != null && distance.duration != null) {
            return distance.duration.inSeconds;
        } else {
            return 0L;
        }
    }

    private long getDistance(Double lat, Double lng, Driver driver) {

        long heuristic = new Double(Math.hypot(driver.getLatitude() - lat, driver.getLongitude() - lng)).longValue();
        return heuristic;
    }

    private LinkedList<Driver> createDriversList(LinkedList<BestChoiceHeuristic> heuristics) {

        LinkedList<Driver> drivers = new LinkedList<Driver>();
        for (BestChoiceHeuristic heuristic : heuristics) {
            drivers.add(heuristic.driver);
        }

        return drivers;
    }

}
