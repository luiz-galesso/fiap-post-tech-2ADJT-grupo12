package com.tech.challenge.processoseletivo.gestaofeedback.dto;

public record FeedbackDTO(
        String idCandidato,
        Long idVaga,
        Long idEtapa,
        String conteudoFeedback,
        String idAutor
) {

}
