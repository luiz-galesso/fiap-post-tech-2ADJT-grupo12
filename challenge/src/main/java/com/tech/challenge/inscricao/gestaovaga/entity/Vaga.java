package com.tech.challenge.inscricao.gestaovaga.entity;

import com.tech.challenge.inscricao.gestaoetapa.entity.Etapa;
import com.tech.challenge.inscricao.gestaousuario.entity.Usuario;
import com.tech.challenge.inscricao.gestaovaga.enumeration.Nivel;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Entity
@Table(name="tb_vaga")
public class Vaga {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "vaga_id")
    private Long id;
    @NotNull(message="O título é obrigatório")
    private String titulo;
    @NotNull(message="A descrição é obrigatória")
    private String descricao;

    @ManyToMany
     @JoinTable(name = "tb_vaga_etapa",
        joinColumns = @JoinColumn(name="vaga_id"),
             inverseJoinColumns = @JoinColumn(name = "etapa_id")
     )
    private List<Etapa> etapas;
    @NotNull(message="A carreira é obrigatória")
    private String carreira;

    @Enumerated(EnumType.STRING)
    private Nivel nivel;

    @Temporal(TemporalType.DATE)
    private Date dataExpiracao;

    @Temporal(TemporalType.DATE)
    private Date dataCriacao;

    @ManyToOne
    private Usuario criador;

    @OneToOne
    private SolicitaVaga solicitacao;

    public Vaga() {
    }

    public Vaga(Long id) {
        this.id = id;
    }

    public Vaga (SolicitaVaga solicitacao)
    {
        this.titulo = solicitacao.getTitulo();
        this.descricao = solicitacao.getDescricao();
        this.nivel = solicitacao.getNivel();
        //pensar em regra de expiracao
        //---

        this.dataCriacao = Calendar.getInstance().getTime();
        this.criador = solicitacao.getAvaliador();
        this.solicitacao = solicitacao;
        //alterar
        this.carreira = solicitacao.getTitulo();
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

    public List<Etapa> getEtapas() {
        return etapas;
    }

    public void setEtapas(List<Etapa> etapas) {
        this.etapas = etapas;
    }

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

    public SolicitaVaga getSolicitacao() {
        return solicitacao;
    }

    public void setSolicitacao(SolicitaVaga solicitacao) {
        this.solicitacao = solicitacao;
    }
}
