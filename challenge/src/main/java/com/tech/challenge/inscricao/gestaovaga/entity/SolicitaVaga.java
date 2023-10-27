package com.tech.challenge.inscricao.gestaovaga.entity;

import com.tech.challenge.inscricao.gestaousuario.entity.Usuario;
import com.tech.challenge.inscricao.gestaovaga.enumeration.Nivel;
import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name = "tb_solicita_vaga")
public class SolicitaVaga
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String titulo;

    private String descricao;

    private Integer quantidadeDeVagas;

    @Temporal(TemporalType.TIMESTAMP)
    private Date dataSolicitacao;

    @Temporal(TemporalType.TIMESTAMP)
    private Date dataAvaliado = null;

    @ManyToOne
    private Usuario solicitante;

    @ManyToOne
    private Usuario avaliador = null;

    @Enumerated(EnumType.STRING)
    private Nivel nivel;

    public boolean isAprovado() {
        return aprovado;
    }

    public void setAprovado(boolean aprovado) {
        this.aprovado = aprovado;
    }

    private boolean aprovado;


    public SolicitaVaga() {
    }

    public SolicitaVaga(String titulo, String descricao, Integer quantidadeDeVagas, String idSolicitante, Nivel nivel) {
        this.titulo = titulo;
        this.descricao = descricao;
        this.quantidadeDeVagas = quantidadeDeVagas;
        this.solicitante = new Usuario(idSolicitante);
        this.nivel = nivel;
        this.dataSolicitacao = new Date();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Integer getQuantidadeDeVagas() {
        return quantidadeDeVagas;
    }

    public void setQuantidadeDeVagas(Integer quantidadeDeVagas) {
        this.quantidadeDeVagas = quantidadeDeVagas;
    }

    public Usuario getSolicitante() {
        return solicitante;
    }

    public void setSolicitante(Usuario solicitante) {
        this.solicitante = solicitante;
    }

    public Usuario getAvaliador() {
        return avaliador;
    }

    public void setAvaliador(Usuario avaliador) {
        this.avaliador = avaliador;
    }

    public Nivel getNivel() {
        return nivel;
    }

    public void setNivel(Nivel nivel) {
        this.nivel = nivel;
    }

    public Date getDataSolicitacao() {
        return dataSolicitacao;
    }

    public void setDataSolicitacao(Date dataSolicitacao) {
        this.dataSolicitacao = dataSolicitacao;
    }

    public Date getDataAvaliado() {
        return dataAvaliado;
    }

    public void setDataAvaliado(Date dataAvaliado) {
        this.dataAvaliado = dataAvaliado;
    }
}
