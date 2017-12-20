package com.bsu.exchange.entity;

import com.bsu.exchange.constants.Currency;

import java.util.concurrent.atomic.AtomicInteger;

public class Client {

    int id;

    AtomicInteger totalAmountByr;
    AtomicInteger currentAmountByr;
    AtomicInteger usd;
    AtomicInteger euro;

    public Client() {
    }

    public Client(AtomicInteger currentAmountByr, AtomicInteger usd, AtomicInteger euro) {
        this.currentAmountByr = currentAmountByr;
        this.usd = usd;
        this.euro = euro;
    }

    public AtomicInteger getAmountByCurrency(Currency currency) {
        switch (currency) {
            case USD:
                return this.usd;
            case EURO:
                return this.euro;
            default:
                return null;
        }
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public AtomicInteger getUsd() {
        return usd;
    }

    public void setUsd(AtomicInteger usd) {
        this.usd = usd;
    }

    public AtomicInteger getEuro() {
        return euro;
    }

    public void setEuro(AtomicInteger euro) {
        this.euro = euro;
    }

    public AtomicInteger getCurrentAmountByr() {
        return currentAmountByr;
    }

    public void setCurrentAmountByr(AtomicInteger currentAmountByr) {
        this.currentAmountByr = currentAmountByr;
    }

    @Override
    public String toString() {
        return "Client{" +
                "currentAmountByr=" + currentAmountByr +
                ", usd=" + usd +
                ", euro=" + euro +
                '}';
    }
}



