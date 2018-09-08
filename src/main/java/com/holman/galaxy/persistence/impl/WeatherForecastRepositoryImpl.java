package com.holman.galaxy.persistence.impl;

import com.holman.galaxy.model.entities.WeatherForecast;
import com.holman.galaxy.persistence.CustomWeatherForecastRepository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.Optional;

public class WeatherForecastRepositoryImpl implements CustomWeatherForecastRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Optional<WeatherForecast> findByDay(Integer day) {
        Query query = entityManager.createNativeQuery("SELECT w.id, w.day, w.weather FROM weather_forecast w WHERE w.day = ?", WeatherForecast.class);
        query.setParameter(1, day);
        return query.getResultList().stream().findFirst();
    }
}
