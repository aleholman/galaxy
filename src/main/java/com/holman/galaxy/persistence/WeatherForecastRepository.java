package com.holman.galaxy.persistence;

import com.holman.galaxy.model.entities.WeatherForecast;
import org.springframework.data.repository.CrudRepository;

public interface WeatherForecastRepository extends CrudRepository<WeatherForecast, Integer>, CustomWeatherForecastRepository {

}

