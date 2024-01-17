package com.fase2.techchallenge.fiap.cadastro.condutor.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
@JsonInclude(JsonInclude.Include.NON_NULL)
public record EnderecoDTO(
        String descricao,
        String numero,
        String cidade,
        String estado,
        @NotNull(message = "O CEP do condutor é obrigatório")
        String CEP,
        String complemento) {
}
