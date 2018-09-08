package com.holman.galaxy.model.geometric;

import com.google.common.base.Preconditions;
import com.holman.galaxy.model.Coordinates;

import java.util.Optional;

public class Line {
    private static double PRECISION = 0.01;

    private Coordinates pointA;
    private Coordinates pointB;


    public Line(Coordinates pointA, Coordinates pointB) {
        Preconditions.checkArgument(pointA != null && pointB != null, "Points must not be null");
        this.pointA = pointA;
        this.pointB = pointB;
    }


    // Calculating the slope m = (yB-yA)/(xB-xA)
    private Optional<Double> getSlope() {
        double deltaX = this.pointB.getX() - this.pointA.getX();

        if (deltaX == 0d) {
            return Optional.empty(); // It's a vertical line
        }

        double deltaY = this.pointB.getY() - this.pointA.getY();
        return Optional.of(deltaY / deltaX);
    }


    // Calculating b = y - mx
    private Double getYAxisIntersection(Double slope) {
        return this.pointA.getY() - slope * this.pointA.getX();
    }


    public boolean isInLine(Coordinates point) {
        Preconditions.checkArgument(point != null, "Point must not be null");

        return this.getSlope().map(m -> {
            // A point is in line if its true that y = mx + b
            double calculatedY = m * point.getX() + this.getYAxisIntersection(m);
            return Math.abs(calculatedY - point.getY()) < PRECISION;
        }).orElse(
                // Its a vertical line, so to be in line points should have same x
                Math.abs(this.pointA.getX() - point.getX()) < PRECISION
        );
    }


    public double getDistance() {
        double yPowDistance = Math.pow(this.pointB.getY() - this.pointA.getY(), 2);
        double xPowDistance = Math.pow(this.pointB.getX() - this.pointA.getX(), 2);
        return Math.sqrt(yPowDistance + xPowDistance);
    }

}
