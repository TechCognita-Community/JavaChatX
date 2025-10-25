package examples;

import com.google.gson.JsonObject;
import com.techcognita.javachatx.JavaChatX;
import com.techcognita.javachatx.agent.EchoAgent;
import com.techcognita.javachatx.plugin.EchoPlugin;
import com.techcognita.javachatx.plugin.PluginException;

/**
 * Comprehensive example demonstrating the complete JavaChatX workflow.
 */
public class CompleteExample {

    public static void main(String[] args) {
        System.out.println("=== JavaChatX Comprehensive Example ===\n");

        // 1. Initialize the JavaChatX framework
        System.out.println("1. Initializing JavaChatX framework...");
        JavaChatX javaChatX = new JavaChatX();
        javaChatX.initialize();
        System.out.println("   Framework initialized successfully!\n");

        // 2. Create and configure agents
        System.out.println("2. Creating and configuring agents...");
        EchoAgent echoAgent = new EchoAgent("comprehensive-echo-1");
        System.out.println("   Created EchoAgent: " + echoAgent.getName() + " (ID: " + echoAgent.getId() + ")\n");

        // 3. Create and configure plugins
        System.out.println("3. Creating and configuring plugins...");
        EchoPlugin echoPlugin = new EchoPlugin("comprehensive-plugin-1");
        JsonObject pluginConfig = new JsonObject();
        echoPlugin.configure(pluginConfig);
        javaChatX.getPluginManager().registerPlugin(echoPlugin);
        System.out.println("   Created and registered EchoPlugin: " + echoPlugin.getName() + " (ID: "
                + echoPlugin.getId() + ")\n");

        // 4. Use the chat engine
        System.out.println("4. Using the chat engine...");
        JsonObject chatMessage = new JsonObject();
        chatMessage.addProperty("content", "Hello, JavaChatX Chat Engine!");
        chatMessage.addProperty("sessionId", "comprehensive-session-1");

        JsonObject chatResponse = javaChatX.processMessage(chatMessage);
        System.out.println("   Chat Engine Response: " + chatResponse.get("response").getAsString() + "\n");

        // 5. Use agents directly
        System.out.println("5. Using agents directly...");
        JsonObject agentInput = new JsonObject();
        agentInput.addProperty("content", "Hello, Echo Agent!");

        JsonObject agentResponse = echoAgent.process(agentInput);
        System.out.println("   Agent Response: " + agentResponse.get("response").getAsString() + "\n");

        // 6. Use plugins
        System.out.println("6. Using plugins...");
        JsonObject pluginInput = new JsonObject();
        pluginInput.addProperty("content", "Hello, Echo Plugin!");

        try {
            JsonObject pluginResult = javaChatX.getPluginManager().executePlugin("comprehensive-plugin-1", pluginInput);
            System.out.println("   Plugin Result: " + pluginResult.get("result").getAsString() + "\n");
        } catch (PluginException e) {
            System.err.println("   Error executing plugin: " + e.getMessage());
        }

        // 7. Demonstrate context management
        System.out.println("7. Demonstrating context management...");
        String sessionId = "comprehensive-session-1";
        javaChatX.getContextManager().setContext(sessionId, "user", "Java Developer");
        javaChatX.getContextManager().setContext(sessionId, "topic", "AI Frameworks");

        JsonObject context = javaChatX.getContextManager().getSessionContext(sessionId);
        System.out.println("   Session Context: " + context.toString() + "\n");

        // 8. Summary
        System.out.println("8. Summary");
        System.out.println("   - Framework initialized successfully");
        System.out.println("   - Agent created and used");
        System.out.println("   - Plugin created, registered, and executed");
        System.out.println("   - Chat engine processed messages");
        System.out.println("   - Context management demonstrated");
        System.out.println("\n=== Example completed successfully! ===");
    }
}