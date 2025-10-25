# API Integration Guide

This guide explains how to integrate external AI services and APIs with JavaChatX.

## Understanding API Connectors

API connectors in JavaChatX provide a standardized way to communicate with external AI services like OpenAI, Google Gemini, Hugging Face, etc. They handle authentication, request formatting, and response parsing.

## Creating a Custom API Connector

To create a custom API connector, you have two options:

1. Extend the `BaseApiConnector` abstract class (recommended)
2. Implement the `ApiConnector` interface directly

### Extending BaseApiConnector

```java
public class MyCustomApiConnector extends BaseApiConnector {
    
    private String apiKey;
    private String baseUrl;
    
    public MyCustomApiConnector() {
        super("MyCustomAPI");
    }
    
    @Override
    public JsonObject sendRequest(JsonObject request) throws ApiException {
        try {
            // Format the request for the external API
            JsonObject formattedRequest = formatRequest(request);
            
            // Send HTTP request to the external API
            JsonObject response = sendHttpRequest(formattedRequest);
            
            // Parse and format the response
            return parseResponse(response);
        } catch (Exception e) {
            throw new ApiException("Error communicating with MyCustomAPI", e);
        }
    }
    
    @Override
    public void configure(JsonObject configuration) {
        super.configure(configuration);
        
        if (configuration.has("apiKey")) {
            this.apiKey = configuration.get("apiKey").getAsString();
        }
        
        if (configuration.has("baseUrl")) {
            this.baseUrl = configuration.get("baseUrl").getAsString();
        }
        
        // Validate configuration
        if (this.apiKey != null && !this.apiKey.isEmpty()) {
            this.ready = true;
        }
    }
    
    private JsonObject formatRequest(JsonObject request) {
        // Implement request formatting logic
        JsonObject formatted = new JsonObject();
        formatted.addProperty("prompt", request.get("content").getAsString());
        return formatted;
    }
    
    private JsonObject sendHttpRequest(JsonObject formattedRequest) throws Exception {
        // Implement HTTP request logic
        // This is a simplified example
        JsonObject response = new JsonObject();
        response.addProperty("text", "Response from MyCustomAPI");
        return response;
    }
    
    private JsonObject parseResponse(JsonObject apiResponse) {
        // Implement response parsing logic
        JsonObject parsed = new JsonObject();
        parsed.addProperty("response", apiResponse.get("text").getAsString());
        return parsed;
    }
}
```

### Implementing the ApiConnector Interface Directly

```java
public class MyCustomApiConnector implements ApiConnector {
    
    private String name;
    private JsonObject configuration;
    private boolean ready = false;
    private String apiKey;
    private String baseUrl;
    
    public MyCustomApiConnector() {
        this.name = "MyCustomAPI";
        this.configuration = new JsonObject();
    }
    
    @Override
    public String getName() {
        return name;
    }
    
    @Override
    public JsonObject sendRequest(JsonObject request) throws ApiException {
        try {
            // Format the request for the external API
            JsonObject formattedRequest = formatRequest(request);
            
            // Send HTTP request to the external API
            JsonObject response = sendHttpRequest(formattedRequest);
            
            // Parse and format the response
            return parseResponse(response);
        } catch (Exception e) {
            throw new ApiException("Error communicating with MyCustomAPI", e);
        }
    }
    
    @Override
    public boolean isReady() {
        return ready;
    }
    
    @Override
    public void configure(JsonObject configuration) {
        this.configuration = configuration;
        
        if (configuration.has("apiKey")) {
            this.apiKey = configuration.get("apiKey").getAsString();
        }
        
        if (configuration.has("baseUrl")) {
            this.baseUrl = configuration.get("baseUrl").getAsString();
        }
        
        // Validate configuration
        if (this.apiKey != null && !this.apiKey.isEmpty()) {
            this.ready = true;
        }
    }
    
    private JsonObject formatRequest(JsonObject request) {
        // Implement request formatting logic
        JsonObject formatted = new JsonObject();
        formatted.addProperty("prompt", request.get("content").getAsString());
        return formatted;
    }
    
    private JsonObject sendHttpRequest(JsonObject formattedRequest) throws Exception {
        // Implement HTTP request logic
        // This is a simplified example
        JsonObject response = new JsonObject();
        response.addProperty("text", "Response from MyCustomAPI");
        return response;
    }
    
    private JsonObject parseResponse(JsonObject apiResponse) {
        // Implement response parsing logic
        JsonObject parsed = new JsonObject();
        parsed.addProperty("response", apiResponse.get("text").getAsString());
        return parsed;
    }
}
```

## Using API Connectors with Agents

API connectors can be used within agents to communicate with external services:

