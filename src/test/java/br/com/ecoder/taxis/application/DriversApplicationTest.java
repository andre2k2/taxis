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
import org.mockito.invocation.InvocationOnMock;
import org.mockito.runners.MockitoJUnitRunner;
import org.mockito.stubbing.Answer;

import br.com.ecoder.taxis.model.Driver;
import br.com.ecoder.taxis.repository.DriversRepository;

@RunWith(MockitoJUnitRunner.class)
@SuppressWarnings("unchecked")
public class DriversApplicationTest {

    @InjectMocks
    DriversApplication application;

    @Mock
    BestChoiceApplication bestChoiceApplication;

    @Mock
    DriversRepository repository;

    @Before
    public void initialize() {

        List<Driver> drivers =
                Arrays.asList(new Driver(1L, "Ze", "ZZZ-8080", 15.0, 15.0, true), new Driver(2L, "Joao", "AAA-7070", 10.0, 10.0, true), new Driver(3L, "Antonio", "BBB-6060", 5.0, 5.0, true));

        Mockito.when(repository.save(Mockito.any(Driver.class))).then(new Answer<Driver>() {

            @Override
            public Driver answer(InvocationOnMock invocation) throws Throwable {
                return (Driver) invocation.getArguments()[0];
            }
        });

        Mockito.when(repository.findOne(new Long(1L))).thenReturn(new Driver(1L, "Ze", "ZZZ-8080", 15.0, 15.0, true));

        Mockito.when(repository.findInArea(new Double(0.0), new Double(0.0), new Double(30.0), new Double(30.0))).thenReturn(drivers);

        Mockito.when(bestChoiceApplication.sortBestDrivers(new Double(15.0), new Double(15.0), drivers)).then(new Answer<List<Driver>>() {

            @Override
            public List<Driver> answer(InvocationOnMock invocation) throws Throwable {
                return (List<Driver>) invocation.getArguments()[2];
            }
        });
    }

    @Test
    public void shouldCallRepositorySave() {

        Driver driver = new Driver(1L, "Ze", "ZZZ-8080", 15.0, 15.0, true);
        Driver response = application.createOrUpdate(driver);

        Assert.assertNotNull(response);
        Assert.assertEquals(driver, response);
        Mockito.verify(repository, Mockito.times(1)).save(driver);
    }

    @Test
    public void shouldCallRepositoryFindOne() {

        Driver response = application.findById(1L);

        Assert.assertNotNull(response);
        Assert.assertEquals(new Long(1L), response.getId());
        Assert.assertEquals(new Double(15.0), response.getLatitude());
        Assert.assertEquals(new Double(15.0), response.getLongitude());
        Assert.assertEquals(new Boolean(true), response.getDriverAvailable());
        Mockito.verify(repository, Mockito.times(1)).findOne(1L);
    }


    @Test
    public void shouldCallRepositoryFindInAreaAndBestChoiceApplicationSortBestDrivers() {

        List<Driver> drivers = application.findInArea(new Area("0.0,0.0", "30.0,30.0"));

        Assert.assertNotNull(drivers);
        Assert.assertEquals(3, drivers.size());
        Assert.assertEquals(new Long(1L), drivers.get(0).getId());
        Assert.assertEquals(new Long(2L), drivers.get(1).getId());
        Assert.assertEquals(new Long(3L), drivers.get(2).getId());

        Mockito.verify(repository, Mockito.times(1)).findInArea(new Double(0.0), new Double(0.0), new Double(30.0), new Double(30.0));
        Mockito.verify(bestChoiceApplication, Mockito.times(1)).sortBestDrivers(new Double(15.0), new Double(15.0), drivers);
    }

}
