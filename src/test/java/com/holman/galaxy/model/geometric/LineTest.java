package com.holman.galaxy.model.geometric;

import com.holman.galaxy.model.Coordinates;
import org.junit.Assert;
import org.junit.Test;

public class LineTest {
    double precision = 0.01;

    @Test
    public void distance_samePoints_shouldReturnZero() {
        Coordinates point = new Coordinates(1,1);
        Line line = new Line(point, point);
        Assert.assertEquals(0, line.getDistance(), precision);
    }

    @Test
    public void distance_twoHorizontalPoints() {
        Coordinates pointA = new Coordinates(1, 2);
        Coordinates pointB = new Coordinates(3, 2);
        Line line = new Line(pointA, pointB);
        Assert.assertEquals(3-1, line.getDistance(), precision);
    }

    @Test
    public void distance_twoVerticalPoints() {
        Coordinates pointA = new Coordinates(1,4);
        Coordinates pointB = new Coordinates(1, -6);

        Line lineAB = new Line(pointA, pointB);
        Assert.assertEquals(4+6, lineAB.getDistance(), precision);

        Line lineBA = new Line(pointB, pointA);
        Assert.assertEquals(4+6, lineBA.getDistance(), precision);

    }

    @Test
    public void distance_twoDiagonalPoints() {
        Coordinates pointA = new Coordinates(1,3);
        Coordinates pointB = new Coordinates(2, -4);
        Line line = new Line(pointB, pointA);

        double expected =  Math.sqrt(Math.pow(2-1, 2) + Math.pow(-4-3, 2));
        Assert.assertEquals(expected, line.getDistance(), precision);
    }


    @Test(expected = IllegalArgumentException.class)
    public void isInLine_noPoint_shouldThrowAnException() {
        Line line = new Line(new Coordinates(0,0), new Coordinates(1,1));
        line.isInLine(null);
    }


    @Test
    public void isInLine_samePoints_returnTrue() {
        Coordinates point = new Coordinates(1,1);
        Line line = new Line(point, point);
        Assert.assertTrue(line.isInLine(point));
    }

    @Test
    public void isInLine_threePointsInDiagonalLine_returnTrue() {
        Coordinates pointA = new Coordinates(4,-2);
        Coordinates pointB = new Coordinates(0, 0);
        Coordinates pointC = new Coordinates(-8, 4);

        Line line = new Line(pointA, pointB);
        Assert.assertTrue(line.isInLine(pointC));
    }

    @Test
    public void isInLine_threePointsInVerticalLine_returnTrue() {
        Coordinates pointA = new Coordinates(4,-2);
        Coordinates pointB = new Coordinates(4, 0);
        Coordinates pointC = new Coordinates(4, 4);

        Line line = new Line(pointA, pointB);
        Assert.assertTrue(line.isInLine(pointC));
    }

    @Test
    public void isInLine_threePointsInHorizontalLine_returnTrue() {
        Coordinates pointA = new Coordinates(2,-5);
        Coordinates pointB = new Coordinates(-12, -5);
        Coordinates pointC = new Coordinates(3, -5);

        Line line = new Line(pointA, pointB);
        Assert.assertTrue(line.isInLine(pointC));
    }

    @Test
    public void isInLine_threePointsNotAligned_returnFalse() {
        Coordinates pointA = new Coordinates(1,-2);
        Coordinates pointB = new Coordinates(0, 0);
        Coordinates pointC = new Coordinates(-5, 12);

        Line line = new Line(pointA, pointB);
        Assert.assertFalse(line.isInLine(pointC));
    }

}
