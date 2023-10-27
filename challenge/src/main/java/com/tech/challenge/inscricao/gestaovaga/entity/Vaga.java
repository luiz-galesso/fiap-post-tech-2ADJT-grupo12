package com.tech.challenge.inscricao.gestaovaga.entity;

import com.tech.challenge.inscricao.gestaousuario.entity.Usuario;
import com.tech.challenge.inscricao.gestaovaga.enumeration.Nivel;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

import java.util.Calendar;
import java.util.Date;

@Entity
@Table(name="tb_vaga")
public class Vaga {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;
    @NotNull(message="O título é obrigatório")
    private String titulo;
    @NotNull(message="A descrição é obrigatória")
    private String descricao;

/*    private List<Etapa> etapas;*/
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
    }
}
