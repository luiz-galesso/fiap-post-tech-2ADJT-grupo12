package com.tech.challenge.processoseletivo.gestaoentrevista.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.hibernate.validator.constraints.br.CPF;

import java.time.LocalDate;

public record EntrevistaRequestDTO(
        Long id,

        @CPF(message = "CPF Invalido")
        String entrevistador,

        @NotNull(message = "Data da Entrevista não pode estar em branco")
        LocalDate dataEntrevista,

        @NotBlank(message = "Local não pode estar em branco")
        String local,

        @CPF(message = "CPF Invalido")
        String candidato,

        @NotNull(message = "Vaga não pode estar em branco")
        Long vaga,

        @NotNull(message = "Etapa não pode estar em branco")
        Long etapa


) {
}
