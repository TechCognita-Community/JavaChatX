package com.techcognita.javachatx.context;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Simple implementation of the ContextManager interface.
 * Manages session context using in-memory storage.
 */
public class SimpleContextManager implements ContextManager {

    private static final Logger logger = LoggerFactory.getLogger(SimpleContextManager.class);

    private final Map<String, Map<String, Object>> sessionContexts = new ConcurrentHashMap<>();
    private final Map<String, JsonArray> sessionHistories = new ConcurrentHashMap<>();

    @Override
    public void setContext(String sessionId, String key, Object value) {
        sessionContexts.computeIfAbsent(sessionId, k -> new ConcurrentHashMap<>())
                .put(key, value);
        logger.debug("Set context for session {}: {} = {}", sessionId, key, value);
    }

    @Override
    public Object getContext(String sessionId, String key) {
        Map<String, Object> context = sessionContexts.get(sessionId);
        if (context != null) {
            return context.get(key);
        }
        return null;
    }

    @Override
    public JsonObject getSessionContext(String sessionId) {
        JsonObject contextJson = new JsonObject();
        Map<String, Object> context = sessionContexts.get(sessionId);

        if (context != null) {
            for (Map.Entry<String, Object> entry : context.entrySet()) {
                // For simplicity, we're converting all values to strings
                // In a production implementation, you might want more sophisticated
                // serialization
                contextJson.addProperty(entry.getKey(), entry.getValue().toString());
            }
        }

        return contextJson;
    }

    @Override
    public void clearContext(String sessionId) {
        sessionContexts.remove(sessionId);
        sessionHistories.remove(sessionId);
        logger.info("Cleared context for session {}", sessionId);
    }

    @Override
    public void addToHistory(String sessionId, JsonObject message) {
        sessionHistories.computeIfAbsent(sessionId, k -> new JsonArray()).add(message);
        logger.debug("Added message to history for session {}: {}", sessionId, message.toString());
    }

    @Override
    public JsonObject getHistory(String sessionId) {
        JsonObject history = new JsonObject();
        JsonArray messages = sessionHistories.get(sessionId);

        if (messages != null) {
            history.add("messages", messages);
        } else {
            history.add("messages", new JsonArray());
        }

        return history;
    }
}