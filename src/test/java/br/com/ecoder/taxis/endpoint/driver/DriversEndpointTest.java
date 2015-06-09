package br.com.ecoder.taxis.endpoint.driver;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import br.com.ecoder.taxis.config.GoogleMaps;
import br.com.ecoder.taxis.config.MvcConfiguration;
import br.com.ecoder.taxis.config.Persistence;
import br.com.ecoder.taxis.model.Driver;
import br.com.ecoder.taxis.repository.DriversRepository;

@ContextConfiguration(classes = { MvcConfiguration.class, Persistence.class, GoogleMaps.class })
@WebAppConfiguration
@RunWith(SpringJUnit4ClassRunner.class)
public class DriversEndpointTest {

    @Autowired
    DriversEndpoint endpoint;

    @Autowired
    DriversRepository repository;

    @Test
    public void shouldCreateDriver() {

        DriverCreateRequest request = new DriverCreateRequest("ze", "FFF-0101");
        ResponseEntity<DriverCreateResponse> response = endpoint.createOrUpdate(request);

        Assert.assertNotNull(response);

        Driver driver = repository.findOne(response.getBody().getDriverId());

        Assert.assertNotNull(driver);
        Assert.assertEquals("ze", driver.getName());
        Assert.assertEquals("FFF-0101", driver.getCarPlate());

        repository.delete(driver);
    }

    @Test
    public void shouldGetDriverStatus() {

        Driver driver = repository.save(new Driver(null, "joao", "DDD-0202", -23.123, -43.123, true));

        ResponseEntity<DriverStatusResponse> response = endpoint.getStatus(driver.getId());

        Assert.assertNotNull(response);
        Assert.assertEquals(driver.getId(), response.getBody().getDriverId());
        Assert.assertEquals(new Double(-23.123), response.getBody().getLatitude());
        Assert.assertEquals(new Double(-43.123), response.getBody().getLongitude());
        Assert.assertEquals(new Boolean(true), response.getBody().getDriverAvailable());

        repository.delete(driver);
    }

    @Test
    public void shouldUpdateDriverStatus() {

        Driver driver = repository.save(new Driver(null, "antonio", "BBB-0303", -10.0, -10.0, false));
        Long id = driver.getId();

        DriverStatusRequest request = new DriverStatusRequest(driver.getId(), -23.123, -43.123, true);
        ResponseEntity<?> response = endpoint.updateStatus(id, request);

        driver = repository.findOne(id);

        Assert.assertNotNull(response);
        Assert.assertEquals(id, driver.getId());
        Assert.assertEquals(new Double(-23.123), driver.getLatitude());
        Assert.assertEquals(new Double(-43.123), driver.getLongitude());
        Assert.assertEquals(new Boolean(true), driver.getDriverAvailable());

        repository.delete(driver);
    }

    @Test
    public void shouldFindDriverInArea() {

        Driver driver = repository.save(new Driver(null, "euclides", "AAA-0303", 10.0, 10.0, true));

        ResponseEntity<List<DriverStatusResponse>> response = endpoint.findInArea("1.0,1.0", "50.0,50.0");

        Assert.assertNotNull(response);
        Assert.assertNotNull(response.getBody());
        Assert.assertEquals(1, response.getBody().size());
        Assert.assertEquals(driver.getId(), response.getBody().get(0).getDriverId());

        repository.delete(driver);
    }

    @Test
    public void shouldNotFindDriverInArea() {

        Driver driver = repository.save(new Driver(null, "aritoteles", "GGG-0404", 60.0, 60.0, true));

        ResponseEntity<List<DriverStatusResponse>> response = endpoint.findInArea("1.0,1.0", "50.0,50.0");

        Assert.assertNotNull(response);
        Assert.assertNotNull(response.getBody());
        Assert.assertEquals(0, response.getBody().size());

        repository.delete(driver);
    }

    @Test
    public void shouldNotFindUnavailableDriverInArea() {

        Driver driver = repository.save(new Driver(null, "marquito", "HHH-0505", 10.0, 10.0, false));

        ResponseEntity<List<DriverStatusResponse>> response = endpoint.findInArea("1.0,1.0", "50.0,50.0");

        Assert.assertNotNull(response);
        Assert.assertNotNull(response.getBody());
        Assert.assertEquals(0, response.getBody().size());

        repository.delete(driver);
    }

    @Test
    public void shouldSortCloserDriverFirst() {

        Driver driver1 = repository.save(new Driver(null, "jesus", "JJJ-0606", 26.0, 26.0, true));
        Driver driver2 = repository.save(new Driver(null, "joana", "KKK-0707", 1.0, 1.0, true));
        Driver driver3 = repository.save(new Driver(null, "pericles", "LLL-0808", 10.0, 10.0, true));
        Driver driverUnavailable = repository.save(new Driver(null, "renato", "MMM-0909", 5.0, 5.0, false));

        ResponseEntity<List<DriverStatusResponse>> response = endpoint.findInArea("1.0,1.0", "50.0,50.0");

        Assert.assertNotNull(response);
        Assert.assertNotNull(response.getBody());
        Assert.assertEquals(3, response.getBody().size());
        Assert.assertEquals(driver1.getId(), response.getBody().get(0).getDriverId());
        Assert.assertEquals(driver3.getId(), response.getBody().get(1).getDriverId());
        Assert.assertEquals(driver2.getId(), response.getBody().get(2).getDriverId());

        repository.delete(driver1);
        repository.delete(driver2);
        repository.delete(driver3);
        repository.delete(driverUnavailable);
    }

}
