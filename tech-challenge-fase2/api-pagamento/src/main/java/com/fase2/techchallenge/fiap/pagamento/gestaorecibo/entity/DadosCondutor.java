package com.fase2.techchallenge.fiap.pagamento.gestaorecibo.entity;

import jakarta.persistence.Embeddable;

@Embeddable
public class DadosCondutor {
    private String email;
    private String nomeCondutor;
    private String cpf;

    public DadosCondutor() {
    }

    public DadosCondutor(String email, String nomeCondutor, String cpf) {
        this.email = email;
        this.nomeCondutor = nomeCondutor;
        this.cpf = cpf;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNomeCondutor() {
        return nomeCondutor;
    }

    public void setNomeCondutor(String nomeCondutor) {
        this.nomeCondutor = nomeCondutor;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }
}
