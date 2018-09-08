package com.holman.galaxy.model.geometric;

import com.google.common.base.Preconditions;
import com.holman.galaxy.model.Coordinates;

public class Triangle {
    private Coordinates verticeA;
    private Coordinates verticeB;
    private Coordinates verticeC;

    public Triangle(Coordinates pointA, Coordinates pointB, Coordinates pointC) {
        Preconditions.checkArgument(pointA != null && pointB != null && pointC != null, "Points cannot be null");

        this.verticeA = pointA;
        this.verticeB = pointB;
        this.verticeC = pointC;
    }


    public double getPerimeter() {
        double distanceAB = new Line(this.verticeA, this.verticeB).getDistance();
        double distanceBC = new Line(this.verticeB, this.verticeC).getDistance();
        double distanceCA = new Line(this.verticeC, this.verticeA).getDistance();

        return distanceAB + distanceBC + distanceCA;
    }


    // Making 3 new triangles using the given point, if the sum if their 3 areas are equals the area od the original triangle, the point is inside.
    public boolean isInside(Coordinates point) {
        Double originalArea = area(this.verticeA, this.verticeB, this.verticeC);

        double areaReplacingA = area(point, this.verticeB, this.verticeC);
        double areaReplacingB = area(this.verticeA, point, this.verticeC);
        double areaReplacingC = area(this.verticeA, this.verticeB, point);

        return originalArea.equals(areaReplacingA + areaReplacingB + areaReplacingC);
    }


    // Given three points, A, B and C the triangle area is the module of [ Ax (By −	Cy) + Bx (Cy −	Ay)	+ Cx (Ay − By) ] / 2
    private double area(Coordinates pointA, Coordinates pointB, Coordinates pointC) {
        double tmp = pointA.getX() * (pointB.getY() - pointC.getY()) +
                pointB.getX() * (pointC.getY() - pointA.getY()) +
                pointC.getX() * (pointA.getY() - pointB.getY());
        return Math.abs(tmp / 2);
    }

}
