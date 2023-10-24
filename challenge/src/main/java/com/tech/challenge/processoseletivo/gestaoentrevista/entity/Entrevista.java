package com.tech.challenge.processoseletivo.gestaoentrevista.entity;

import com.tech.challenge.inscricao.gestaousuario.entity.Candidato;
import com.tech.challenge.inscricao.gestaousuario.entity.Usuario;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

import java.util.Date;


@Entity
@Table(name = "tb_entrevista")
public class Entrevista {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private Date dataHora;

    @NotNull
    private String local;

    @OneToOne
    private Candidato candidato;

    @OneToOne
    private Usuario entrevistador;

    public Entrevista() {
    }

    public Entrevista(Long id, Date dataHora, String local, Candidato candidato, Usuario entrevistador) {
        this.id = id;
        this.dataHora = dataHora;
        this.local = local;
        this.candidato = candidato;
        this.entrevistador = entrevistador;
    }

    public Long getId() {
        return id;
    }

    public Candidato getCandidato() {
        return candidato;
    }

    public void setCandidato(Candidato candidato) {
        this.candidato = candidato;
    }

    public Usuario getEntrevistador() {
        return entrevistador;
    }

    public void setEntrevistador(Usuario entrevistador) {
        this.entrevistador = entrevistador;
    }

    public Date getDataHora() {
        return dataHora;
    }

    public void setDataHora(Date dataHora) {
        this.dataHora = dataHora;
    }

    public String getLocal() {
        return local;
    }

    public void setLocal(String local) {
        this.local = local;
    }

}
