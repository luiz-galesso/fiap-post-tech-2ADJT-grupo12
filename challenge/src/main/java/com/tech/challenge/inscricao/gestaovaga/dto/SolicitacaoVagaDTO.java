package com.tech.challenge.inscricao.gestaovaga.dto;

import java.util.Date;

public record SolicitacaoVagaDTO(
    String titulo,
    String descricao,

    Integer quantidade,

    String idSolicitante,

    String nivel,

    Date dataExpiracao,

    Long carreira

) { }
