package com.tech.challenge.processoseletivo.gestaoetapa.entity;

import com.tech.challenge.inscricao.gestaovaga.entity.Vaga;
import jakarta.persistence.Embeddable;
import jakarta.persistence.ManyToOne;

import java.io.Serializable;

@Embeddable
public class VagaEtapaID implements Serializable {


    @ManyToOne
    private Etapa etapa;

    @ManyToOne
    private Vaga vaga;

    private Integer ordem;

    public Etapa getEtapa() {
        return etapa;
    }

    public void setEtapa(Etapa etapa) {
        this.etapa = etapa;
    }

    public Vaga getVaga() {
        return vaga;
    }

    public void setVaga(Vaga vaga) {
        this.vaga = vaga;
    }

    public Integer getOrdem() {
        return ordem;
    }

    public void setOrdem(Integer ordem) {
        this.ordem = ordem;
    }

    public VagaEtapaID(Vaga vaga, Etapa etapa, Integer ordem) {
        this.etapa = etapa;
        this.vaga  = vaga;
        this.ordem = ordem;
    }

    public VagaEtapaID() {
    }
}
