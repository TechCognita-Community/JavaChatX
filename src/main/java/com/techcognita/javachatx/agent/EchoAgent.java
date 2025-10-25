package com.techcognita.javachatx.agent;

import com.google.gson.JsonObject;

/**
 * A simple echo agent that repeats back any input it receives.
 * Demonstrates the basic structure of an agent implementation.
 */
public class EchoAgent extends BaseAgent {

    public EchoAgent(String id) {
        super(id, "Echo Agent");
    }

    @Override
    public JsonObject process(JsonObject input) {
        JsonObject response = new JsonObject();
        response.addProperty("agentId", getId());
        response.addProperty("agentName", getName());
        response.addProperty("timestamp", System.currentTimeMillis());

        if (input.has("content")) {
            response.addProperty("response", "Echo: " + input.get("content").getAsString());
        } else {
            response.addProperty("response", "Hello from Echo Agent!");
        }

        return response;
    }
}