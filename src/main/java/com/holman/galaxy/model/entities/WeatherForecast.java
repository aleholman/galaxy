package com.holman.galaxy.model.entities;


import com.holman.galaxy.model.Weather;

import javax.persistence.*;

@Entity
@Table(name = "weather_forecast")
public class WeatherForecast {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column(nullable = false, unique = true)
    private Integer day;

    @Column(nullable = false)
    private Weather weather;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getDay() {
        return day;
    }

    public void setDay(Integer day) {
        this.day = day;
    }

    public Weather getWeather() {
        return weather;
    }

    public void setWeather(Weather weather) {
        this.weather = weather;
    }
}