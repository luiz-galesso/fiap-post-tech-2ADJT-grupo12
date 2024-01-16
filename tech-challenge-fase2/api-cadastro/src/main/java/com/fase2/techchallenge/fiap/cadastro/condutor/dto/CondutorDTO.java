package com.fase2.techchallenge.fiap.cadastro.condutor.dto;

import com.fase2.techchallenge.fiap.cadastro.condutor.entity.DadosPessoais;
import com.fase2.techchallenge.fiap.cadastro.condutor.entity.Endereco;
import jakarta.validation.constraints.NotNull;

public record CondutorDTO(
        @NotNull(message = "O email do Condutor é obrigatório")
        String email,
        DadosPessoaisDTO dadosPessoais,

        EnderecoDTO endereco,

        boolean ativacaoAutomatica

) {
}
