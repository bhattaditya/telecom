package com.bhattaditya.telecom.exception;

public class ResourceNotFoundException extends RuntimeException{

    public ResourceNotFoundException() {
    }

    public ResourceNotFoundException(String message) {
        super(message);
    }

    public ResourceNotFoundException(String resource, String field, String value) {
         super(String.format("%s with %s: %s not found ", resource, field, value));
    }
}
