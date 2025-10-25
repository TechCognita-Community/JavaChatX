package com.techcognita.javachatx.agent;

import com.google.gson.JsonObject;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for the EchoAgent class.
 */
public class EchoAgentTest {

    @Test
    public void testEchoAgentCreation() {
        EchoAgent agent = new EchoAgent("test-echo-1");

        assertEquals("test-echo-1", agent.getId(), "Agent ID should match");
        assertEquals("Echo Agent", agent.getName(), "Agent name should match");
    }

    @Test
    public void testEchoAgentProcess() {
        EchoAgent agent = new EchoAgent("test-echo-2");

        JsonObject input = new JsonObject();
        input.addProperty("content", "Hello, Echo Agent!");

        JsonObject response = agent.process(input);

        assertNotNull(response, "Response should not be null");
        assertTrue(response.has("agentId"), "Response should have agentId");
        assertTrue(response.has("agentName"), "Response should have agentName");
        assertTrue(response.has("timestamp"), "Response should have timestamp");
        assertTrue(response.has("response"), "Response should have response");

        assertEquals("test-echo-2", response.get("agentId").getAsString(), "Agent ID should match");
        assertEquals("Echo Agent", response.get("agentName").getAsString(), "Agent name should match");
        assertTrue(response.get("response").getAsString().contains("Echo: Hello, Echo Agent!"),
                "Response should contain echoed content");
    }

    @Test
    public void testEchoAgentProcessWithoutContent() {
        EchoAgent agent = new EchoAgent("test-echo-3");

        JsonObject input = new JsonObject();

        JsonObject response = agent.process(input);

        assertNotNull(response, "Response should not be null");
        assertTrue(response.has("response"), "Response should have response");
        assertEquals("Hello from Echo Agent!", response.get("response").getAsString(),
                "Response should have default message");
    }
}