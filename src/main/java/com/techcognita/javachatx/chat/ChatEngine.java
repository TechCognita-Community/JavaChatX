package com.techcognita.javachatx.chat;

import com.google.gson.JsonObject;

/**
 * Core chat engine for handling message processing in JavaChatX.
 * Manages the flow of messages between users and AI agents.
 */
public interface ChatEngine {

    /**
     * Process an incoming message and generate a response.
     * 
     * @param message The incoming message as a JSON object
     * @return The response message as a JSON object
     */
    JsonObject processMessage(JsonObject message);

    /**
     * Register a new conversation session.
     * 
     * @param sessionId Unique identifier for the conversation
     */
    void registerSession(String sessionId);

    /**
     * Terminate a conversation session.
     * 
     * @param sessionId Unique identifier for the conversation
     */
    void terminateSession(String sessionId);
}