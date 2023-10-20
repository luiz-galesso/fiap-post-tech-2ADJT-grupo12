package com.tech.challenge.inscricao.gestaousuario.dto;

public record AtualizarCandidatoDTO(
        String nomeCompleto,
        DadosPessoaisDTO dadosPessoais,
        UsuarioDTO usuario

) {
}
