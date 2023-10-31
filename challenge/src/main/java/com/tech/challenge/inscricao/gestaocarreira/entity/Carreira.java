package com.tech.challenge.inscricao.gestaocarreira.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name="tb_carreira")
public class Carreira {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="carreira_generator")
    @SequenceGenerator(name="carreira_generator", sequenceName="carreira_sequence", allocationSize = 1)
    private Long id;

    @NotBlank
    @NotNull(message="A descrição é obrigatória")
    @Column(unique = true)
    private String descricao;

    public Carreira() {
    }

    public Carreira(Long id) {
        this.id = id;
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