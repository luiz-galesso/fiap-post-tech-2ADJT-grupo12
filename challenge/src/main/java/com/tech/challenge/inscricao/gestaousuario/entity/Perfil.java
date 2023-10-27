package com.tech.challenge.inscricao.gestaousuario.entity;

import com.tech.challenge.inscricao.gestaousuario.dto.PerfilDTO;
import jakarta.persistence.Embeddable;

@Embeddable
public class Perfil {

    private Long id;

    public Perfil() {
    }

    public Perfil(PerfilDTO perfilDTO) {
        this.id = perfilDTO.id();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
