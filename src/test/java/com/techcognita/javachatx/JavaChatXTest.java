package com.techcognita.javachatx;

import com.google.gson.JsonObject;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for the JavaChatX framework.
 */
public class JavaChatXTest {

    private JavaChatX javaChatX;

    @BeforeEach
    public void setUp() {
        javaChatX = new JavaChatX();
        javaChatX.initialize();
    }

    @Test
    public void testInitialization() {
        assertTrue(javaChatX.isInitialized(), "Framework should be initialized");
        assertNotNull(javaChatX.getChatEngine(), "Chat engine should not be null");
        assertNotNull(javaChatX.getContextManager(), "Context manager should not be null");
        assertNotNull(javaChatX.getPluginManager(), "Plugin manager should not be null");
    }

    @Test
    public void testProcessMessage() {
        JsonObject testMessage = new JsonObject();
        testMessage.addProperty("content", "Hello, JavaChatX!");
        testMessage.addProperty("sessionId", "test-session-1");

        JsonObject response = javaChatX.processMessage(testMessage);

        assertNotNull(response, "Response should not be null");
        assertTrue(response.has("timestamp"), "Response should have a timestamp");
        assertTrue(response.has("status"), "Response should have a status");
        assertTrue(response.has("response"), "Response should have a response");
    }

    @Test
    public void testPluginManager() {
        assertNotNull(javaChatX.getPluginManager(), "Plugin manager should not be null");
        assertTrue(javaChatX.getPluginManager().getPluginCount() > 0, "Should have at least one plugin registered");
    }
}