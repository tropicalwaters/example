package com.city;

import com.city.service.CityEventService;
import com.city.util.CampgroundConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.web.SpringBootServletInitializer;

import javax.annotation.PostConstruct;

@SpringBootApplication
public class Application extends SpringBootServletInitializer {

    @Autowired
    public CityEventService cityEventService;

    @Autowired
    CampgroundConfig campgroundConfig;

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @PostConstruct
    public void postConstruct() throws Exception {
        cityEventService.loadAllEventsIntoMemory();
    }

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        return builder.sources(getClass());
    }
}