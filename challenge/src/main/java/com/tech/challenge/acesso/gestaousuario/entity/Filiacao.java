package com.tech.challenge.acesso.gestaousuario.entity;

import jakarta.persistence.Embeddable;
import jakarta.validation.constraints.NotNull;

@Embeddable
public class Filiacao {

    @NotNull(message="O nome da mãe é obrigatório")
    private String nomeMae;
    private String nomePai;

    public Filiacao(){}

    public Filiacao(String nomeMae, String nomePai) {
        this.nomeMae = nomeMae;
        this.nomePai = nomePai;
    }

    public String getNomeMae() {
        return nomeMae;
    }

    public void setNomeMae(String nomeMae) {
        this.nomeMae = nomeMae;
    }

    public String getNomePai() {
        return nomePai;
    }

    public void setNomePai(String nomePai) {
        this.nomePai = nomePai;
    }
}
