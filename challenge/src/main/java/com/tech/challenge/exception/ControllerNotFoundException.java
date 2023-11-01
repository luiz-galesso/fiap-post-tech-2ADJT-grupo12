package com.tech.challenge.exception;

public class ControllerNotFoundException extends  RuntimeException {
    public ControllerNotFoundException(String message) {
        super(message);
    }
}
