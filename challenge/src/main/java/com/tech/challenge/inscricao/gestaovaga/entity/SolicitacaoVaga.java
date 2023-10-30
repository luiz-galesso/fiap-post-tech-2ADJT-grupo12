package com.tech.challenge.inscricao.gestaovaga.entity;

import com.tech.challenge.acesso.gestaousuario.entity.Usuario;
import com.tech.challenge.inscricao.gestaocarreira.entity.Carreira;
import com.tech.challenge.inscricao.gestaovaga.enumeration.Nivel;
import com.tech.challenge.inscricao.gestaovaga.enumeration.SolicitacaoSituacao;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Entity
@Table(name = "tb_solicitacao_vaga")
public class SolicitacaoVaga
{
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="solicitacao_generator")
    @SequenceGenerator(name="solicitacao_generator", sequenceName="solicitacao_sequence", allocationSize = 1)
    private Integer id;

    private String titulo;

    private String descricao;

    @NotNull(message="A carreira é obrigatória")
    @ManyToOne
    private Carreira carreira;

    @Enumerated(EnumType.STRING)
    private Nivel nivel;
    private Integer quantidadeDeVagas;

    public SolicitacaoVaga(String idSolicitante, Nivel nivel, String idAvaliador, SolicitacaoSituacao situacao) {
    }


    @Enumerated(EnumType.STRING)
    private SolicitacaoSituacao situacao;
    @Temporal(TemporalType.TIMESTAMP)
    private Date dataSolicitacao;

    @Temporal(TemporalType.TIMESTAMP)
    private Date dataAvaliacao = null;

    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date dataExpiracao;

    @ManyToOne
    private Usuario solicitante;

    @ManyToOne
    private Usuario avaliador = null;

    private String mensagem;


    public SolicitacaoVaga() {
    }

    public SolicitacaoVaga(String titulo, String descricao, Integer quantidadeDeVagas, String idSolicitante, Nivel nivel, Date dataExpiracao, Long idCarreira, SolicitacaoSituacao situacao) {
        this.titulo = titulo;
        this.descricao = descricao;
        this.quantidadeDeVagas = quantidadeDeVagas;
        this.solicitante = new Usuario(idSolicitante);
        this.nivel = nivel;
        this.dataSolicitacao = new Date();
        this.dataExpiracao = dataExpiracao;
        this.carreira = new Carreira(idCarreira);
        this.situacao = situacao;
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
    public SolicitacaoSituacao getSituacao() {
        return situacao;
    }

    public void setSituacao(SolicitacaoSituacao situacao) {
        this.situacao = situacao;
    }
    public Date getDataSolicitacao() {
        return dataSolicitacao;
    }

    public void setDataSolicitacao(Date dataSolicitacao) {
        this.dataSolicitacao = dataSolicitacao;
    }

    public Date getDataAvaliacao() {
        return dataAvaliacao;
    }

    public void setDataAvaliacao(Date dataAvaliacao) {
        this.dataAvaliacao = dataAvaliacao;
    }

    public String getMensagem() {
        return mensagem;
    }

    public void setMensagem(String mensagem) {
        this.mensagem = mensagem;
    }

    public Date getDataExpiracao() {
        return dataExpiracao;
    }

    public void setDataExpiracao(Date dataExpiracao) {
        this.dataExpiracao = dataExpiracao;
    }

    public Carreira getCarreira() {
        return carreira;
    }

    public void setCarreira(Carreira carreira) {
        this.carreira = carreira;
    }
}
