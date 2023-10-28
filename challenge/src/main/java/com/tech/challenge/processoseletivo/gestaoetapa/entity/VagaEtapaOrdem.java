package com.tech.challenge.processoseletivo.gestaoetapa.entity;
import com.tech.challenge.inscricao.gestaoetapa.entity.Etapa;
import com.tech.challenge.inscricao.gestaousuario.entity.Candidato;
import com.tech.challenge.inscricao.gestaousuario.entity.Usuario;
import com.tech.challenge.inscricao.gestaovaga.entity.Vaga;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

@Entity
@Table(name = "tb_feedback")
@Embeddable
@IdClass(VagaEtapaOrdemId.class)
public class VagaEtapaOrdem {


    @Id
    @ManyToOne(cascade = CascadeType.ALL)
    private Candidato candidato;
    @Id
    @ManyToOne(cascade =  CascadeType.ALL)
    private Vaga vaga;
    @Id
    @ManyToOne(cascade = CascadeType.ALL)
    private Etapa etapa;

    @NotNull(message = "É necessário preencher o conteúdo do feedback.")
    private String conteudoFeedback;
    @ManyToOne(cascade = CascadeType.ALL)
    @NotNull(message = "É necessário informar o autor do feedback.")
    private Usuario autor;

    private LocalDate dataFeedback;
    public VagaEtapaOrdem(){}

    public VagaEtapaOrdem(Candidato candidato, Vaga vaga, Etapa etapa, String conteudoFeedback, Usuario autor, LocalDate dataFeedback) {
        this.candidato = candidato;
        this.vaga = vaga;
        this.etapa = etapa;
        this.conteudoFeedback = conteudoFeedback;
        this.autor = autor;
        this.dataFeedback = dataFeedback;
    }

    public Candidato getCandidato() {
        return candidato;
    }

    public void setCandidato(Candidato candidato) {
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
