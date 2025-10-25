package com.techcognita.javachatx;

import com.techcognita.javachatx.agent.Agent;
import com.techcognita.javachatx.agent.EchoAgent;
import com.techcognita.javachatx.chat.ChatEngine;
import com.techcognita.javachatx.chat.SimpleChatEngine;
import com.techcognita.javachatx.context.ContextManager;
import com.techcognita.javachatx.context.SimpleContextManager;
import com.techcognita.javachatx.plugin.PluginManager;
import com.google.gson.JsonObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Main coordinator class for the JavaChatX framework.
 * Brings together all components and provides a unified interface.
 */
public class JavaChatX {

    private static final Logger logger = LoggerFactory.getLogger(JavaChatX.class);

    private ChatEngine chatEngine;
    private ContextManager contextManager;
    private PluginManager pluginManager;
    private boolean initialized = false;

    /**
     * Initialize the JavaChatX framework with default components.
     */
    public void initialize() {
        logger.info("Initializing JavaChatX framework...");

        // Initialize core components
        chatEngine = new SimpleChatEngine();
        contextManager = new SimpleContextManager();
        pluginManager = new PluginManager();

        // Register some default plugins
        pluginManager.registerPlugin(new com.techcognita.javachatx.plugin.EchoPlugin("default-echo"));

        initialized = true;
        logger.info("JavaChatX framework initialized successfully!");
    }

    /**
     * Process a message through the framework.
     * 
     * @param message The message to process as a JSON object
     * @return The response as a JSON object
     */
    public JsonObject processMessage(JsonObject message) {
        if (!initialized) {
            throw new IllegalStateException("JavaChatX framework not initialized");
        }

        logger.debug("Processing message: {}", message.toString());
        return chatEngine.processMessage(message);
    }

    /**
     * Get the chat engine component.
     * 
     * @return The chat engine
     */
    public ChatEngine getChatEngine() {
        return chatEngine;
    }

    /**
     * Get the context manager component.
     * 
     * @return The context manager
     */
    public ContextManager getContextManager() {
        return contextManager;
    }

    /**
     * Get the plugin manager component.
     * 
     * @return The plugin manager
     */
    public PluginManager getPluginManager() {
        return pluginManager;
    }

    /**
     * Check if the framework is initialized.
     * 
     * @return true if initialized, false otherwise
     */
    public boolean isInitialized() {
        return initialized;
    }
}