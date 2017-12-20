package com.bsu.exchange.constants;


public class ExchangeRate {

    private static int usdToByrRate = 2;
    private static int euroToByrRate = 3;

    public static int getRate(Currency currency) {
        switch (currency){
            case USD:
                return usdToByrRate;
            case EURO:
                return euroToByrRate;
            default:
                throw new IllegalArgumentException();
        }
    }
}
