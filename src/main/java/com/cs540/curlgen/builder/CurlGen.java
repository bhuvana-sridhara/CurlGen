package com.cs540.curlgen.builder;

/**
 * Client facing class to build Curl Command
 */
public class CurlGen {

    // The CurlCommandBuilder that client needs and sends as input
    private ICurlCommandBuilder commandBuilder;

    public CurlGen(ICurlCommandBuilder commandBuilder) {
        this.commandBuilder = commandBuilder;
    }

    /**
     * Client facing method to generate the Curl Command
     *
     * @return String: The Curl Command
     */
    public String GetCurlCommand() {
        return commandBuilder.GenerateCommand();
    }
}
