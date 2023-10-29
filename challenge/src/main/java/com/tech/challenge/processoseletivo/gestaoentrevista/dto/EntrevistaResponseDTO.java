package com.tech.challenge.processoseletivo.gestaoentrevista.dto;

import java.time.LocalDate;

public record EntrevistaResponseDTO(

        Long id,
        String entrevistador,
        LocalDate dataEntrevista,
        String local,
        String candidato
) {
}
