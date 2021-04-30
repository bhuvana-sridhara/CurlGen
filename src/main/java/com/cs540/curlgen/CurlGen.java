package com.cs540.curlgen;

public class CurlGen {

    private ICurlCommandBuilder commandBuilder;

    public CurlGen(ICurlCommandBuilder commandBuilder) {
        this.commandBuilder = commandBuilder;
    }

    public String GetCurlCommand() {
        return commandBuilder.GenerateCommand();
    }
}
