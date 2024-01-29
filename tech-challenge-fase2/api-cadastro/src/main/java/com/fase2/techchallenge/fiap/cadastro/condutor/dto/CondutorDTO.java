package com.fase2.techchallenge.fiap.cadastro.condutor.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

@JsonInclude(JsonInclude.Include.NON_NULL)
public record CondutorDTO(
        @NotNull(message = "O email do Condutor é obrigatório")
        String email,
        @Valid
        DadosPessoaisDTO dadosPessoais,
        @Valid
        EnderecoDTO endereco,
        boolean ativacaoAutomatica

) {
}
