package com.techcognita.javachatx;

import com.google.gson.JsonObject;

/**
 * Main entry point for the JavaChatX framework.
 * This class initializes the core components of the AI agent development
 * framework.
 */
public class JavaChatXApplication {

    public static void main(String[] args) {
        System.out.println("JavaChatX - AI Agent Development Framework");
        System.out.println("Initializing...");

        // Initialize the framework
        JavaChatX javaChatX = new JavaChatX();
        javaChatX.initialize();

        // Test with a simple message
        JsonObject testMessage = new JsonObject();
        testMessage.addProperty("content", "Hello, JavaChatX!");
        testMessage.addProperty("sessionId", "test-session-1");

        JsonObject response = javaChatX.processMessage(testMessage);
        System.out.println("Response: " + response.toString());

        System.out.println("Ready for agent development!");
    }
}