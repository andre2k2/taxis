package br.com.ecoder.taxis.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.ecoder.taxis.exception.InternalServerErrorException;

import com.google.maps.DistanceMatrixApi;
import com.google.maps.GeoApiContext;
import com.google.maps.model.DistanceMatrix;
import com.google.maps.model.DistanceMatrixElement;

@Service
public class GoogleMapsRepository {

    @Autowired
    GeoApiContext context;

    public DistanceMatrixElement distance(Double originLat, Double originLng, Double destinationLat, Double destinationLng) {

        try {

            String[] origins = new String[] { originLat + "," + originLng };
            String[] destinations = new String[] { destinationLat + "," + destinationLng };

            DistanceMatrix distanceMatrix = DistanceMatrixApi.getDistanceMatrix(context, origins, destinations).await();
            DistanceMatrixElement distance = distanceMatrix.rows[0].elements[0];

            return distance;

        } catch (Exception ex) {
            throw new InternalServerErrorException("Erro ao consultar servico google maps.", ex);
        }
    }
}
