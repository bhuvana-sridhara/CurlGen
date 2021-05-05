package com.cs540.curlgen.builder;

/**
 * Curl Command Builder interface exposing the primary methods to be implemented by any builder
 */
public interface ICurlCommandBuilder {
    String GenerateCommand();
}
