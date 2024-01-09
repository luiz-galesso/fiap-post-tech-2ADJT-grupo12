package com.fase2.techchallenge.fiap.pagamento.gestaopagamento.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;

import java.util.Objects;

@Entity
@Table(name="tb_meio_pagamento")
public class MeioPagamento {

    @Id
    @NotNull(message = "O id do meio de pagamento é obrigatório")
    private String id;
    @NotNull(message = "A descrição do meio de pagamento é obrigatória")
    private String descricao;

    @NotNull(message = "A situação do meio de pagamento é obrigatória")
    private String situacao;
    /* TODO fazer um enum para esse cara */

    public MeioPagamento() {
    }

    public MeioPagamento(String id, String descricao, String situacao) {
        this.id = id;
        this.descricao = descricao;
        this.situacao = situacao;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getSituacao() {
        return situacao;
    }

    public void setSituacao(String situacao) {
        this.situacao = situacao;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MeioPagamento that = (MeioPagamento) o;
        return Objects.equals(id, that.id) && Objects.equals(descricao, that.descricao) && Objects.equals(situacao, that.situacao);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, descricao, situacao);
    }

    @Override
    public String toString() {
        return "MeioPagamento{" +
                "id='" + id + '\'' +
                ", descricao='" + descricao + '\'' +
                ", situacao='" + situacao + '\'' +
                '}';
    }
}
