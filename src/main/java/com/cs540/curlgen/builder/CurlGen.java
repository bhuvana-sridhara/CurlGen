package com.cs540.curlgen.builder;

import com.cs540.curlgen.CurlDemo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Client facing class to build Curl Command
 */
public class CurlGen {

    private final Logger logger = LoggerFactory.getLogger(CurlGen.class);

    // The CurlCommandBuilder that client needs and sends as input
    private final ICurlCommandBuilder commandBuilder;

    public CurlGen(ICurlCommandBuilder commandBuilder) {

        logger.debug(String.format("CurlGen of Builder Class %s created", commandBuilder.getClass().toString()));
        this.commandBuilder = commandBuilder;
    }

    /**
     * Client facing method to generate the Curl Command
     *
     * @return String: The Curl Command
     */
    public String GetCurlCommand() {

        logger.debug(String.format("Generating Curl Command using Builder Class %s", commandBuilder.getClass().toString()));
        return commandBuilder.GenerateCommand();
    }
}
