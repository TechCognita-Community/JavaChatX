package com.techcognita.javachatx.plugin;

import com.google.gson.JsonObject;

import com.techcognita.javachatx.plugin.PluginException;

/**
 * Interface for plugins that extend the functionality of JavaChatX.
 * Plugins can add new capabilities, integrations, or processing features.
 */
public interface Plugin {

    /**
     * Get the unique identifier for this plugin.
     * 
     * @return The plugin's unique ID
     */
    String getId();

    /**
     * Get the name of this plugin.
     * 
     * @return The plugin's name
     */
    String getName();

    /**
     * Execute the plugin's functionality with the provided input.
     * 
     * @param input The input data as a JSON object
     * @return The result of the plugin's execution as a JSON object
     * @throws PluginException If there's an error executing the plugin
     */
    JsonObject execute(JsonObject input) throws PluginException;

    /**
     * Configure the plugin with specific settings.
     * 
     * @param configuration The configuration parameters as a JSON object
     */
    void configure(JsonObject configuration);

    /**
     * Check if the plugin is properly configured and ready to use.
     * 
     * @return true if the plugin is ready, false otherwise
     */
    boolean isReady();
}