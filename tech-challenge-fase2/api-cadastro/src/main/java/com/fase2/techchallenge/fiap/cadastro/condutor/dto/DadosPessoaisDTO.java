package com.fase2.techchallenge.fiap.cadastro.condutor.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.validation.constraints.NotNull;
import org.hibernate.validator.constraints.br.CPF;
@JsonInclude(JsonInclude.Include.NON_NULL)
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
