package br.com.ecoder.taxis.application;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.ecoder.taxis.model.Driver;
import br.com.ecoder.taxis.repository.DriversRepository;

@Service
public class DriversApplication {

    @Autowired
    BestChoiceApplication bestChoiceApplication;

    @Autowired
    DriversRepository repository;

    public Driver createOrUpdate(Driver driver) {

        driver = repository.save(driver);
        return driver;
    }

    public Driver findById(Long driverId) {

        Driver driver = repository.findOne(driverId);
        return driver;
    }

    public List<Driver> findInArea(Area area) {

        List<Driver> drivers = repository.findInArea(area.getLat1(), area.getLng1(), area.getLat2(), area.getLng2());
        drivers = bestChoiceApplication.sortBestDrivers(area.getCenterLat(), area.getCenterLng(), drivers);

        return drivers;
    }

}
