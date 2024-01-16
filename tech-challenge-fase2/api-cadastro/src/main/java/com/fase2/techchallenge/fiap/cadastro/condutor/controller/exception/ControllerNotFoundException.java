package com.fase2.techchallenge.fiap.cadastro.condutor.controller.exception;

public class ControllerNotFoundException extends  RuntimeException {
    public ControllerNotFoundException(String message) {
        super(message);
    }
}
