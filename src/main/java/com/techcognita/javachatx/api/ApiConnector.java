package com.techcognita.javachatx.api;

import com.google.gson.JsonObject;

import com.techcognita.javachatx.api.ApiException;

/**
 * Interface for connecting to external AI services and APIs.
 * Provides a standardized way to integrate with various AI providers.
 */
public interface ApiConnector {

    /**
     * Get the name of this API connector.
     * 
     * @return The connector name
     */
    String getName();

    /**
     * Send a request to the external API and get a response.
     * 
     * @param request The request data as a JSON object
     * @return The API response as a JSON object
     * @throws ApiException If there's an error communicating with the API
     */
    JsonObject sendRequest(JsonObject request) throws ApiException;

    /**
     * Check if the connector is properly configured and ready to use.
     * 
     * @return true if the connector is ready, false otherwise
     */
    boolean isReady();

    /**
     * Configure the API connector with necessary credentials and settings.
     * 
     * @param configuration The configuration parameters as a JSON object
     */
    void configure(JsonObject configuration);
}