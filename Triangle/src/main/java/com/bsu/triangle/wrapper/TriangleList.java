package com.bsu.triangle.wrapper;

import com.bsu.triangle.entity.Triangle;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by ASUS on 14.11.2017.
 */
public class TriangleList {

    private static TriangleList instance;
    private List<Triangle> triangles ;

    private TriangleList(){
        triangles = new ArrayList<>();
    }

    public static TriangleList getInstance(){
        if(instance==null){
            instance = new TriangleList();
        }
        return instance;
    }

    public List<Triangle> getTriangles() {
        return triangles;
    }


}
