package com.holman.galaxy.model.geometric;

import com.holman.galaxy.model.Coordinates;
import org.junit.Assert;
import org.junit.Test;

public class TriangleTest {
    double precision = 0.01;

    @Test
    public void perimeter_sameThreePoints_shouldReturnZero() {
        Coordinates point = new Coordinates(1,-3);
        Triangle triangle = new Triangle(point, point, point);
        Assert.assertEquals(0, triangle.getPerimeter(), precision);
    }

    @Test
    public void perimeter_threePointsInTriangle_returnPerimeterOk() {
        Coordinates pointA = new Coordinates(6,1);
        Coordinates pointB = new Coordinates(3,-1);
        Coordinates pointC = new Coordinates(-2 ,5);

        Triangle triangle = new Triangle(pointA, pointB, pointC);
        Assert.assertEquals(20.36, triangle.getPerimeter(), precision);
    }

    @Test
    public void isInside_pointOutsideTriangle_returnFalse() {
        Coordinates pointA = new Coordinates(6,1);
        Coordinates pointB = new Coordinates(3,-1);
        Coordinates pointC = new Coordinates(-2 ,5);
        Triangle triangle = new Triangle(pointA, pointB, pointC);

        Assert.assertFalse(triangle.isInside(new Coordinates(7,1)));
        Assert.assertFalse(triangle.isInside(new Coordinates(3,-2)));
        Assert.assertFalse(triangle.isInside(new Coordinates(-3,11)));
        Assert.assertFalse(triangle.isInside(new Coordinates(4,-1)));
    }

    @Test
    public void isInside_pointInTheEdge_returnTrue() {
        Coordinates pointA = new Coordinates(6,1);
        Coordinates pointB = new Coordinates(6,-1);
        Coordinates pointC = new Coordinates(-2 ,5);
        Triangle triangle = new Triangle(pointA, pointB, pointC);

        Assert.assertTrue(triangle.isInside(new Coordinates(6, 0)));
    }

    @Test
    public void isInside_pointInsideTriangle_returnTrue() {
        Coordinates pointA = new Coordinates(6,2);
        Coordinates pointB = new Coordinates(6,-2);
        Coordinates pointC = new Coordinates(-2 ,0);
        Triangle triangle = new Triangle(pointA, pointB, pointC);

        Assert.assertTrue(triangle.isInside(new Coordinates(0, 0)));
        Assert.assertTrue(triangle.isInside(new Coordinates(5, 1)));
        Assert.assertTrue(triangle.isInside(new Coordinates(5, -1)));
        Assert.assertTrue(triangle.isInside(new Coordinates(-1, 0)));
    }

}
