package examples.contextagent;

import com.google.gson.JsonObject;
import com.techcognita.javachatx.JavaChatX;
import com.techcognita.javachatx.context.ContextManager;

/**
 * Example demonstrating how to use the context management system.
 */
public class ContextAgentExample {

    public static void main(String[] args) {
        // Initialize the JavaChatX framework
        JavaChatX javaChatX = new JavaChatX();
        javaChatX.initialize();

        // Get the context manager
        ContextManager contextManager = javaChatX.getContextManager();

        // Set up a session
        String sessionId = "context-example-session-1";

        // Store some context data
        contextManager.setContext(sessionId, "userName", "John Doe");
        contextManager.setContext(sessionId, "userPreference", "darkMode");

        // Add messages to history
        JsonObject message1 = new JsonObject();
        message1.addProperty("content", "Hello, JavaChatX!");
        message1.addProperty("sender", "user");
        contextManager.addToHistory(sessionId, message1);

        JsonObject message2 = new JsonObject();
        message2.addProperty("content", "Hello, John! How can I help you today?");
        message2.addProperty("sender", "agent");
        contextManager.addToHistory(sessionId, message2);

        // Retrieve context data
        String userName = (String) contextManager.getContext(sessionId, "userName");
        System.out.println("User Name: " + userName);

        String userPreference = (String) contextManager.getContext(sessionId, "userPreference");
        System.out.println("User Preference: " + userPreference);

        // Get session context as JSON
        JsonObject sessionContext = contextManager.getSessionContext(sessionId);
        System.out.println("Session Context: " + sessionContext.toString());

        // Get conversation history
        JsonObject history = contextManager.getHistory(sessionId);
        System.out.println("Conversation History: " + history.toString());

        // Process a message through the chat engine
        JsonObject chatMessage = new JsonObject();
        chatMessage.addProperty("content", "What's my name?");
        chatMessage.addProperty("sessionId", sessionId);

        JsonObject chatResponse = javaChatX.processMessage(chatMessage);
        System.out.println("Chat Response: " + chatResponse.toString());
    }
}