package com.holman.galaxy.model;

import com.google.common.base.Preconditions;

public class Planet {
    private static Integer ORBIT_DEGREES = 360;

    private String name;
    private Integer kmToTheSun;
    private TranslationMovement translationMovement;

    public Planet(String name, Integer kmToTheSun, TranslationMovement translationMovement) {
        Preconditions.checkNotNull(kmToTheSun, "Distance to the Sun is required");
        Preconditions.checkNotNull(translationMovement, "Translation movement is required");

        this.name = name;
        this.kmToTheSun = kmToTheSun;
        this.translationMovement = translationMovement;
    }


    public Integer getDegreesFromXAxis(Integer day) {
        Preconditions.checkArgument(day != null, "Day must not be null");

        Integer currentDegrees = (translationMovement.getDiaryDegrees() * day) % ORBIT_DEGREES;

        if (TurningMode.CLOCKWISE.equals(translationMovement.getTurningMode()) && currentDegrees != 0) {
            currentDegrees = ORBIT_DEGREES - currentDegrees;
        }

        return currentDegrees;
    }


    public Coordinates getCoordinates(Integer day) {
        Preconditions.checkArgument(day != null, "Day must not be null");

        Integer degrees = getDegreesFromXAxis(day);
        double radians = Math.toRadians(degrees);

        double x = this.kmToTheSun * Math.cos(radians);
        double y = this.kmToTheSun * Math.sin(radians);

        return new Coordinates(x,y);
    }


    public String getName() {
        return name;
    }



}
