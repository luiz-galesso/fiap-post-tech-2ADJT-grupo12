package com.tech.challenge.acesso.gestaousuario.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.hibernate.validator.constraints.br.CPF;

public record UsuarioDTO(
        @NotBlank
        String nomeUsuario,
        @CPF
        String cpf,
        @NotBlank
        String nomeCompleto,
        DadosPessoaisDTO dadosPessoais,
        PerfilDTO perfil) {
}
