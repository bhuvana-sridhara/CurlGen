package com.cs540.curlgen.models;

public enum Option {
    HEADER("-H"),
    TIMEOUT("--connect-timeout"),
    METHOD("-X");

    public final String value;

    private Option(String option) {
        this.value = option;
    }
}