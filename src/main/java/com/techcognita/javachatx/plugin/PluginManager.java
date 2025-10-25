package com.techcognita.javachatx.plugin;

import com.google.gson.JsonObject;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Manages plugins in the JavaChatX framework.
 * Handles plugin registration, configuration, and execution.
 */
public class PluginManager {

    private static final Logger logger = LoggerFactory.getLogger(PluginManager.class);

    private final Map<String, Plugin> plugins = new ConcurrentHashMap<>();

    /**
     * Register a new plugin with the manager.
     * 
     * @param plugin The plugin to register
     */
    public void registerPlugin(Plugin plugin) {
        plugins.put(plugin.getId(), plugin);
        logger.info("Registered plugin: {}", plugin.getName());
    }

    /**
     * Unregister a plugin from the manager.
     * 
     * @param pluginId The ID of the plugin to unregister
     */
    public void unregisterPlugin(String pluginId) {
        Plugin removed = plugins.remove(pluginId);
        if (removed != null) {
            logger.info("Unregistered plugin: {}", removed.getName());
        }
    }

    /**
     * Get a registered plugin by its ID.
     * 
     * @param pluginId The ID of the plugin to retrieve
     * @return The plugin, or null if not found
     */
    public Plugin getPlugin(String pluginId) {
        return plugins.get(pluginId);
    }

    /**
     * Execute a plugin by its ID with the provided input.
     * 
     * @param pluginId The ID of the plugin to execute
     * @param input    The input data for the plugin
     * @return The result of the plugin execution
     * @throws PluginException If there's an error executing the plugin
     */
    public JsonObject executePlugin(String pluginId, JsonObject input) throws PluginException {
        Plugin plugin = plugins.get(pluginId);
        if (plugin == null) {
            throw new PluginException("Plugin not found: " + pluginId);
        }

        if (!plugin.isReady()) {
            throw new PluginException("Plugin not ready: " + pluginId);
        }

        logger.debug("Executing plugin {} with input: {}", pluginId, input.toString());
        return plugin.execute(input);
    }

    /**
     * Get the number of registered plugins.
     * 
     * @return The number of registered plugins
     */
    public int getPluginCount() {
        return plugins.size();
    }
}