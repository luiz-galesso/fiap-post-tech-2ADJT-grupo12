package com.tech.challenge.processoseletivo.gestaofeedback.dto;

public record AtualizarFeedbackDTO (
        String conteudoFeedback,
        String idAutor,
        Long idVaga,
        Long idEtapa,
        String idCandidato

) {


}
