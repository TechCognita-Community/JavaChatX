package com.techcognita.javachatx.context;

import com.google.gson.JsonObject;
import java.util.Map;

/**
 * Manages conversation context and state for AI agents.
 * Handles session data, conversation history, and contextual information.
 */
public interface ContextManager {

    /**
     * Store context data for a session.
     * 
     * @param sessionId The session identifier
     * @param key       The context key
     * @param value     The context value
     */
    void setContext(String sessionId, String key, Object value);

    /**
     * Retrieve context data for a session.
     * 
     * @param sessionId The session identifier
     * @param key       The context key
     * @return The context value, or null if not found
     */
    Object getContext(String sessionId, String key);

    /**
     * Get all context data for a session as a JSON object.
     * 
     * @param sessionId The session identifier
     * @return All context data for the session
     */
    JsonObject getSessionContext(String sessionId);

    /**
     * Clear context data for a session.
     * 
     * @param sessionId The session identifier
     */
    void clearContext(String sessionId);

    /**
     * Add a message to the conversation history.
     * 
     * @param sessionId The session identifier
     * @param message   The message to add to history
     */
    void addToHistory(String sessionId, JsonObject message);

    /**
     * Get the conversation history for a session.
     * 
     * @param sessionId The session identifier
     * @return The conversation history
     */
    JsonObject getHistory(String sessionId);
}