```java
public class ApiIntegratedAgent extends BaseAgent {
    
    private ApiConnector apiConnector;
    
    public ApiIntegratedAgent(String id, ApiConnector apiConnector) {
        super(id, "API Integrated Agent");
        this.apiConnector = apiConnector;
    }
    
    @Override
    public JsonObject process(JsonObject input) {
        if (!apiConnector.isReady()) {
            JsonObject errorResponse = new JsonObject();
            errorResponse.addProperty("error", "API connector not ready");
            return errorResponse;
        }
        
        try {
            JsonObject apiResponse = apiConnector.sendRequest(input);
            
            JsonObject response = new JsonObject();
            response.addProperty("agentId", getId());
            response.addProperty("agentName", getName());
            response.addProperty("response", apiResponse.toString());
            
            return response;
        } catch (ApiException e) {
            JsonObject errorResponse = new JsonObject();
            errorResponse.addProperty("agentId", getId());
            errorResponse.addProperty("error", "Failed to communicate with API: " + e.getMessage());
            return errorResponse;
        }
    }
    
    @Override
    public void configure(JsonObject configuration) {
        super.configure(configuration);
        
        if (configuration.has("apiConfig")) {
            JsonObject apiConfig = configuration.getAsJsonObject("apiConfig");
            apiConnector.configure(apiConfig);
        }
    }
}
```

## Configuration

API connectors can be configured using the `configure` method:

```java
JsonObject config = new JsonObject();
config.addProperty("apiKey", "your-api-key");
config.addProperty("baseUrl", "https://api.example.com/v1");

apiConnector.configure(config);
```

## Supported APIs

JavaChatX aims to provide connectors for popular AI services:

1. **OpenAI Connector** - For GPT models
2. **Google Gemini Connector** - For Google's Gemini models
3. **Hugging Face Connector** - For various open-source models
4. **Anthropic Connector** - For Claude models

## Best Practices

1. **Secure API Keys**: Never hardcode API keys; use configuration files or environment variables
2. **Handle Rate Limits**: Implement proper rate limiting and retry logic
3. **Error Handling**: Implement comprehensive error handling for network and API issues
4. **Logging**: Log API requests and responses for debugging (be careful with sensitive data)
5. **Timeouts**: Set appropriate timeouts for API requests
6. **Caching**: Consider caching responses for better performance (where appropriate)

## Example API Connectors

### Echo API Connector
A simple connector that echoes back the request:

```java
EchoApiConnector connector = new EchoApiConnector();
JsonObject config = new JsonObject();
connector.configure(config);

JsonObject request = new JsonObject();
request.addProperty("content", "Hello, API!");
JsonObject response = connector.sendRequest(request);
```

### OpenAI Connector (Conceptual)
A connector for OpenAI's API:

```java
public class OpenAIConnector extends BaseApiConnector {
    
    private String apiKey;
    private String model;
    
    public OpenAIConnector() {
        super("OpenAI");
        this.model = "gpt-3.5-turbo";
    }
    
    @Override
    public JsonObject sendRequest(JsonObject request) throws ApiException {
        try {
            // Format request for OpenAI API
            JsonObject openAIRequest = new JsonObject();
            openAIRequest.addProperty("model", this.model);
            
            JsonArray messages = new JsonArray();
            JsonObject message = new JsonObject();
            message.addProperty("role", "user");
            message.addProperty("content", request.get("content").getAsString());
            messages.add(message);
            
            openAIRequest.add("messages", messages);
            
            // Send request to OpenAI (simplified)
            // In a real implementation, you would use an HTTP client
            JsonObject openAIResponse = callOpenAIApi(openAIRequest);
            
            // Parse response
            JsonObject response = new JsonObject();
            JsonArray choices = openAIResponse.getAsJsonArray("choices");
            if (choices.size() > 0) {
                JsonObject choice = choices.get(0).getAsJsonObject();
                JsonObject messageResponse = choice.getAsJsonObject("message");
                response.addProperty("response", messageResponse.get("content").getAsString());
            }
            
            return response;
        } catch (Exception e) {
            throw new ApiException("Error communicating with OpenAI", e);
        }
    }
    
    @Override
    public void configure(JsonObject configuration) {
        super.configure(configuration);
        
        if (configuration.has("apiKey")) {
            this.apiKey = configuration.get("apiKey").getAsString();
        }
        
        if (configuration.has("model")) {
            this.model = configuration.get("model").getAsString();
        }
        
        // Validate configuration
        if (this.apiKey != null && !this.apiKey.isEmpty()) {
            this.ready = true;
        }
    }
    
    private JsonObject callOpenAIApi(JsonObject request) throws Exception {
        // Implementation would use an HTTP client like OkHttp or Apache HttpClient
        // This is a placeholder implementation
        JsonObject response = new JsonObject();
        JsonArray choices = new JsonArray();
        JsonObject choice = new JsonObject();
        JsonObject message = new JsonObject();
        message.addProperty("content", "Response from OpenAI");
        choice.add("message", message);
        choices.add(choice);
        response.add("choices", choices);
        return response;
    }
}
```

## Testing API Connectors

Create unit tests for your API connectors to ensure they work correctly:

```java
@Test
public void testCustomApiConnector() throws ApiException {
    MyCustomApiConnector connector = new MyCustomApiConnector();
    
    JsonObject config = new JsonObject();
    config.addProperty("apiKey", "test-key");
    connector.configure(config);
    
    assertTrue(connector.isReady());
    
    JsonObject request = new JsonObject();
    request.addProperty("content", "Test input");
    
    JsonObject response = connector.sendRequest(request);
    
    assertNotNull(response);
    assertTrue(response.has("response"));
}
```

## Next Steps

1. Explore the [Agent Development Guide](AGENT_GUIDE.md) to create agents that use your API connectors
2. Learn about [Plugin Development](PLUGIN_DEVELOPMENT.md) to create plugins that integrate with APIs
3. Check out the [examples](../examples/) directory for more API integration implementations