package com.techcognita.javachatx.api;

/**
 * Exception thrown when there are issues with API communication.
 */
public class ApiException extends Exception {

    public ApiException(String message) {
        super(message);
    }

    public ApiException(String message, Throwable cause) {
        super(message, cause);
    }
}