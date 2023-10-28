package com.tech.challenge.inscricao.gestaoperfil.controller.exception;

public class AuthenticationException extends RuntimeException{

    private String campo;
    public AuthenticationException(String string) {
        super(string);
    }

    public AuthenticationException(String mensagem, String campo) {
        super(mensagem);
        this.campo = campo;
    }

    public String getCampo() {
        return campo;
    }
}
