package com.techcognita.javachatx.plugin;

import com.google.gson.JsonObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Base implementation of the Plugin interface.
 * Provides common functionality for all plugins in the JavaChatX framework.
 */
public abstract class BasePlugin implements Plugin {

    private static final Logger logger = LoggerFactory.getLogger(BasePlugin.class);

    protected String id;
    protected String name;
    protected JsonObject configuration;
    protected boolean ready = false;

    public BasePlugin(String id, String name) {
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
        logger.info("Configuring plugin {} with {}", name, configuration.toString());
        this.configuration = configuration;
        // Subclasses should override this method to implement specific configuration
        // logic
        // and set the ready flag appropriately
    }

    @Override
    public boolean isReady() {
        return ready;
    }

    @Override
    public String toString() {
        return String.format("Plugin{id='%s', name='%s', ready=%s}", id, name, ready);
    }
}