package com.fase2.techchallenge.fiap.cadastro.condutor.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.validation.Valid;

@JsonInclude(JsonInclude.Include.NON_NULL)
public record CondutorUpdateDTO(
        @Valid
        DadosPessoaisDTO dadosPessoais,
        @Valid
        EnderecoDTO endereco,

        Boolean ativacaoAutomatica

) {
}
