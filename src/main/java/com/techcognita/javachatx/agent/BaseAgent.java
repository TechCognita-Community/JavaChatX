package com.techcognita.javachatx.agent;

import com.google.gson.JsonObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Base implementation of the Agent interface.
 * Provides common functionality for all agents in the JavaChatX framework.
 */
public abstract class BaseAgent implements Agent {

    private static final Logger logger = LoggerFactory.getLogger(BaseAgent.class);

    protected String id;
    protected String name;
    protected JsonObject configuration;

    public BaseAgent(String id, String name) {
        this.id = id;
        this.name = name;
        this.configuration = new JsonObject();
    }

    @Override
    public String getId() {
        return id;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void configure(JsonObject configuration) {
        logger.info("Configuring agent {} with {}", name, configuration.toString());
        this.configuration = configuration;
    }

    @Override
    public String toString() {
        return String.format("Agent{id='%s', name='%s'}", id, name);
    }
}