package com.techcognita.javachatx.chat;

import com.google.gson.JsonObject;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Simple implementation of the ChatEngine interface.
 * Provides basic message handling and session management.
 */
public class SimpleChatEngine implements ChatEngine {

    private static final Logger logger = LoggerFactory.getLogger(SimpleChatEngine.class);

    private final Map<String, Object> sessions = new ConcurrentHashMap<>();

    @Override
    public JsonObject processMessage(JsonObject message) {
        logger.info("Processing message: {}", message.toString());

        JsonObject response = new JsonObject();
        response.addProperty("timestamp", System.currentTimeMillis());
        response.addProperty("status", "success");

        // Extract message content
        if (message.has("content")) {
            String content = message.get("content").getAsString();
            response.addProperty("response", "Echo: " + content);
        } else {
            response.addProperty("response", "Hello from JavaChatX!");
        }

        // Add session info if present
        if (message.has("sessionId")) {
            response.addProperty("sessionId", message.get("sessionId").getAsString());
        }

        return response;
    }

    @Override
    public void registerSession(String sessionId) {
        logger.info("Registering session: {}", sessionId);
        sessions.put(sessionId, new Object());
    }

    @Override
    public void terminateSession(String sessionId) {
        logger.info("Terminating session: {}", sessionId);
        sessions.remove(sessionId);
    }
}