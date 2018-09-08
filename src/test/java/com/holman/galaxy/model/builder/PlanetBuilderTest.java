package com.holman.galaxy.model.builder;

import com.holman.galaxy.model.TurningMode;

public class PlanetBuilderTest extends PlanetBuilder {

    public PlanetBuilder dummy() {
        this.name = "dummy";
        this.kmToTheSun = 300;
        this.diaryDegrees = 1;
        this.turningMode = TurningMode.CLOCKWISE;
        return this;
    }

    public PlanetBuilder ferengi() {
        this.name = "Ferengi";
        this.kmToTheSun = 500;
        this.diaryDegrees = 1;
        this.turningMode = TurningMode.CLOCKWISE;
        return this;
    }

    public PlanetBuilder betasoide() {
        this.name = "Betasoide";
        this.kmToTheSun = 2000;
        this.diaryDegrees = 3;
        this.turningMode = TurningMode.CLOCKWISE;
        return this;
    }

    public PlanetBuilder vulcano() {
        this.name = "Vulcano";
        this.kmToTheSun = 1000;
        this.diaryDegrees = 5;
        this.turningMode = TurningMode.COUNTER_CLOCKWISE;
        return this;
    }

}
