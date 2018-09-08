package com.holman.galaxy.model.builder;

import com.holman.galaxy.model.Planet;
import com.holman.galaxy.model.TranslationMovement;
import com.holman.galaxy.model.TurningMode;

import java.util.function.Consumer;

public class PlanetBuilder {

    public String name;
    public Integer kmToTheSun;
    public Integer diaryDegrees;
    public TurningMode turningMode;


    public PlanetBuilder with(Consumer<PlanetBuilder> builderFunction) {
        builderFunction.accept(this);
        return this;
    }


    public Planet createPlanet() {
        return new Planet(name, kmToTheSun, new TranslationMovement(diaryDegrees, turningMode));
    }

}

