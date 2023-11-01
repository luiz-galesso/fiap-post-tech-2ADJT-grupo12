package com.tech.challenge.acesso.gestaousuario.dto;

import jakarta.validation.constraints.NotBlank;

public record PerfilDTO(
        @NotBlank
        Long id) {

}
