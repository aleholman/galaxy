package com.holman.galaxy.model;

import com.holman.galaxy.model.builder.PlanetBuilderTest;
import org.junit.Assert;
import org.junit.Test;

public class PlanetTest {

    @Test(expected = NullPointerException.class)
    public void buildingPlanet_withoutTranslationInfo_mustFail() {
        Planet planet = new Planet("dummyPlanet", 300, null);
    }


    @Test(expected = NullPointerException.class)
    public void buildingPlanet_withoutDistanceToTheSun_mustFail() {
        Planet planet = new Planet("dummyPlanet", null, new TranslationMovement(1, TurningMode.CLOCKWISE));
    }

    @Test(expected = IllegalArgumentException.class)
    public void getDegreesFromXAxis_nullParameter() {
        Planet planet = new PlanetBuilderTest().dummy().with(b -> b.turningMode = TurningMode.CLOCKWISE).createPlanet();
        planet.getDegreesFromXAxis(null);
    }


    @Test
    public void getDegreesFromXAxis_dayZeroClockwise_planetsAreAlignedWithXAxis() {
        Planet planet = new PlanetBuilderTest().dummy().with(b -> b.turningMode = TurningMode.CLOCKWISE).createPlanet();
        Assert.assertEquals(Integer.valueOf(0), planet.getDegreesFromXAxis(0));
    }

    @Test
    public void getDegreesFromXAxis_dayZeroCounterClock_planetsAreAlignedWithXAxis() {
        Planet planet = new PlanetBuilderTest().dummy().with(b -> b.turningMode = TurningMode.COUNTER_CLOCKWISE).createPlanet();
        Assert.assertEquals(Integer.valueOf(0), planet.getDegreesFromXAxis(0));
    }


    @Test
    public void getDegreesFromXAxis_counterClockJustOneDegree() {
        Planet planet = new PlanetBuilderTest().dummy().with(builder -> {
           builder.turningMode = TurningMode.COUNTER_CLOCKWISE;
           builder.diaryDegrees = 1;
        }).createPlanet();

        Assert.assertEquals(Integer.valueOf(1), planet.getDegreesFromXAxis(1));
    }


    @Test
    public void getDegreesFromXAxis_counterClockMoreThan360Degrees() {
        Integer days = 100;
        Integer dairyDegrees = 5;
        Integer expected = (days * dairyDegrees) % 360;

        Planet planet = new PlanetBuilderTest().dummy().with(builder -> {
            builder.turningMode = TurningMode.COUNTER_CLOCKWISE;
            builder.diaryDegrees = dairyDegrees;
        }).createPlanet();

        Assert.assertEquals(expected, planet.getDegreesFromXAxis(days));
    }


    @Test
    public void getDegreesFromXAxis_360Degrees() {
        Integer days = 360;
        Integer dairyDegrees = 1;

        Planet planet = new PlanetBuilderTest().dummy().with(builder -> {
            builder.turningMode = TurningMode.COUNTER_CLOCKWISE;
            builder.diaryDegrees = dairyDegrees;
        }).createPlanet();

        Assert.assertEquals(Integer.valueOf(0), planet.getDegreesFromXAxis(days));
    }


    @Test
    public void getDegreesFromXAxis_clockwiseJustOneDegree() {
        Planet planet = new PlanetBuilderTest().dummy().with(builder -> {
            builder.turningMode = TurningMode.CLOCKWISE;
            builder.diaryDegrees = 1;
        }).createPlanet();

        Assert.assertEquals(Integer.valueOf(360-1), planet.getDegreesFromXAxis(1));
    }


    @Test
    public void getDegreesFromXAxis_clockwiseMoreThan360Degrees() {
        Integer days = 100;
        Integer dairyDegrees = 5;
        Integer expected = ((days * dairyDegrees * - 1) % 360) + 360;

        Planet planet = new PlanetBuilderTest().dummy().with(builder -> {
            builder.turningMode = TurningMode.CLOCKWISE;
            builder.diaryDegrees = dairyDegrees;
        }).createPlanet();

        Assert.assertEquals(expected, planet.getDegreesFromXAxis(days));
    }

    @Test(expected = IllegalArgumentException.class)
    public void getCoordinates_dayNull() {
        Planet planet = new PlanetBuilderTest().dummy().createPlanet();
        planet.getCoordinates(null);
    }

    @Test
    public void getCoordinates_calculateInitialPosition() {
        Integer km = 500;
        Integer day = 0;
        Integer diaryDegrees = 1;
        double expectedX = 500;
        double expectedY = 0;

        this.assertGetCoordinates(km, day, diaryDegrees, expectedX, expectedY);
    }

    @Test
    public void getCoordinates_calculatePosition90Degrees() {
        Integer km = 500;
        Integer day = 90;
        Integer diaryDegrees = 1;
        double expectedX = 0;
        double expectedY = 500;

        this.assertGetCoordinates(km, day, diaryDegrees, expectedX, expectedY);
    }


    @Test
    public void getCoordinates_calculatePosition180Degrees() {
        Integer km = 500;
        Integer day = 180;
        Integer diaryDegrees = 1;
        double expectedX = -500;
        double expectedY = 0;

        this.assertGetCoordinates(km, day, diaryDegrees, expectedX, expectedY);
    }

    @Test
    public void getCoordinates_calculatePosition270Degrees() {
        Integer km = 500;
        Integer day = 270;
        Integer diaryDegrees = 1;
        double expectedX = 0;
        double expectedY = -500;

        this.assertGetCoordinates(km, day, diaryDegrees, expectedX, expectedY);
    }

    @Test
    public void getCoordinates_calculatePositionQuadrantI() {
        Integer km = 500;
        Integer day = 30;
        Integer diaryDegrees = 1;
        double expectedX = 433.01;
        double expectedY = 250;

        this.assertGetCoordinates(km, day, diaryDegrees, expectedX, expectedY);
    }

    @Test
    public void getCoordinates_calculatePositionQuadrantII() {
        Integer km = 500;
        Integer day = 120;
        Integer diaryDegrees = 1;
        double expectedX = -250;
        double expectedY = 433.01;

        this.assertGetCoordinates(km, day, diaryDegrees, expectedX, expectedY);
    }

    @Test
    public void getCoordinates_calculatePositionQuadrantIII() {
        Integer km = 500;
        Integer day = 210;
        Integer diaryDegrees = 1;
        double expectedX = -433.01;
        double expectedY = -250;

        this.assertGetCoordinates(km, day, diaryDegrees, expectedX, expectedY);
    }

    @Test
    public void getCoordinates_calculatePositionQuadrantIV() {
        Integer km = 500;
        Integer day = 300;
        Integer diaryDegrees = 1;
        double expectedX = 250;
        double expectedY = -433.01;

        this.assertGetCoordinates(km, day, diaryDegrees, expectedX, expectedY);
    }

    private void assertGetCoordinates(Integer km, Integer day, Integer diaryDegrees, double expectedX, double expectedY) {
        Planet planet = new PlanetBuilderTest().dummy().with(b -> {
            b.kmToTheSun = km;
            b.diaryDegrees = diaryDegrees;
            b.turningMode = TurningMode.COUNTER_CLOCKWISE;
        }).createPlanet();

        Coordinates result = planet.getCoordinates(day);

        Assert.assertEquals(expectedX, result.getX(), 0.01);
        Assert.assertEquals(expectedY, result.getY(), 0.01);
    }



}

