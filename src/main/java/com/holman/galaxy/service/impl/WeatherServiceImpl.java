package com.holman.galaxy.service.impl;

import com.holman.galaxy.api.dto.WeatherForecastDTO;
import com.holman.galaxy.model.Galaxy;
import com.holman.galaxy.model.Weather;
import com.holman.galaxy.model.entities.WeatherForecast;
import com.holman.galaxy.persistence.WeatherForecastRepository;
import com.holman.galaxy.service.WeatherService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.stream.Collectors;

public class WeatherServiceImpl implements WeatherService {

    private static final Logger logger = LoggerFactory.getLogger(WeatherServiceImpl.class);

    @Autowired
    private WeatherForecastRepository weatherRepository;

    @Autowired
    private Galaxy galaxy;

    public WeatherForecastDTO findByDay(Integer day) {
        logger.info(String.format("Finding weather forecast by day %s", day));

        Weather weatherConditions = weatherRepository.findByDay(day).map(WeatherForecast::getWeather).orElseGet(() -> {
            logger.debug(String.format("Prediction for day %s not found on database. Calculating it...", day));
            return galaxy.predictWeather(day);
        });

        return new WeatherForecastDTO(day, weatherConditions.getValue());
    }

    public void calculateAndStorePredictions(Integer fromDay, Integer toDay) {
        logger.info(String.format("Calculating predictions from %s to %s", fromDay, toDay));
        List<WeatherForecast> weatherForecasts = galaxy.predictWeather(fromDay, toDay).entrySet().stream().map(entry -> {

            WeatherForecast weatherForecast = new WeatherForecast();
            weatherForecast.setDay(entry.getKey());
            weatherForecast.setWeather(entry.getValue());

            return weatherForecast;
        }).collect(Collectors.toList());

        logger.info(String.format("Saving %s predictions", weatherForecasts.size()));
        weatherRepository.saveAll(weatherForecasts);
    }

}
