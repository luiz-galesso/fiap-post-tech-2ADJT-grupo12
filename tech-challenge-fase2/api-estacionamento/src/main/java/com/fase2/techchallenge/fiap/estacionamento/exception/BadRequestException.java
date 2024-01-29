package com.fase2.techchallenge.fiap.estacionamento.exception;

public class BadRequestException extends  RuntimeException {
    public BadRequestException(String message) {
        super(message);
    }
}
