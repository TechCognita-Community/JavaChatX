package com.techcognita.javachatx.api;

import com.google.gson.JsonObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Base implementation of the ApiConnector interface.
 * Provides common functionality for all API connectors.
 */
public abstract class BaseApiConnector implements ApiConnector {

    private static final Logger logger = LoggerFactory.getLogger(BaseApiConnector.class);

    protected String name;
    protected JsonObject configuration;
    protected boolean ready = false;

    public BaseApiConnector(String name) {
        this.name = name;
        this.configuration = new JsonObject();
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public boolean isReady() {
        return ready;
    }

    @Override
    public void configure(JsonObject configuration) {
        logger.info("Configuring API connector {} with {}", name, configuration.toString());
        this.configuration = configuration;
        // Subclasses should override this method to implement specific configuration
        // logic
        // and set the ready flag appropriately
    }

    @Override
    public String toString() {
        return String.format("ApiConnector{name='%s', ready=%s}", name, ready);
    }
}