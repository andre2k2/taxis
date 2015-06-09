package br.com.ecoder.taxis.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.google.maps.GeoApiContext;

@Configuration
public class GoogleMaps {

    @Bean
    public GeoApiContext context() {
        return new GeoApiContext().setApiKey("AIzaSyCkpHAKXT9_Nc4WYy55lvzYIj7dqWPm21U");
    }

}
