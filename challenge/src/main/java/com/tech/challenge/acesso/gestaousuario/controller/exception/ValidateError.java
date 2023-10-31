package com.tech.challenge.acesso.gestaousuario.controller.exception;

import java.util.ArrayList;
import com.tech.challenge.acesso.gestaousuario.controller.exception.StandardError;
import com.tech.challenge.acesso.gestaousuario.controller.exception.ValidateMessage;
import java.util.List;

public class ValidateError extends StandardError {

    private List<ValidateMessage> mensagens = new ArrayList<>();

    public List<ValidateMessage> getMensagens() {
        return mensagens;
    }

    public void addMensagens(String campo, String mensagem) {
        mensagens.add(new ValidateMessage(campo, mensagem));
    }
}
