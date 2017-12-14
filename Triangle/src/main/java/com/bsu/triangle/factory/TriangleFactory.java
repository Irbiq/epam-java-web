package com.bsu.triangle.factory;

import com.bsu.triangle.action.TriangleCalculator;
import com.bsu.triangle.entity.Point;
import com.bsu.triangle.entity.Triangle;
import com.bsu.triangle.exception.InvalidTriangleParametersException;
import com.bsu.triangle.exception.TriangleValidationException;
import com.bsu.triangle.validator.TriangleValidator;
import com.bsu.triangle.wrapper.TriangleList;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.*;

/**
 * Created by ASUS on 08.10.2017.
 */
public class TriangleFactory {

    private static final Logger LOGGER = LogManager.getLogger(TriangleFactory.class);

    public static Triangle createTriangle(Point a, Point b, Point c) throws InvalidTriangleParametersException {

        if (TriangleCalculator.isCorrectTriangle(a, b, c)) {
            Triangle triangle = new Triangle(a, b, c);
            LOGGER.info("Triangle created. Vertexes are : " + a + " " + b + " " + c);
            return triangle;
        } else {
            throw new InvalidTriangleParametersException();
        }
    }

    public static Triangle createTriangle(String triangleCoordinates) throws InvalidTriangleParametersException, TriangleValidationException {

        List<Point> points;
        Triangle triangle;

        if (TriangleValidator.validate(triangleCoordinates)) {
            points = coordinatesToPoints(triangleCoordinates);
            triangle = TriangleFactory.createTriangle(points.get(0), points.get(1), points.get(2));
        } else {
            throw new TriangleValidationException();
        }
        return triangle;
    }


    public static List<Triangle> createTriangles(List<String> trianglesData) {

        List<Triangle> triangles = TriangleList.getInstance().getTriangles();
        Triangle triangle;
        List<Point> points;
        try {
            for (String triangleCoordinates : trianglesData) {
                triangle = TriangleFactory.createTriangle(triangleCoordinates);
                triangles.add(triangle);
            }
        } catch (InvalidTriangleParametersException e) {
            LOGGER.error("Mathematically incorrect coordinates", e.getMessage(), e);
        } catch (TriangleValidationException e) {
            LOGGER.error("Invalid string wih coordinates", e.getMessage(), e);
        }
        return triangles;
    }

    private static List<Point> coordinatesToPoints(String triangleStr) {

        List<Point> points = new ArrayList<>();
        String[] coordinates = triangleStr.split(" ");
        for (int i = 0; i < coordinates.length; i += 2) {
            points.add(new Point(Double.parseDouble(coordinates[i]), Double.parseDouble(coordinates[i + 1])));
        }
        return points;
    }
}
