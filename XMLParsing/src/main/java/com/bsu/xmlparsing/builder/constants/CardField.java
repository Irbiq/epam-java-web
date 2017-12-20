package com.bsu.xmlparsing.builder.constants;

public enum CardField {
    OLDCARDS("OldCards"),
    CARD("Card"),
    GREETINGCARD("GreetingCard"),
    ORDINARYCARD("OrdinaryCard"),
    THEME("theme"),
    HISTORY("history"),
    AUTHOR("author"),
    YEAR("year"),
    COUNTRY("country"),
    VALUABLE("valuable"),
    TEXT("text"),
    ID("id"),
    SENT("sent"),
    EMPTY_TAG("");

    private String value;

    CardField(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}

