package com.tech.challenge.acesso.gestaousuario.dto;


public record AtualizarUsuarioDTO(
        String nomeCompleto,

        String nomeUsuario,

        DadosPessoaisDTO dadosPessoais,
        PerfilDTO perfil

) {
}
