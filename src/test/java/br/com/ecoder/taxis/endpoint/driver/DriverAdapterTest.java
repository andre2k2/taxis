package br.com.ecoder.taxis.endpoint.driver;

import java.util.Arrays;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import br.com.ecoder.taxis.model.Driver;

@RunWith(MockitoJUnitRunner.class)
public class DriverAdapterTest {

    @Test
    public void shouldAdaptEntityToCreateResponse() {

        Driver driver = new Driver(1L, "Ze", "ZZZ-8080", 15.0, 15.0, true);

        DriverCreateResponse response = DriverAdapter.adaptEntityToCreateResponse(driver);

        Assert.assertNotNull(response);
        Assert.assertEquals(new Long(1L), response.getDriverId());
    }

    @Test
    public void shouldAdaptEntityToStatusResponse() {

        Driver driver = new Driver(1L, "Ze", "ZZZ-8080", 15.0, 15.0, true);

        DriverStatusResponse response = DriverAdapter.adaptEntityToStatusResponse(driver);

        Assert.assertNotNull(response);
        Assert.assertEquals(new Long(1L), response.getDriverId());
        Assert.assertEquals(new Double(15.0), response.getLatitude());
        Assert.assertEquals(new Double(15.0), response.getLongitude());
        Assert.assertEquals(new Boolean(true), response.getDriverAvailable());
    }

    @Test
    public void shouldAdaptEntitiesToStatusResponses() {

        Driver driver = new Driver(1L, "Ze", "ZZZ-8080", 15.0, 15.0, true);

        List<DriverStatusResponse> response = DriverAdapter.adaptEntityToStatusResponse(Arrays.asList(driver));

        Assert.assertNotNull(response);
        Assert.assertEquals(new Long(1L), response.get(0).getDriverId());
        Assert.assertEquals(new Double(15.0), response.get(0).getLatitude());
        Assert.assertEquals(new Double(15.0), response.get(0).getLongitude());
        Assert.assertEquals(new Boolean(true), response.get(0).getDriverAvailable());
    }

    @Test
    public void shouldNotAdaptNullEntitiesToStatusResponses() {

        List<DriverStatusResponse> response = DriverAdapter.adaptEntityToStatusResponse((List<Driver>) null);

        Assert.assertNull(response);
    }

    @Test
    public void shouldAdaptCreateRequestToEntity() {

        DriverCreateRequest request = new DriverCreateRequest("Joao", "DDD-7878");

        Driver driver = DriverAdapter.adaptCreateRequestToEntity(request);

        Assert.assertNotNull(driver);
        Assert.assertEquals("Joao", driver.getName());
        Assert.assertEquals("DDD-7878", driver.getCarPlate());
    }

    @Test
    public void shouldAdaptStatusRequestToEntity() {

        DriverStatusRequest request = new DriverStatusRequest(1L, -23.123, -25.543, false);

        Driver driver = DriverAdapter.adaptStatusRequestToEntity(request);

        Assert.assertNotNull(driver);
        Assert.assertEquals(new Long(1L), driver.getId());
        Assert.assertEquals(new Double(-23.123), driver.getLatitude());
        Assert.assertEquals(new Double(-25.543), driver.getLongitude());
        Assert.assertEquals(new Boolean(false), driver.getDriverAvailable());
    }

}
