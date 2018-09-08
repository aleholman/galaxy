package com.holman.galaxy;

import com.holman.galaxy.model.Galaxy;
import com.holman.galaxy.model.builder.GalaxyBuilder;
import com.holman.galaxy.service.WeatherService;
import com.holman.galaxy.service.impl.WeatherServiceImpl;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableJpaRepositories("com.holman.galaxy.persistence")
@EntityScan("com.holman.galaxy.model")
@EnableScheduling
public class GalaxyApplication {

	public static void main(String[] args) {
		SpringApplication.run(GalaxyApplication.class, args);
	}

	@Bean
	WeatherService weatherService() {
		return new WeatherServiceImpl();
	}

	@Bean
	Galaxy galaxy() {
		return GalaxyBuilder.buildGalaxy();
	}


}
