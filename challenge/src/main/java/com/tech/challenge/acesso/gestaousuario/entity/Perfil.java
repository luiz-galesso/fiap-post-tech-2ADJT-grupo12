package com.tech.challenge.acesso.gestaousuario.entity;

import com.tech.challenge.acesso.gestaoperfil.dto.PerfilRequestDTO;
import com.tech.challenge.acesso.gestaousuario.dto.PerfilDTO;
import jakarta.persistence.Embeddable;

@Embeddable
public class Perfil {

    private Long idPerfil;

    public Perfil() {
    }

    public Perfil(PerfilDTO perfilDTO) {
        this.idPerfil = perfilDTO.id();
    }

    public Perfil(PerfilRequestDTO perfilRequestDTO) {
        this.idPerfil = perfilRequestDTO.id();
    }

    public Long getId() {
        return idPerfil;
    }

    public void setId(Long id) {
        this.idPerfil = id;
    }
}
