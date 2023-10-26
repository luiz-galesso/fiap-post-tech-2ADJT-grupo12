package com.tech.challenge.inscricao.gestaousuario.dto;

import com.tech.challenge.inscricao.gestaousuario.entity.Perfil;

public record UsuarioDTO(
        String nomeUsuario,
        String cpf,
        String nomeCompleto,
        DadosPessoaisDTO dadosPessoais,
        PerfilDTO perfil,

        int status) {
}
