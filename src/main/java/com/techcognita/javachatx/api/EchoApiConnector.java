package com.techcognita.javachatx.api;

import com.google.gson.JsonObject;

/**
 * A simple echo API connector for demonstration purposes.
 * This connector simply echoes back the request as the response.
 */
public class EchoApiConnector extends BaseApiConnector {

    public EchoApiConnector() {
        super("EchoAPI");
    }

    @Override
    public JsonObject sendRequest(JsonObject request) throws ApiException {
        JsonObject response = new JsonObject();
        response.addProperty("connector", getName());
        response.addProperty("timestamp", System.currentTimeMillis());

        if (request.has("content")) {
            response.addProperty("response", "Echo: " + request.get("content").getAsString());
        } else {
            response.addProperty("response", "Hello from Echo API!");
        }

        return response;
    }

    @Override
    public void configure(JsonObject configuration) {
        super.configure(configuration);
        // For this simple example, we'll just mark it as ready after configuration
        this.ready = true;
    }
}