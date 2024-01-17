package com.fase2.techchallenge.fiap.cadastro.meiopagamentocondutor.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "tb_meio_pagamento_condutor")
public class MeioPagamentoCondutor {

    private Long meioPagamentoFavorito;

    //@OneToOne
    @Id
    private String emailCondutor;

    public MeioPagamentoCondutor() {
    }

    public MeioPagamentoCondutor(String emailCondutor, Long meioPagamentoFavorito) {
        this.emailCondutor = emailCondutor;
        this.meioPagamentoFavorito = meioPagamentoFavorito;
    }

    public Long getMeioPagamentoFavorito() {
        return meioPagamentoFavorito;
    }

    public void setMeioPagamentoFavorito(Long meioPagamentoFavorito) {
        this.meioPagamentoFavorito = meioPagamentoFavorito;
    }

    public String getEmailCondutor() {
        return emailCondutor;
    }

    public void setEmailCondutor(String emailCondutor) {
        this.emailCondutor = emailCondutor;
    }
}
