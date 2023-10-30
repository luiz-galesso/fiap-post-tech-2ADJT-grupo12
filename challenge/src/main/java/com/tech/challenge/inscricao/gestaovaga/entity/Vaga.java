package com.tech.challenge.inscricao.gestaovaga.entity;

import com.tech.challenge.inscricao.gestaovaga.enumeration.VagaSituacao;
import com.tech.challenge.acesso.gestaousuario.entity.Usuario;
import com.tech.challenge.inscricao.gestaovaga.enumeration.Nivel;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

import java.util.Calendar;
import java.util.Date;

@Entity
@Table(name="tb_vaga"
)
public class Vaga {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="vaga_generator")
    @SequenceGenerator(name="vaga_generator", sequenceName="vaga_sequence", allocationSize = 1)
    private Long id;
    @NotNull(message="O título é obrigatório")
    private String titulo;
    @NotNull(message="A descrição é obrigatória")
    private String descricao;

    @NotNull(message="A carreira é obrigatória")
    private String carreira;

    @Enumerated(EnumType.STRING)
    private Nivel nivel;

    private Integer quantidadeDeVagas;
    @Enumerated(EnumType.STRING)
    private VagaSituacao situacao;

    @Temporal(TemporalType.DATE)
    private Date dataCriacao;
    @Temporal(TemporalType.DATE)
    private Date dataExpiracao;

    @ManyToOne
    private Usuario criador;

    @OneToOne
    private SolicitacaoVaga solicitacao;

    public Vaga() {
    }

    public Vaga(Long id) {
        this.id = id;
    }

    public Vaga (SolicitacaoVaga solicitacao)
    {
        this.titulo = solicitacao.getTitulo();
        this.descricao = solicitacao.getDescricao();
        this.nivel = solicitacao.getNivel();
        this.quantidadeDeVagas = solicitacao.getQuantidadeDeVagas();
        this.dataCriacao = Calendar.getInstance().getTime();
        this.criador = solicitacao.getAvaliador();
        this.solicitacao = solicitacao;
        this.carreira = solicitacao.getTitulo();
        this.dataExpiracao = solicitacao.getDataExpiracao();
        this.situacao = VagaSituacao.valueOf("ABERTA");
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
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

    /*public void setEtapas(List<Etapa> etapas) {
        this.etapas = etapas;
    }*/

    public String getCarreira() {
        return carreira;
    }

    public void setCarreira(String carreira) {
        this.carreira = carreira;
    }

    public Nivel getNivel() {
        return nivel;
    }

    public void setNivel(Nivel nivel) {
        this.nivel = nivel;
    }

    public Date getDataExpiracao() {
        return dataExpiracao;
    }

    public void setDataExpiracao(Date dataExpiracao) {
        this.dataExpiracao = dataExpiracao;
    }

    public Date getDataCriacao() {
        return dataCriacao;
    }

    public void setDataCriacao(Date dataCriacao) {
        this.dataCriacao = dataCriacao;
    }

    public Usuario getCriador() {
        return criador;
    }

    public void setCriador(Usuario criador) {
        this.criador = criador;
    }

    public SolicitacaoVaga getSolicitacao() {
        return solicitacao;
    }

    public void setSolicitacao(SolicitacaoVaga solicitacao) {
        this.solicitacao = solicitacao;
    }
}
