package com.nawasoft.datalayer.model;

public enum AccountType {
    MEMBER(0),
    COMPANY(1);

    private int value;

    AccountType(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public static AccountType getTypeByValue(int value) {
        return value == 0 ? MEMBER : COMPANY;
    }
}
