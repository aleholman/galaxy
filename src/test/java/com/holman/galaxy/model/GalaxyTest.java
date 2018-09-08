package com.holman.galaxy.model;

import com.holman.galaxy.model.builder.PlanetBuilderTest;
import org.junit.Assert;
import org.junit.Test;

public class GalaxyTest {
    double precision = 0.01;

    @Test(expected = IllegalArgumentException.class)
    public void getRainIntensity_nullDay_shouldThrowAnException() {
        Planet planet = new PlanetBuilderTest().dummy().createPlanet();
        Galaxy galaxy = new Galaxy(planet, planet, planet);
        galaxy.getRainIntensity(null);
    }

    @Test
    public void getRainIntensity_planetsInLine_zeroIntensity() {
        Planet planet = new PlanetBuilderTest().dummy().createPlanet();
        Galaxy galaxy = new Galaxy(planet, planet, planet);
        Assert.assertEquals(0, galaxy.getRainIntensity(0), precision);
    }

    @Test
    public void getRainIntensity_planetsInTriangle() {
        Galaxy galaxy = createGalaxy();
        Assert.assertEquals(6262.3, galaxy.getRainIntensity(108), precision);
    }

    @Test(expected = IllegalArgumentException.class)
    public void predictWeather_dayNull_shouldThrowAnException() {
        Galaxy galaxy = createGalaxy();
        galaxy.predictWeather(null);
    }

    @Test
    public void predictWeather_droughtDay() {
        Galaxy galaxy = createGalaxy();
        Assert.assertEquals(Weather.DROUGHT, galaxy.predictWeather(0));
    }

    @Test
    public void predictWeather_OptimalConditionsDay() {
        Planet vulcano = new PlanetBuilderTest().with(b -> {b.kmToTheSun = 100; b.diaryDegrees = 0; b.turningMode= TurningMode.COUNTER_CLOCKWISE;}).createPlanet();
        Planet betasoide = new PlanetBuilderTest().with(b -> {b.kmToTheSun = 200; b.diaryDegrees = 10; b.turningMode = TurningMode.COUNTER_CLOCKWISE;}).createPlanet();
        Planet ferengi = new PlanetBuilderTest().with(b -> {b.kmToTheSun = 200; b.diaryDegrees = 10; b.turningMode = TurningMode.COUNTER_CLOCKWISE;}).createPlanet();
        Galaxy galaxy = new Galaxy(vulcano, betasoide, ferengi);

        Assert.assertEquals(Weather.OPTIMAL_CONDITIONS, galaxy.predictWeather(1));
    }

    @Test
    public void predictWeather_RainDay() {
        Galaxy galaxy = createGalaxy();
        Assert.assertEquals(Weather.RAIN, galaxy.predictWeather(108));
    }

    @Test
    public void predictWeather_UnknownDay() {
        Galaxy galaxy = createGalaxy();
        Assert.assertEquals(Weather.UNKNONW, galaxy.predictWeather(1));
    }


    private Galaxy createGalaxy() {
        Planet vulcano = new PlanetBuilderTest().vulcano().createPlanet();
        Planet betasoide = new PlanetBuilderTest().betasoide().createPlanet();
        Planet ferengi = new PlanetBuilderTest().ferengi().createPlanet();
        return new Galaxy(vulcano, betasoide, ferengi);
    }

}
