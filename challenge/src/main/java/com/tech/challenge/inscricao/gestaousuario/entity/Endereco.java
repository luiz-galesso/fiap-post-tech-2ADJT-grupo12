package com.tech.challenge.inscricao.gestaousuario.entity;

import jakarta.persistence.Embeddable;

@Embeddable
public class Endereco {
    private Integer cep;
    private String numero;
    private String complemento;

    public Integer getCep() {
        return cep;
    }

    public void setCep(Integer cep) {
        this.cep = cep;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getComplemento() {
        return complemento;
    }

    public void setComplemento(String complemento) {
        this.complemento = complemento;
    }
}
