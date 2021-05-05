package com.cs540.curlgen.models;

public enum Option {
    HEADER("-H"),
    TIMEOUT("--connect-timeout"),
    METHOD("-X"),
    DATA("--data"),
    CONFIG("--config"),
    KEEPALIVE("--keepalive-time"),
    USER("--user"),
    PRIVATE_KEY("--key"),
    PUBLIC_KEY("--pubkey"),
    AGENT("--user-agent"),
    COOKIE("--cookie"),
    COOKIE_JAR("--cookie-jar"),
    MAX_TIME("--max-time"),
    LOCATION("--location"),
    RETRY_MAX_TIME("--retry-max-time"),
    RETRY_DELAY("--retry-delay"),
    PASS_PHRASE("--pass"),
    OUTPUT_FILE("--output"),
    COMPRESSION_ENABLED("--compressed"),
    PROXY("--proxy");

    public final String value;

    private Option(String option) {
        this.value = option;
    }
}