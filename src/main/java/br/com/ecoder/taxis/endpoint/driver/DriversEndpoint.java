package br.com.ecoder.taxis.endpoint.driver;

import static br.com.ecoder.taxis.endpoint.driver.DriverAdapter.adaptCreateRequestToEntity;
import static br.com.ecoder.taxis.endpoint.driver.DriverAdapter.adaptEntityToCreateResponse;
import static br.com.ecoder.taxis.endpoint.driver.DriverAdapter.adaptEntityToStatusResponse;
import static br.com.ecoder.taxis.endpoint.driver.DriverAdapter.adaptStatusRequestToEntity;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.ecoder.taxis.application.Area;
import br.com.ecoder.taxis.application.DriversApplication;
import br.com.ecoder.taxis.model.Driver;

@RestController
@RequestMapping("/drivers")
@SuppressWarnings("rawtypes")
public class DriversEndpoint {

    @Autowired
    DriversApplication application;

    // Cria ou atualiza taxista
    @RequestMapping(value = "", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<DriverCreateResponse> createOrUpdate(@Validated @RequestBody DriverCreateRequest driverRequest) {

        Driver driver = adaptCreateRequestToEntity(driverRequest);
        driver = application.createOrUpdate(driver);
        DriverCreateResponse result = adaptEntityToCreateResponse(driver);

        return new ResponseEntity<DriverCreateResponse>(result, HttpStatus.CREATED);
    }

    // Retorna o estado de um taxista.
    @RequestMapping(value = "/{driverId}/status", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<DriverStatusResponse> getStatus(@PathVariable("driverId") Long driverId) {

        Driver driver = application.findById(driverId);
        DriverStatusResponse result = adaptEntityToStatusResponse(driver);

        return new ResponseEntity<DriverStatusResponse>(result, HttpStatus.OK);
    }

    // Grava a posição atual e estado de um taxista.
    @RequestMapping(value = "/{driverId}/status", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> updateStatus(@PathVariable("driverId") Long driverId, @Validated @RequestBody DriverStatusRequest driverRequest) {

        driverRequest.setDriverId(driverId);

        Driver driver = adaptStatusRequestToEntity(driverRequest);
        application.createOrUpdate(driver);

        return new ResponseEntity(HttpStatus.OK);
    }

    // Lista os taxistas ativos dentro de uma retângulo geográfico.
    @RequestMapping(value = "/inArea", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<DriverStatusResponse>> findInArea(@RequestParam(value = "sw", required = true) String sw, @RequestParam(value = "ne", required = true) String ne) {

        List<Driver> drivers = application.findInArea(new Area(sw, ne));
        List<DriverStatusResponse> result = adaptEntityToStatusResponse(drivers);

        return new ResponseEntity<List<DriverStatusResponse>>(result, HttpStatus.OK);
    }

}
