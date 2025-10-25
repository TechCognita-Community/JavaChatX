package com.techcognita.javachatx.plugin;

import com.google.gson.JsonObject;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for the PluginManager class.
 */
public class PluginManagerTest {

    @Test
    public void testPluginRegistration() {
        PluginManager pluginManager = new PluginManager();

        EchoPlugin plugin = new EchoPlugin("test-plugin-1");
        pluginManager.registerPlugin(plugin);

        assertEquals(1, pluginManager.getPluginCount(), "Should have one plugin registered");

        Plugin retrieved = pluginManager.getPlugin("test-plugin-1");
        assertNotNull(retrieved, "Should retrieve the registered plugin");
        assertEquals(plugin, retrieved, "Retrieved plugin should be the same as registered");
    }

    @Test
    public void testPluginUnregistration() {
        PluginManager pluginManager = new PluginManager();

        EchoPlugin plugin = new EchoPlugin("test-plugin-2");
        pluginManager.registerPlugin(plugin);

        assertEquals(1, pluginManager.getPluginCount(), "Should have one plugin registered");

        pluginManager.unregisterPlugin("test-plugin-2");
        assertEquals(0, pluginManager.getPluginCount(), "Should have no plugins registered after unregistration");

        Plugin retrieved = pluginManager.getPlugin("test-plugin-2");
        assertNull(retrieved, "Should not retrieve unregistered plugin");
    }

    @Test
    public void testPluginExecution() throws PluginException {
        PluginManager pluginManager = new PluginManager();

        EchoPlugin plugin = new EchoPlugin("test-plugin-3");
        plugin.configure(new JsonObject()); // Configure to make it ready
        pluginManager.registerPlugin(plugin);

        JsonObject input = new JsonObject();
        input.addProperty("content", "Hello, Plugin!");

        JsonObject result = pluginManager.executePlugin("test-plugin-3", input);

        assertNotNull(result, "Result should not be null");
        assertTrue(result.has("pluginId"), "Result should have pluginId");
        assertTrue(result.has("pluginName"), "Result should have pluginName");
        assertTrue(result.has("timestamp"), "Result should have timestamp");
        assertTrue(result.has("result"), "Result should have result");

        assertEquals("test-plugin-3", result.get("pluginId").getAsString(), "Plugin ID should match");
        assertEquals("Echo Plugin", result.get("pluginName").getAsString(), "Plugin name should match");
        assertTrue(result.get("result").getAsString().contains("Echo: Hello, Plugin!"),
                "Result should contain echoed content");
    }

    @Test
    public void testPluginExecutionWithNonExistentPlugin() {
        PluginManager pluginManager = new PluginManager();

        JsonObject input = new JsonObject();

        assertThrows(PluginException.class, () -> {
            pluginManager.executePlugin("non-existent-plugin", input);
        }, "Should throw PluginException for non-existent plugin");
    }
}