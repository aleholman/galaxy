package com.holman.galaxy.model.builder;

import com.holman.galaxy.model.Galaxy;
import com.holman.galaxy.model.Planet;
import com.holman.galaxy.model.TurningMode;

public class GalaxyBuilder {

    public static Galaxy buildGalaxy() {

        Planet ferengi = new PlanetBuilder().with(builder -> {
            builder.name = "Ferengi";
            builder.kmToTheSun = 500;
            builder.diaryDegrees = 1;
            builder.turningMode = TurningMode.CLOCKWISE;
        }).createPlanet();

        Planet betasoide = new PlanetBuilder().with(builder -> {
            builder.name = "Betasoide";
            builder.kmToTheSun = 2000;
            builder.diaryDegrees = 3;
            builder.turningMode = TurningMode.CLOCKWISE;
        }).createPlanet();


        Planet vulcano = new PlanetBuilder().with(builder -> {
            builder.name = "Vulcano";
            builder.kmToTheSun = 1000;
            builder.diaryDegrees = 5;
            builder.turningMode = TurningMode.COUNTER_CLOCKWISE;
        }).createPlanet();

        return new Galaxy(ferengi, betasoide, vulcano);
    }

}
