package com.tech.challenge.inscricao.gestaovaga.dto;

import com.tech.challenge.inscricao.gestaovaga.enumeration.Nivel;

import java.util.Date;

public record SolicitaVagaRespostaDTO(
    String titulo,
    String descricao,
    Integer quantidadeDeVagas,
    Date dataSolicitacao,
    Nivel nivel
) { }
