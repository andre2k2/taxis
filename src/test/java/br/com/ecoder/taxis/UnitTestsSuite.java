package br.com.ecoder.taxis;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import br.com.ecoder.taxis.application.AreaTest;
import br.com.ecoder.taxis.application.BestChoiceApplicationTest;
import br.com.ecoder.taxis.application.DriversApplicationTest;
import br.com.ecoder.taxis.endpoint.driver.DriverAdapterTest;
import br.com.ecoder.taxis.endpoint.driver.DriversEndpointTest;
import br.com.ecoder.taxis.interceptor.AuthenticationInterceptorTest;
import br.com.ecoder.taxis.repository.GoogleMapsRepositoryTest;

//@formatter:off
@RunWith(Suite.class)
@SuiteClasses({
    AreaTest.class,
    BestChoiceApplicationTest.class,
    DriversApplicationTest.class,
    DriverAdapterTest.class,
    DriversEndpointTest.class,
    AuthenticationInterceptorTest.class,
    GoogleMapsRepositoryTest.class,
})
public class UnitTestsSuite {

}
