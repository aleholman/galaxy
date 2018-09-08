package com.holman.galaxy.model;

import com.google.common.base.Preconditions;
import com.holman.galaxy.model.geometric.Line;
import com.holman.galaxy.model.geometric.Triangle;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;


public class Galaxy {
    private static final Logger logger = LoggerFactory.getLogger(Galaxy.class);

    private Coordinates sunPosition = new Coordinates(0, 0);

    private Planet vulcano;
    private Planet betasoide;
    private Planet ferengi;

    public Galaxy(Planet ferengi, Planet betasoide, Planet vulcano) {
        Preconditions.checkArgument(ferengi != null && betasoide != null & vulcano != null, "Planets must not be null");

        this.ferengi = ferengi;
        this.betasoide = betasoide;
        this.vulcano = vulcano;
    }


    public Map<Integer, Weather> predictWeather(Integer dayFrom, Integer dayTo) {
        Preconditions.checkArgument(dayFrom != null && dayTo != null, "Day from and to are required");

        return IntStream.rangeClosed(dayFrom, dayTo).boxed().collect(
                Collectors.toMap(Function.identity(), this::predictWeather)
        );
    }



    public Weather predictWeather(Integer day) {
        Preconditions.checkArgument(day != null, "Day is required");

        Coordinates ferengiPosition = ferengi.getCoordinates(day);
        Coordinates betasoidePosition = betasoide.getCoordinates(day);
        Coordinates vulcanoPosition = vulcano.getCoordinates(day);

        Line sunLine = new Line(sunPosition, ferengiPosition);
        if(sunLine.isInLine(vulcanoPosition) && sunLine.isInLine(betasoidePosition)) {
            logger.debug("Three planets and Sun are in line, so day {} is a drought day", day);
            return Weather.DROUGHT;
        }

        Line line2Planets = new Line(ferengiPosition, betasoidePosition);
        if(line2Planets.isInLine(vulcanoPosition)) {
            logger.debug("Three planets are in line but Sun isn't, so day {} has optimal conditions", day);
            return Weather.OPTIMAL_CONDITIONS;
        }

        Triangle planetsTriangle = new Triangle(ferengiPosition, betasoidePosition, vulcanoPosition);
        if(planetsTriangle.isInside(sunPosition)) {
            logger.debug("Three planets in triangle with the Sun inside it, so day {} is a rainny day", day);
            return Weather.RAIN;
        }

        logger.debug("Any rule was applied, so we don't know what's the weather like on day {}", day);
        return Weather.UNKNONW;
    }


    public double getRainIntensity(Integer day) {
        Preconditions.checkArgument(day != null, "Day must not be null");

        Coordinates ferengiPosition = ferengi.getCoordinates(day);
        Coordinates betasoidePosition = betasoide.getCoordinates(day);
        Coordinates vulcanoPosition = vulcano.getCoordinates(day);

        Line line = new Line(ferengiPosition, betasoidePosition);
        if(line.isInLine(vulcanoPosition))
            return 0;

        Triangle planetsTriangle = new Triangle(ferengiPosition, betasoidePosition, vulcanoPosition);
        return planetsTriangle.getPerimeter();
    }

}
