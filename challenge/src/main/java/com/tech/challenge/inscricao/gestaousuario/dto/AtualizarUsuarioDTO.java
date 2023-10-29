package com.tech.challenge.inscricao.gestaousuario.dto;


public record AtualizarUsuarioDTO(
        String nomeCompleto,

        String nomeUsuario,

        DadosPessoaisDTO dadosPessoais,
        PerfilDTO perfil

) {
}
