package com.cloudtemplate.shared.constans;

public enum LogStatus {
    SUCCESS("SUCCESS"),
    FAIL("FAIL");

    public final String value;

    private LogStatus(String value) {
        this.value = value;
    }
}
