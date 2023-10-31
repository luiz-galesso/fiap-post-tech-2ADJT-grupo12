package com.tech.challenge.acesso.gestaousuario.dto;

public record UsuarioDTO(
        String nomeUsuario,
        String cpf,
        String nomeCompleto,
        DadosPessoaisDTO dadosPessoais,
        PerfilDTO perfil) {
}
