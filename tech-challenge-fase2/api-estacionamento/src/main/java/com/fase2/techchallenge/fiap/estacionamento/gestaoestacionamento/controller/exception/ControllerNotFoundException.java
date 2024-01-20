package com.fase2.techchallenge.fiap.estacionamento.gestaoestacionamento.controller.exception;

public class ControllerNotFoundException extends  RuntimeException {
    public ControllerNotFoundException(String message) {
        super(message);
    }
}
