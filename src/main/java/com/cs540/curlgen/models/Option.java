package com.cs540.curlgen.models;

public enum Option {
    HEADER("-H"),
    TIMEOUT("--connect-timeout");


    public final String value;

    private Option(String option) {
        this.value = option;
    }
}
