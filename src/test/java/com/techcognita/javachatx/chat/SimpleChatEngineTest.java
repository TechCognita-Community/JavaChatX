package com.techcognita.javachatx.chat;

import com.google.gson.JsonObject;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for the SimpleChatEngine class.
 */
public class SimpleChatEngineTest {

    @Test
    public void testProcessMessageWithContent() {
        SimpleChatEngine chatEngine = new SimpleChatEngine();

        JsonObject message = new JsonObject();
        message.addProperty("content", "Hello, Chat Engine!");
        message.addProperty("sessionId", "test-session-1");

        JsonObject response = chatEngine.processMessage(message);

        assertNotNull(response, "Response should not be null");
        assertTrue(response.has("timestamp"), "Response should have timestamp");
        assertTrue(response.has("status"), "Response should have status");
        assertTrue(response.has("response"), "Response should have response");
        assertTrue(response.has("sessionId"), "Response should have sessionId");

        assertEquals("success", response.get("status").getAsString(), "Status should be success");
        assertTrue(response.get("response").getAsString().contains("Echo: Hello, Chat Engine!"),
                "Response should contain echoed content");
        assertEquals("test-session-1", response.get("sessionId").getAsString(), "Session ID should match");
    }

    @Test
    public void testProcessMessageWithoutContent() {
        SimpleChatEngine chatEngine = new SimpleChatEngine();

        JsonObject message = new JsonObject();

        JsonObject response = chatEngine.processMessage(message);

        assertNotNull(response, "Response should not be null");
        assertTrue(response.has("timestamp"), "Response should have timestamp");
        assertTrue(response.has("status"), "Response should have status");
        assertTrue(response.has("response"), "Response should have response");

        assertEquals("success", response.get("status").getAsString(), "Status should be success");
        assertEquals("Hello from JavaChatX!", response.get("response").getAsString(),
                "Response should have default message");
    }

    @Test
    public void testSessionManagement() {
        SimpleChatEngine chatEngine = new SimpleChatEngine();

        // Register a session
        chatEngine.registerSession("test-session-2");

        // Process a message with the session
        JsonObject message = new JsonObject();
        message.addProperty("content", "Message with session");
        message.addProperty("sessionId", "test-session-2");

        JsonObject response = chatEngine.processMessage(message);

        assertTrue(response.has("sessionId"), "Response should have sessionId");
        assertEquals("test-session-2", response.get("sessionId").getAsString(), "Session ID should match");

        // Terminate the session
        chatEngine.terminateSession("test-session-2");
    }
}