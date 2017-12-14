package com.bsu.triangle.entity;

import com.bsu.triangle.action.TriangleCalculator;
import com.bsu.triangle.observer.TriangleObserver;

/**
 * Created by ASUS on 14.11.2017.
 */
public class TriangleParameter implements TriangleObserver {

    private int id;
    private double square;
    private double perimeter;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getSquare() {
        return square;
    }

    public void setSquare(double square) {
        this.square = square;
    }

    public double getPerimeter() {
        return perimeter;
    }

    public void setPerimeter(double perimeter) {
        this.perimeter = perimeter;
    }


    @Override
    public void recalculate(Triangle triangle) {
        this.square = TriangleCalculator.square(triangle);
        this.perimeter = TriangleCalculator.perimeter(triangle);
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof TriangleParameter)) return false;

        TriangleParameter that = (TriangleParameter) o;

        if (Double.compare(that.square, square) != 0) return false;
        return Double.compare(that.perimeter, perimeter) == 0;
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        temp = Double.doubleToLongBits(square);
        result = (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(perimeter);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        return result;
    }

    @Override
    public String toString() {
        return "TriangleParameter{" +
                "square=" + square +
                ", perimeter=" + perimeter +
                '}';
    }

}
