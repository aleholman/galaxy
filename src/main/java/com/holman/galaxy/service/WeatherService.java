package com.holman.galaxy.service;

import com.holman.galaxy.api.dto.WeatherForecastDTO;

public interface WeatherService {

    /**
     * Returns the weather predicted for the given day. This method looks for the prediction in the database at first. If its not there, the prediction is calculated and returned
     * @param day Day to predict the weather
     * @return @{@link WeatherForecastDTO}
     */
    WeatherForecastDTO findByDay(Integer day);

    /**
     * This method calculates and stores the weather forecast for the given period
     * @param fromDay First day of the period to calculate
     * @param toDay Last day of the period to calculate (included)
     */
    void calculateAndStorePredictions(Integer fromDay, Integer toDay);

}
