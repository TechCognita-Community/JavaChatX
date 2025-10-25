package com.techcognita.javachatx.agent;

import com.google.gson.JsonObject;

/**
 * Base interface for all AI agents in the JavaChatX framework.
 * Defines the core functionality that all agents must implement.
 */
public interface Agent {

    /**
     * Get the unique identifier for this agent.
     * 
     * @return The agent's unique ID
     */
    String getId();

    /**
     * Get the name of this agent.
     * 
     * @return The agent's name
     */
    String getName();

    /**
     * Process a task or message directed at this agent.
     * 
     * @param input The input data as a JSON object
     * @return The result of the agent's processing as a JSON object
     */
    JsonObject process(JsonObject input);

    /**
     * Configure the agent with specific settings.
     * 
     * @param configuration The configuration parameters as a JSON object
     */
    void configure(JsonObject configuration);
}