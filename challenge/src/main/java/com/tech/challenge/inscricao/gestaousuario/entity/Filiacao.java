package com.tech.challenge.inscricao.gestaousuario.entity;

import jakarta.persistence.Embeddable;

@Embeddable
public class Filiacao {
    private String nomeMae;
    private String nomePai;

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
