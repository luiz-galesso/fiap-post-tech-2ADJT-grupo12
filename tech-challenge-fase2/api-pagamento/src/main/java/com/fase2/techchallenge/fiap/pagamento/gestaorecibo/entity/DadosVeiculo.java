package com.fase2.techchallenge.fiap.pagamento.gestaorecibo.entity;

import jakarta.persistence.Embeddable;

@Embeddable
public class DadosVeiculo {
    private String placaVeiculo;
    private String descricaoVeiculo;

    public DadosVeiculo() {
    }

    public DadosVeiculo(String placaVeiculo, String descricaoVeiculo) {
        this.placaVeiculo = placaVeiculo;
        this.descricaoVeiculo = descricaoVeiculo;
    }

    public String getPlacaVeiculo() {
        return placaVeiculo;
    }

    public void setPlacaVeiculo(String placaVeiculo) {
        this.placaVeiculo = placaVeiculo;
    }

    public String getDescricaoVeiculo() {
        return descricaoVeiculo;
    }

    public void setDescricaoVeiculo(String descricaoVeiculo) {
        this.descricaoVeiculo = descricaoVeiculo;
    }
}
