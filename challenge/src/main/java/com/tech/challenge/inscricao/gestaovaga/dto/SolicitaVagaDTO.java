package com.tech.challenge.inscricao.gestaovaga.dto;

import java.util.Date;

public record SolicitaVagaDTO(
    String titulo,
    String descricao,

    Integer quantidade,

    String idSolicitante,

    String nivel,

    Date dataExpiracao
) { }
