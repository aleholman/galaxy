package com.holman.galaxy.api.controllers;

import com.holman.galaxy.api.dto.WeatherForecastDTO;
import com.holman.galaxy.service.WeatherService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/clima")
public class WeatherForecastController {
    private static final Logger logger = LoggerFactory.getLogger(WeatherForecastController.class);

    @Autowired
    private WeatherService weatherService;

    @GetMapping("/dia/{day}")
    public WeatherForecastDTO predictWeather(@PathVariable Integer day) {
        logger.info(String.format("Getting weather conditions on day %s", day));
        return weatherService.findByDay(day);
    }

}
