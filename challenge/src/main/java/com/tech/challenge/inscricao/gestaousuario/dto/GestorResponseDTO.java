package com.tech.challenge.inscricao.gestaousuario.dto;

public record GestorResponseDTO(

        Long id,
        EnderecoDTO endereco,
        UsuarioDTO usuario
) {
}
