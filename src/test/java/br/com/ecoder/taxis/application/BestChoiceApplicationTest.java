package br.com.ecoder.taxis.application;

import java.util.Arrays;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import br.com.ecoder.taxis.exception.InternalServerErrorException;
import br.com.ecoder.taxis.model.Driver;
import br.com.ecoder.taxis.repository.GoogleMapsRepository;

import com.google.maps.model.DistanceMatrixElement;
import com.google.maps.model.Duration;

@RunWith(MockitoJUnitRunner.class)
@SuppressWarnings("unchecked")
public class BestChoiceApplicationTest {

    @InjectMocks
    BestChoiceApplication application;

    @Mock
    GoogleMapsRepository googlemaps;

    List<Driver> drivers;

    @Before
    public void initialize() {

        drivers = Arrays.asList(new Driver(1L, "Ze", "ZZZ-8080", 15.0, 15.0, true), new Driver(2L, "Joao", "AAA-7070", 10.0, 10.0, true), new Driver(3L, "Antonio", "BBB-6060", 5.0, 5.0, true));

        DistanceMatrixElement melement1 = new DistanceMatrixElement();
        melement1.duration = new Duration();
        melement1.duration.inSeconds = 60;

        Mockito.when(googlemaps.distance(new Double(15.0), new Double(15.0), new Double(11.0), new Double(11.0))).thenReturn(melement1);

        DistanceMatrixElement melement2 = new DistanceMatrixElement();
        melement2.duration = new Duration();
        melement2.duration.inSeconds = 30;

        Mockito.when(googlemaps.distance(new Double(10.0), new Double(10.0), new Double(11.0), new Double(11.0))).thenReturn(melement2);

        DistanceMatrixElement melement3 = new DistanceMatrixElement();
        melement3.duration = new Duration();
        melement3.duration.inSeconds = 10;

        Mockito.when(googlemaps.distance(new Double(5.0), new Double(5.0), new Double(11.0), new Double(11.0))).thenReturn(melement3);
    }

    @Test
    public void shouldSortDriversByArriveTime() {

        List<Driver> result = application.sortBestDrivers(11.0, 11.0, drivers);

        Assert.assertNotNull(result);
        Assert.assertEquals(new Long(3L), result.get(0).getId());
        Assert.assertEquals(new Long(2L), result.get(1).getId());
        Assert.assertEquals(new Long(1L), result.get(2).getId());
    }

    @Test
    public void shouldSortDriversByDistance() {

        Mockito.when(googlemaps.distance(Mockito.anyDouble(), Mockito.anyDouble(), Mockito.anyDouble(), Mockito.anyDouble())).thenThrow(InternalServerErrorException.class);

        List<Driver> result = application.sortBestDrivers(11.0, 11.0, drivers);

        Assert.assertNotNull(result);
        Assert.assertEquals(new Long(2L), result.get(0).getId());
        Assert.assertEquals(new Long(1L), result.get(1).getId());
        Assert.assertEquals(new Long(3L), result.get(2).getId());
    }

}
