package com.techcognita.javachatx.plugin;

/**
 * Exception thrown when there are issues with plugin execution.
 */
public class PluginException extends Exception {

    public PluginException(String message) {
        super(message);
    }

    public PluginException(String message, Throwable cause) {
        super(message, cause);
    }
}