# Agent Development Guide

This guide explains how to create custom agents in JavaChatX.

## Understanding Agents

In JavaChatX, an agent is a component that can process input and generate responses. Agents can be simple (like the provided EchoAgent) or complex (integrating with AI services like OpenAI).

## Creating a Custom Agent

To create a custom agent, you have two options:

1. Extend the `BaseAgent` abstract class (recommended)
2. Implement the `Agent` interface directly

### Extending BaseAgent

```java
public class MyCustomAgent extends BaseAgent {
    
    public MyCustomAgent(String id) {
        super(id, "My Custom Agent");
    }
    
    @Override
    public JsonObject process(JsonObject input) {
        JsonObject response = new JsonObject();
        response.addProperty("agentId", getId());
        response.addProperty("agentName", getName());
        response.addProperty("timestamp", System.currentTimeMillis());
        
        // Add your custom logic here
        response.addProperty("response", "Hello from MyCustomAgent!");
        
        return response;
    }
}
```

### Implementing the Agent Interface Directly

```java
public class MyCustomAgent implements Agent {
    
    private String id;
    private String name;
    
    public MyCustomAgent(String id) {
        this.id = id;
        this.name = "My Custom Agent";
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
    public JsonObject process(JsonObject input) {
        JsonObject response = new JsonObject();
        response.addProperty("agentId", id);
        response.addProperty("agentName", name);
        response.addProperty("timestamp", System.currentTimeMillis());
        
        // Add your custom logic here
        response.addProperty("response", "Hello from MyCustomAgent!");
        
        return response;
    }
    
    @Override
    public void configure(JsonObject configuration) {
        // Implement configuration logic
    }
}
```

## Agent Factory

Use the `AgentFactory` to create instances of agents:

```java
// Create an instance of your custom agent
MyCustomAgent agent = new MyCustomAgent("custom-1");

// Or use the factory for standard agents
EchoAgent echoAgent = AgentFactory.createEchoAgent("echo-1");
```

## Agent Configuration

Agents can be configured using the `configure` method:

```java
JsonObject config = new JsonObject();
config.addProperty("model", "gpt-4");
config.addProperty("temperature", 0.7);

agent.configure(config);
```

## Best Practices

1. **Use Descriptive Names**: Give your agents clear, descriptive names
2. **Handle Errors Gracefully**: Implement proper error handling in your agents
3. **Keep Agents Focused**: Each agent should have a single, well-defined purpose
4. **Use Logging**: Utilize the logging framework for debugging and monitoring
5. **Test Thoroughly**: Write unit tests for your agents

## Example Agents

### Echo Agent
The simplest agent that echoes back the input:

```java
EchoAgent agent = new EchoAgent("echo-1");
JsonObject input = new JsonObject();
input.addProperty("content", "Hello, World!");
JsonObject response = agent.process(input);
```

### OpenAI Integration Agent (Conceptual)
An agent that integrates with OpenAI's API:

```java
public class OpenAIAgent extends BaseAgent {
    
    private OpenAIConnector openAIConnector;
    
    public OpenAIAgent(String id) {
        super(id, "OpenAI Agent");
        this.openAIConnector = new OpenAIConnector();
    }
    
    @Override
    public JsonObject process(JsonObject input) {
        // Format input for OpenAI
        JsonObject openAIRequest = new JsonObject();
        openAIRequest.addProperty("prompt", input.get("content").getAsString());
        
        try {
            // Send request to OpenAI
            JsonObject openAIResponse = openAIConnector.sendRequest(openAIRequest);
            
            // Format response
            JsonObject response = new JsonObject();
            response.addProperty("agentId", getId());
            response.addProperty("response", openAIResponse.get("text").getAsString());
            
            return response;
        } catch (ApiException e) {
            JsonObject errorResponse = new JsonObject();
            errorResponse.addProperty("agentId", getId());
            errorResponse.addProperty("error", "Failed to communicate with OpenAI");
            return errorResponse;
        }
    }
}
```

## Testing Agents

Create unit tests for your agents to ensure they work correctly:

```java
@Test
public void testCustomAgent() {
    MyCustomAgent agent = new MyCustomAgent("test-1");
    
    JsonObject input = new JsonObject();
    input.addProperty("content", "Test input");
    
    JsonObject response = agent.process(input);
    
    assertNotNull(response);
    assertTrue(response.has("response"));
}
```

## Next Steps

1. Explore the [Plugin Development Guide](PLUGIN_DEVELOPMENT.md) to extend functionality
2. Learn about [API Integration](API_INTEGRATION.md) to connect with external services
3. Check out the [examples](../examples/) directory for more agent implementations