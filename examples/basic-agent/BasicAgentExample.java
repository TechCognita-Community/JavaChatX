package examples.basicagent;

import com.google.gson.JsonObject;
import com.techcognita.javachatx.JavaChatX;
import com.techcognita.javachatx.agent.EchoAgent;

/**
 * Example demonstrating how to create and use a basic agent.
 */
public class BasicAgentExample {

    public static void main(String[] args) {
        // Initialize the JavaChatX framework
        JavaChatX javaChatX = new JavaChatX();
        javaChatX.initialize();

        // Create an EchoAgent
        EchoAgent echoAgent = new EchoAgent("example-echo-1");

        // Create a test message
        JsonObject input = new JsonObject();
        input.addProperty("content", "Hello, JavaChatX!");

        // Process the message with the agent
        JsonObject response = echoAgent.process(input);

        // Print the response
        System.out.println("Agent Response: " + response.toString());

        // You can also process messages through the framework's chat engine
        JsonObject chatMessage = new JsonObject();
        chatMessage.addProperty("content", "Hello through chat engine!");
        chatMessage.addProperty("sessionId", "example-session-1");

        JsonObject chatResponse = javaChatX.processMessage(chatMessage);
        System.out.println("Chat Engine Response: " + chatResponse.toString());
    }
}