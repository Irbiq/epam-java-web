package com.bsu.xmlparsing.entity.type;


public enum CardType {
    ORDINARY_CARD("OrdinaryCard"), GREETING_CARD("GreetingCard");
    String value;

    CardType(String ordinaryCard) {
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
