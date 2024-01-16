package com.fase2.techchallenge.fiap.cadastro.condutor.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record EnderecoDTO(
        String descricao,
        String numero,
        String cidade,
        String estado,
        @NotNull(message = "O CEP do condutor é obrigatório")
        @Valid
        String CEP,
        String complemento) {
}
