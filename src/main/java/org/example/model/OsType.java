package org.example.model;

public enum OsType {
    WINDOWS("windows"),
    LINUX("linux");

    private final String value;

    OsType(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}