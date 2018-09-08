package com.holman.galaxy.persistence;

import com.holman.galaxy.model.entities.WeatherForecast;

import java.util.Optional;

public interface CustomWeatherForecastRepository {

    /**
     * Looks for the weather forecast of the given day on database.
     * @param day Day to find the weather conditions.
     * @return @{@link Optional} of @{@link WeatherForecast}
     */
    Optional<WeatherForecast> findByDay(Integer day);

}

