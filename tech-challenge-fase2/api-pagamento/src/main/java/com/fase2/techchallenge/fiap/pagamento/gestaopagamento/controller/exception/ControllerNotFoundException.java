package com.fase2.techchallenge.fiap.pagamento.gestaopagamento.controller.exception;

public class ControllerNotFoundException extends  RuntimeException {
    public ControllerNotFoundException(String message) {
        super(message);
    }
}
