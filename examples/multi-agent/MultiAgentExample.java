package examples.multiagent;

import com.google.gson.JsonObject;
import com.techcognita.javachatx.JavaChatX;
import com.techcognita.javachatx.agent.EchoAgent;

/**
 * Example demonstrating how to use multiple agents in coordination.
 */
public class MultiAgentExample {

    public static void main(String[] args) {
        // Initialize the JavaChatX framework
        JavaChatX javaChatX = new JavaChatX();
        javaChatX.initialize();

        // Create multiple agents
        EchoAgent agent1 = new EchoAgent("multi-agent-1");
        EchoAgent agent2 = new EchoAgent("multi-agent-2");
        EchoAgent agent3 = new EchoAgent("multi-agent-3");

        // Create a test message for the first agent
        JsonObject input1 = new JsonObject();
        input1.addProperty("content", "Hello from Agent 1!");

        // Process the message with the first agent
        JsonObject response1 = agent1.process(input1);
        System.out.println("Agent 1 Response: " + response1.toString());

        // Create a test message for the second agent
        JsonObject input2 = new JsonObject();
        input2.addProperty("content", "Hello from Agent 2!");

        // Process the message with the second agent
        JsonObject response2 = agent2.process(input2);
        System.out.println("Agent 2 Response: " + response2.toString());

        // Create a test message for the third agent
        JsonObject input3 = new JsonObject();
        input3.addProperty("content", "Hello from Agent 3!");

        // Process the message with the third agent
        JsonObject response3 = agent3.process(input3);
        System.out.println("Agent 3 Response: " + response3.toString());

        // Coordinate agents through the chat engine
        JsonObject coordinatedMessage = new JsonObject();
        coordinatedMessage.addProperty("content", "Coordinated message to all agents");
        coordinatedMessage.addProperty("sessionId", "multi-agent-session-1");

        JsonObject coordinatedResponse = javaChatX.processMessage(coordinatedMessage);
        System.out.println("Coordinated Response: " + coordinatedResponse.toString());
    }
}