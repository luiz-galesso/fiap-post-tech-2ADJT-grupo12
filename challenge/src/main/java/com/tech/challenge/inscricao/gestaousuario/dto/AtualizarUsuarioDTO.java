package com.tech.challenge.inscricao.gestaousuario.dto;

import com.tech.challenge.inscricao.gestaoperfil.dto.PerfilRequestDTO;

public record AtualizarUsuarioDTO(
        String nomeCompleto,
        DadosPessoaisDTO dadosPessoais,
        PerfilRequestDTO usuario

) {
}
