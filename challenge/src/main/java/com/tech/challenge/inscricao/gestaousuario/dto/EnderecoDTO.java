package com.tech.challenge.inscricao.gestaousuario.dto;

import com.tech.challenge.inscricao.gestaousuario.entity.Endereco;

public record EnderecoDTO(
        Integer cep,
        String numero,
        String complemento
) {
}
