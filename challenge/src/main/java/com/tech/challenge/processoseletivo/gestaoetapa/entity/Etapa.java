package com.tech.challenge.processoseletivo.gestaoetapa.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;

@Entity
@Table(name = "tb_etapa")
public class Etapa {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="etapa_generator")
    @SequenceGenerator(name="etapa_generator", sequenceName="etapa_sequence", allocationSize = 1)
    private Long idEtapa;

    @NotBlank (message = "A descrição é obrigatória")
    @Column(unique = true)
    private String descricao;

    public Etapa() {}

    public Etapa(String descricao) {
        setDescricao(descricao);
    }

    public Long getId() {
        return idEtapa;
    }

    public void setId(Long id) {
        this.idEtapa = id;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao.toUpperCase();
    }
}
