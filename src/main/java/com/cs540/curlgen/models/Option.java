package com.cs540.curlgen.models;

public enum Option {
    COMPRESSED("--compressed"),
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
    COOKIE_NAME("-b"),
    MAX_TIME("--max-time"),
    LOCATION("--location"),
    RETRY_MAX_TIME("--retry-max-time"),
    RETRY_DELAY("--retry-delay"),
    PASS_PHRASE("--pass"),
    OUTPUT_FILE("--output"),
    COMPRESSION_ENABLED("--compressed"),
    PROXY("--proxy"),
    DATA_BINARY("--data-binary"),
    FORM("-F"),
    HEADER("-H"),
    INSECURE("-k"),
    OUTPUT("-o"),
    REMOTE_NAME("-O"),
    HEADER_ONLY("--head"),
    USER_PWD("-u");


    public final String value;

    private Option(String option) {
        this.value = option;
    }
}