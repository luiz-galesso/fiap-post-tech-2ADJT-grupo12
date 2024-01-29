package com.fase2.techchallenge.fiap.fiscalizacao.exception;

public class ControllerNotFoundException extends  RuntimeException {
    public ControllerNotFoundException(String message) {
        super(message);
    }
}
