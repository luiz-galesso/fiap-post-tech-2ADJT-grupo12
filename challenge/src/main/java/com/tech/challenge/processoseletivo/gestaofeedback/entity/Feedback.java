package com.tech.challenge.processoseletivo.gestaofeedback.entity;
import com.tech.challenge.processoseletivo.gestaoetapa.entity.Etapa;
import com.tech.challenge.acesso.gestaousuario.entity.Usuario;
import com.tech.challenge.inscricao.gestaovaga.entity.Vaga;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

@Entity
@Table(name = "tb_feedback")
@Embeddable
public class Feedback {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Usuario candidato;
    @ManyToOne
    private Vaga vaga;

    @ManyToOne
    private Etapa etapa;

    @NotNull(message = "É necessário preencher o conteúdo do feedback.")
    private String conteudoFeedback;
    @ManyToOne
    @NotNull(message = "É necessário informar o autor do feedback.")
    private Usuario autor;

    private LocalDate dataFeedback;
    public Feedback(){}

    public Feedback(Long id, Usuario candidato, Vaga vaga, Etapa etapa, String conteudoFeedback, Usuario autor, LocalDate dataFeedback) {
        this.id = id;
        this.candidato = candidato;
        this.vaga = vaga;
        this.etapa = etapa;
        this.conteudoFeedback = conteudoFeedback;
        this.autor = autor;
        this.dataFeedback = dataFeedback;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Usuario getCandidato() {
        return candidato;
    }

    public void setCandidato(Usuario candidato) {
        this.candidato = candidato;
    }

    public Usuario getUsuario() {
        return candidato;
    }

    public void setUsuario(Usuario candidato) {
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

    public String getConteudoFeedback() {
        return conteudoFeedback;
    }
    public void setConteudoFeedback(String conteudoFeedback) {
        this.conteudoFeedback = conteudoFeedback;
    }
    public Usuario getAutor() {
        return autor;
    }
    public void setAutor(Usuario idAutor) {
        this.autor = idAutor;
    }
    public LocalDate getDataFeedback() {
        return dataFeedback;
    }

    public void setDataFeedback(LocalDate dataCriacao) {
        this.dataFeedback = dataCriacao;
    }
}
