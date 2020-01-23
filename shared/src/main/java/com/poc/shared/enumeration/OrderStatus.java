package com.poc.shared.enumeration;

public enum OrderStatus {
    PENDING("PENDING"),
    COMPLETED("COMPLETED"),
    FAIL("FAIL");

    public final String value;

    private OrderStatus(String value) {
        this.value = value;
    }
}
