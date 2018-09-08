package com.holman.galaxy.job;

import com.holman.galaxy.service.WeatherService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class ScheduledWeatherForecast {

    private static final Logger logger = LoggerFactory.getLogger(ScheduledWeatherForecast.class);

    @Value("${job.weatherForecast.yearsToPredict}")
    private Integer yearsToPredict;

    @Value("${galaxy.daysPerYear}")
    private Integer daysPerYear;

    @Autowired
    private WeatherService weatherService;

    private boolean alreadyRun = false;

    @Scheduled(fixedDelayString = "${job.weatherForecast.fixedDelayMillis}", initialDelayString = "${job.weatherForecast.initialDelayMillis}")
    public void predict() {
        logger.info("Starting scheduled job: ScheduledWeatherForecast...");

        if (alreadyRun) {
            logger.info("The job has already been executed");
        } else {
            weatherService.calculateAndStorePredictions(0, daysPerYear * yearsToPredict);
            alreadyRun = true;
        }

        logger.info("ScheduledWeatherForecast finished ok");
    }

}
