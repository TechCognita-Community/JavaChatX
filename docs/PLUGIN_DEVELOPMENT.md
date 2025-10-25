# Plugin Development Guide

This guide explains how to create custom plugins for JavaChatX.

## Understanding Plugins

Plugins in JavaChatX are modular extensions that add functionality to the framework. They can perform various tasks such as data processing, external API integration, or custom business logic.

## Creating a Custom Plugin

To create a custom plugin, you have two options:

1. Extend the `BasePlugin` abstract class (recommended)
2. Implement the `Plugin` interface directly

### Extending BasePlugin

```java
public class MyCustomPlugin extends BasePlugin {
    
    public MyCustomPlugin(String id) {
        super(id, "My Custom Plugin");
    }
    
    @Override
    public JsonObject execute(JsonObject input) throws PluginException {
        try {
            JsonObject result = new JsonObject();
            result.addProperty("pluginId", getId());
            result.addProperty("pluginName", getName());
            result.addProperty("timestamp", System.currentTimeMillis());
            
            // Add your custom logic here
            result.addProperty("result", "Processed by MyCustomPlugin");
            
            return result;
        } catch (Exception e) {
            throw new PluginException("Error executing plugin", e);
        }
    }
    
    @Override
    public void configure(JsonObject configuration) {
        super.configure(configuration);
        // Add your configuration logic here
        this.ready = true;
    }
}
```

### Implementing the Plugin Interface Directly

```java
public class MyCustomPlugin implements Plugin {
    
    private String id;
    private String name;
    private JsonObject configuration;
    private boolean ready = false;
    
    public MyCustomPlugin(String id) {
        this.id = id;
        this.name = "My Custom Plugin";
        this.configuration = new JsonObject();
    }
    
    @Override
    public String getId() {
        return id;
    }
    
    @Override
    public String getName() {
        return name;
    }
    
    @Override
    public JsonObject execute(JsonObject input) throws PluginException {
        try {
            JsonObject result = new JsonObject();
            result.addProperty("pluginId", id);
            result.addProperty("pluginName", name);
            result.addProperty("timestamp", System.currentTimeMillis());
            
            // Add your custom logic here
            result.addProperty("result", "Processed by MyCustomPlugin");
            
            return result;
        } catch (Exception e) {
            throw new PluginException("Error executing plugin", e);
        }
    }
    
    @Override
    public void configure(JsonObject configuration) {
        this.configuration = configuration;
        // Add your configuration logic here
        this.ready = true;
    }
    
    @Override
    public boolean isReady() {
        return ready;
    }
}
```

## Registering Plugins

Plugins need to be registered with the `PluginManager` to be used:

```java
// Create an instance of your plugin
MyCustomPlugin plugin = new MyCustomPlugin("custom-plugin-1");

// Configure the plugin
JsonObject config = new JsonObject();
config.addProperty("apiKey", "your-api-key");
plugin.configure(config);

// Register with the plugin manager
JavaChatX javaChatX = new JavaChatX();
javaChatX.initialize();
javaChatX.getPluginManager().registerPlugin(plugin);

// Execute the plugin
JsonObject input = new JsonObject();
input.addProperty("content", "Test input");
JsonObject result = javaChatX.getPluginManager().executePlugin("custom-plugin-1", input);
```

## Plugin Configuration

Plugins can be configured using the `configure` method:

```java
JsonObject config = new JsonObject();
config.addProperty("setting1", "value1");
config.addProperty("setting2", "value2");

plugin.configure(config);
```

## Best Practices

1. **Use Descriptive Names**: Give your plugins clear, descriptive names
2. **Handle Errors Gracefully**: Implement proper error handling in your plugins
3. **Keep Plugins Focused**: Each plugin should have a single, well-defined purpose
4. **Use Logging**: Utilize the logging framework for debugging and monitoring
5. **Test Thoroughly**: Write unit tests for your plugins
6. **Document Configuration**: Clearly document what configuration options your plugin accepts

## Example Plugins

### Echo Plugin
The simplest plugin that echoes back the input:

```java
EchoPlugin plugin = new EchoPlugin("echo-plugin-1");
JsonObject input = new JsonObject();
input.addProperty("content", "Hello, Plugin!");
JsonObject result = plugin.execute(input);
```

### Data Processing Plugin (Conceptual)
A plugin that processes data:

```java
public class DataProcessorPlugin extends BasePlugin {
    
    public DataProcessorPlugin(String id) {
        super(id, "Data Processor Plugin");
    }
    
    @Override
    public JsonObject execute(JsonObject input) throws PluginException {
        try {
            // Process the input data
            JsonArray inputData = input.getAsJsonArray("data");
            JsonArray processedData = new JsonArray();
            
            for (JsonElement element : inputData) {
                // Apply some processing logic
                JsonObject processedItem = new JsonObject();
                processedItem.addProperty("original", element.getAsString());
                processedItem.addProperty("processed", element.getAsString().toUpperCase());
                processedData.add(processedItem);
            }
            
            JsonObject result = new JsonObject();
            result.add("processedData", processedData);
            
            return result;
        } catch (Exception e) {
            throw new PluginException("Error processing data", e);
        }
    }
}
```

## Testing Plugins

Create unit tests for your plugins to ensure they work correctly:

```java
@Test
public void testCustomPlugin() throws PluginException {
    MyCustomPlugin plugin = new MyCustomPlugin("test-plugin-1");
    
    JsonObject config = new JsonObject();
    plugin.configure(config);
    
    JsonObject input = new JsonObject();
    input.addProperty("content", "Test input");
    
    JsonObject result = plugin.execute(input);
    
    assertNotNull(result);
    assertTrue(result.has("result"));
}
```

## Plugin Distribution

To distribute your plugin:

1. Package it as a JAR file
2. Publish it to a Maven repository or share it directly
3. Document how to install and configure it
4. Provide example usage code

## Next Steps

1. Explore the [Agent Development Guide](AGENT_GUIDE.md) to create custom agents
2. Learn about [API Integration](API_INTEGRATION.md) to connect with external services
3. Check out the [examples](../examples/) directory for more plugin implementations