package com.holman.galaxy;

import com.holman.galaxy.model.Galaxy;
import com.holman.galaxy.model.Weather;
import com.holman.galaxy.model.builder.GalaxyBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


public class Main {
    private static final Logger logger = LoggerFactory.getLogger(Main.class);

    private static Integer DAYS_PER_YEAR = 360;
    private static Integer yearsToPredict = 10;


    public static void main( String[] args ) {

        Galaxy galaxy = GalaxyBuilder.buildGalaxy();
        Map<Integer, Weather> predictions = galaxy.predictWeather(0, yearsToPredict * DAYS_PER_YEAR);

        showPeriodsPerWeather(predictions);
        showDayMaxRainIntensity(galaxy, predictions);

    }

    private static void showDayMaxRainIntensity(Galaxy galaxy, Map<Integer, Weather> predictions) {
        List<Integer> rainDaysSortedByIntensity = predictions.entrySet().stream().filter(e -> Weather.RAIN.equals(e.getValue())).
                map(Map.Entry::getKey).
                sorted((day1, day2) -> Double.compare(galaxy.getRainIntensity(day2), galaxy.getRainIntensity(day1))).
                collect(Collectors.toList());

        logger.info("Day of max rain intensity: {}",
                rainDaysSortedByIntensity.stream().findFirst().
                        map(Object::toString).
                        orElse("There weren't any rainy days")
        );
    }

    private static void showPeriodsPerWeather(Map<Integer, Weather> predictions) {
        // Removing consecutive days with the same weather
        LinkedList<Weather> weatherPeriods = predictions.values().stream().collect(LinkedList<Weather>::new,
                (list, elem) -> {
                    if (list.isEmpty() || !elem.equals(list.getLast()))
                        list.add(elem);
                },
                LinkedList<Weather>::addAll);

        logger.info("Rain periods: {}", weatherPeriods.stream().filter(Weather.RAIN::equals).count());
        logger.info("Drought periods: {}", weatherPeriods.stream().filter(Weather.DROUGHT::equals).count());
        logger.info("OptimalConditions periods: {}", weatherPeriods.stream().filter(Weather.OPTIMAL_CONDITIONS::equals).count());
    }

}
