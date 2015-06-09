package br.com.ecoder.taxis.repository;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import br.com.ecoder.taxis.config.GoogleMaps;
import br.com.ecoder.taxis.config.MvcConfiguration;
import br.com.ecoder.taxis.config.Persistence;

import com.google.maps.model.DistanceMatrixElement;

@ContextConfiguration(classes = { MvcConfiguration.class, Persistence.class, GoogleMaps.class })
@WebAppConfiguration
@RunWith(SpringJUnit4ClassRunner.class)
public class GoogleMapsRepositoryTest {

    @Autowired
    GoogleMapsRepository repository;

    @Test
    public void shouldCallGoogleMapsDistanceMatrixAPI() {

        DistanceMatrixElement element = repository.distance(-23.5711562, -46.6932905, -23.5544185, -46.6814607);

        Assert.assertNotNull(element);
    }

}
