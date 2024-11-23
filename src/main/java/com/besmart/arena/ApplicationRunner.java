package com.besmart.arena;

import com.besmart.arena.service.refresh.ArenaObjectRefreshingService;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;

import static org.springframework.boot.SpringApplication.run;

//TODO: remove extra code
@SpringBootApplication
@ConfigurationPropertiesScan
public class ApplicationRunner {

    public static void main(String[] args) {
        run(ApplicationRunner.class, args)
                .getBean(ArenaObjectRefreshingService.class)
                .refresh();
    }
}
