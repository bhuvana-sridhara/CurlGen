package com.cs540.curlgen.models;

public enum Option {
    AGENT("-A"),
    COOKIENAME("-b"),
    COOKIEJAR("-c"),
    COMPRESSED("--compressed"),
    TIMEOUT("--connect-timeout"),
    DATA("-d"),
    DATABINARY("--data-binary"),
    FORM("-F"),
    HEADER("-H"),
    INSECURE("-k"),
    MAXTIME("-m"),
    METHOD("-X"),
    OUTPUT("-o"),
    REMOTENAME("-O"),
    HEADERONLY("--head"),
    USERPWD("-u");





    public final String value;

    private Option(String option) {
        this.value = option;
    }
}