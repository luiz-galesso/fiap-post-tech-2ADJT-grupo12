package com.tech.challenge.acesso.gestaoperfil.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name="tb_perfil")
public class Perfil {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="perfil_generator")
    @SequenceGenerator(name="perfil_generator", sequenceName="perfil_sequence", allocationSize = 1)
    private Long idPerfil;

    @NotBlank
    @NotNull(message="A descrição é obrigatória")
    @Column(unique=true)
    private String descricao;

    public Perfil() {
    }

    public Perfil(Long idPerfil) {
        this.idPerfil = idPerfil;
    }

    public Long getIdPerfil() {
        return idPerfil;
    }

    public void setIdPerfil(Long idPerfil) {
        this.idPerfil = idPerfil;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
}