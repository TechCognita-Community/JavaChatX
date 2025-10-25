package com.techcognita.javachatx.plugin;

import com.google.gson.JsonObject;

/**
 * A simple echo plugin for demonstration purposes.
 * This plugin simply echoes back the input as the output.
 */
public class EchoPlugin extends BasePlugin {

    public EchoPlugin(String id) {
        super(id, "Echo Plugin");
    }

    @Override
    public JsonObject execute(JsonObject input) throws PluginException {
        try {
            JsonObject response = new JsonObject();
            response.addProperty("pluginId", getId());
            response.addProperty("pluginName", getName());
            response.addProperty("timestamp", System.currentTimeMillis());

            if (input.has("content")) {
                response.addProperty("result", "Echo: " + input.get("content").getAsString());
            } else {
                response.addProperty("result", "Hello from Echo Plugin!");
            }

            return response;
        } catch (Exception e) {
            throw new PluginException("Error executing EchoPlugin", e);
        }
    }

    @Override
    public void configure(JsonObject configuration) {
        super.configure(configuration);
        // For this simple example, we'll just mark it as ready after configuration
        this.ready = true;
    }
}