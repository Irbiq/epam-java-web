package com.bsu.triangle.observer;

import com.bsu.triangle.action.TriangleCalculator;
import com.bsu.triangle.entity.Triangle;
import com.bsu.triangle.entity.TriangleParameter;
import com.bsu.triangle.factory.TriangleFactory;
import com.bsu.triangle.reader.TriangleReader;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.File;
import java.io.FileWriter;
import java.util.List;

public class TriangleObserverTest {

    File file;

    String testData = "0 0 0 1 1 0 \n" +
            "0 0 0 2 2 0 \n" +
            "1 1 1 2 3 3 \n";

    @BeforeMethod
    public void setUp() throws Exception {
        file = File.createTempFile("test-file-", ".txt", new File("files"));
        FileWriter writer = new FileWriter(file);
        writer.write(testData);
        writer.flush();
        writer.close();

    }

    @AfterMethod
    public void tearDown() throws Exception {
        file.delete();
    }

    @Test
    public void testTriangleObserverPerimeter() throws Exception {

        List<String> trData = TriangleReader.readTriangles(file.getPath());
        List<Triangle> triangles = TriangleFactory.createTriangles(trData);
        TriangleObserver tobs1 = new TriangleParameter();
        TriangleParameter tobs2 = new TriangleParameter();

        triangles.get(0).addObserver(tobs1);
        double expectedPerimeter1 = ((TriangleParameter) tobs1).getPerimeter();
        double eps = 0.000_01;

        Assert.assertEquals(TriangleCalculator.perimeter(triangles.get(0)), expectedPerimeter1, eps);
    }

    @Test
    public void testTriangleObserverSquare() throws Exception {

        List<String> trData = TriangleReader.readTriangles(file.getPath());
        List<Triangle> triangles = TriangleFactory.createTriangles(trData);
        TriangleObserver trObs = new TriangleParameter();

        triangles.get(0).addObserver(trObs);
        double expectedSquare = ((TriangleParameter) trObs).getSquare();
        double eps = 0.000_01;

        Assert.assertEquals(TriangleCalculator.square(triangles.get(0)), expectedSquare, eps);
    }

}
