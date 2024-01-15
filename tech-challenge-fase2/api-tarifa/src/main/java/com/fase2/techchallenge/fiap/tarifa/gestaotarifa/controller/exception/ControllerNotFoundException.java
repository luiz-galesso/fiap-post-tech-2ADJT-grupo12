package com.fase2.techchallenge.fiap.tarifa.gestaotarifa.controller.exception;

public class ControllerNotFoundException extends  RuntimeException {
    public ControllerNotFoundException(String message) {
        super(message);
    }
}
