package com.tech.challenge.processoseletivo.gestaofeedback.entity;

import com.tech.challenge.inscricao.gestaoetapa.entity.Etapa;
import com.tech.challenge.inscricao.gestaousuario.entity.Usuario;
import com.tech.challenge.inscricao.gestaovaga.entity.Vaga;

import java.io.Serial;
import java.io.Serializable;
import java.util.Objects;

public class FeedbackId implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    private Usuario candidato;
    private Vaga vaga;
    private Etapa etapa;

    public FeedbackId() {
    }

    public FeedbackId(Usuario candidato, Vaga vaga, Etapa etapa) {
        this.candidato = candidato;
        this.vaga = vaga;
        this.etapa = etapa;
    }

    public FeedbackId(String idCandidato, Long idVaga, Long idEtapa) {
        Usuario candidato = new Usuario();
        candidato.setCpf(idCandidato);
        Vaga vaga = new Vaga();
        vaga.setId(idVaga);
        Etapa etapa = new Etapa();
        etapa.setId(idEtapa);
        this.candidato = candidato;
        this.vaga = vaga;
        this.etapa = etapa;
    }

    public Usuario getCandidato() {
        return candidato;
    }

    public void setCandidato(Usuario candidato) {
        this.candidato = candidato;
    }

    public Vaga getVaga() {
        return vaga;
    }

    public void setVaga(Vaga vaga) {
        this.vaga = vaga;
    }

    public Etapa getEtapa() {
        return etapa;
    }

    public void setEtapa(Etapa etapa) {
        this.etapa = etapa;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FeedbackId that = (FeedbackId) o;
        return Objects.equals(candidato, that.candidato) && Objects.equals(vaga, that.vaga) && Objects.equals(etapa, that.etapa);
    }

    @Override
    public int hashCode() {
        return Objects.hash(candidato, vaga, etapa);
    }


}
