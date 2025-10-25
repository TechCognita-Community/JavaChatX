package examples.customplugin;

import com.google.gson.JsonObject;
import com.techcognita.javachatx.JavaChatX;
import com.techcognita.javachatx.plugin.EchoPlugin;
import com.techcognita.javachatx.plugin.PluginException;

/**
 * Example demonstrating how to create and use a custom plugin.
 */
public class CustomPluginExample {

    public static void main(String[] args) {
        // Initialize the JavaChatX framework
        JavaChatX javaChatX = new JavaChatX();
        javaChatX.initialize();

        // Create an EchoPlugin
        EchoPlugin echoPlugin = new EchoPlugin("example-plugin-1");

        // Configure the plugin
        JsonObject config = new JsonObject();
        echoPlugin.configure(config);

        // Register the plugin with the plugin manager
        javaChatX.getPluginManager().registerPlugin(echoPlugin);

        // Create a test input
        JsonObject input = new JsonObject();
        input.addProperty("content", "Hello, Plugin!");

        try {
            // Execute the plugin
            JsonObject result = javaChatX.getPluginManager().executePlugin("example-plugin-1", input);

            // Print the result
            System.out.println("Plugin Result: " + result.toString());
        } catch (PluginException e) {
            System.err.println("Error executing plugin: " + e.getMessage());
            e.printStackTrace();
        }
    }
}