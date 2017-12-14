package com.bsu.triangle.entity;

import com.bsu.triangle.idgenerator.IdGenerator;
import com.bsu.triangle.observer.TriangleObserver;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ASUS on 07.10.2017.
 */

@NoArgsConstructor
@ToString
public class Triangle {

    private int id;

    private Point a;
    private Point b;
    private Point c;

    private List<TriangleObserver> observers = new ArrayList<>();

    public void addObserver(TriangleObserver observer) {

        observer.setId(this.id);
        observer.recalculate(this);
        observers.add(observer);
    }

    private void notifyObservers() {
        observers.forEach(observer -> observer.recalculate(this));
    }


    public Triangle(Point a, Point b, Point c) {
        this.id = IdGenerator.generateId();
        this.a = a;
        this.b = b;
        this.c = c;
    }

    public void setA(Point a) {
        this.a = a;
        notifyObservers();
    }

    public void setB(Point b) {
        this.b = b;
        notifyObservers();
    }

    public void setC(Point c) {
        this.c = c;
        notifyObservers();
    }

    public int getId(){
        return id;
    }

    public Point getA() {
        return a;
    }

    public Point getB() {
        return b;
    }

    public Point getC() {
        return c;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Triangle)) return false;

        Triangle triangle = (Triangle) o;

        if (a != null ? !a.equals(triangle.a) : triangle.a != null) return false;
        if (b != null ? !b.equals(triangle.b) : triangle.b != null) return false;
        return c != null ? c.equals(triangle.c) : triangle.c == null;
    }

    @Override
    public int hashCode() {
        int result = a != null ? a.hashCode() : 0;
        result = 31 * result + (b != null ? b.hashCode() : 0);
        result = 31 * result + (c != null ? c.hashCode() : 0);
        return result;
    }
}
