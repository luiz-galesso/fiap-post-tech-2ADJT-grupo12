package com.fase2.techchallenge.fiap.cadastro.condutor.controller.exception;

public class ValidateMessage {
    private String campo;
    private String mensagem;

    public ValidateMessage() {
    }

    public ValidateMessage(String campo, String mensagem) {
        this.campo = campo;
        this.mensagem = mensagem;
    }

    public String getCampo() {
        return campo;
    }

    public void setCampo(String campo) {
        this.campo = campo;
    }

    public String getMensagem() {
        return mensagem;
    }

    public void setMensagem(String mensagem) {
        this.mensagem = mensagem;
    }
}
