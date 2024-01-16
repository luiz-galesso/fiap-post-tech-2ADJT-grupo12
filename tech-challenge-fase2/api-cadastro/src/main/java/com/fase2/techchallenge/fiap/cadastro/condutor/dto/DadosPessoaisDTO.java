package com.fase2.techchallenge.fiap.cadastro.condutor.dto;

import jakarta.validation.constraints.NotNull;
import org.hibernate.validator.constraints.br.CPF;

public record DadosPessoaisDTO(

        @NotNull(message = "O nome do Condutor é obrigatório")
        String nome,
        @CPF
        @NotNull(message = "O cpf do Condutor é obrigatório")
        String cpf,
        @NotNull(message = "O número de celular do Condutor é obrigatório")
        String nrCelular
) {
}
