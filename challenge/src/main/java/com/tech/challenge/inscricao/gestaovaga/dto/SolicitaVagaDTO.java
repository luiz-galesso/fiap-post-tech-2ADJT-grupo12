package com.tech.challenge.inscricao.gestaovaga.dto;

public record SolicitaVagaDTO(
    String titulo,
    String descricao,

    Integer quantidade,

    String idSolicitante,

    String nivel
) { }
