package com.bsu.triangle.observer;

import com.bsu.triangle.entity.Triangle;

/**
 * Created by ASUS on 14.11.2017.
 */
public interface TriangleObserver {

    void setId(int id);
    void recalculate (Triangle triangle);

}
