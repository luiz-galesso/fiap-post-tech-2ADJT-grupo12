package com.tech.challenge.inscricao.gestaoetapa.entity;

import com.tech.challenge.inscricao.gestaovaga.entity.Vaga;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

import java.util.List;

@Entity
@Table(name = "tb_etapa")
public class Etapa {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "etapa_id")
    private Long id;

    @NotNull(message = "A descrição é obrigatória")
    @Column(unique = true)
    private String descricao;

    @ManyToMany(mappedBy = "etapas")
    private List<Vaga> vagas;

    public Etapa() {}

    public Etapa(String descricao) {
        this.descricao = descricao;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
}
