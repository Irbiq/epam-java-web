package com.bsu.triangle.wrapper;

import com.bsu.triangle.entity.Triangle;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by ASUS on 14.11.2017.
 */
public class TriangleListThreadSafe {

    private static TriangleListThreadSafe instance;
    private static AtomicBoolean isCreated = new AtomicBoolean(false) ;
    private static ReentrantLock lock = new ReentrantLock();

    private List<Triangle> triangles ;

    private TriangleListThreadSafe(){
        triangles = new ArrayList<>();
    }

    public static TriangleListThreadSafe getInstance(){
        if(!isCreated.get()){
            lock.lock();
            try{
                if(instance==null){
                    instance = new TriangleListThreadSafe();
                    isCreated.set(true);
                }
            }finally {
                lock.unlock();
            }
        }
        return instance;
    }

    public List<Triangle> getTriangles(){
        return triangles;
    }

}

