package com.fase2.techchallenge.fiap.pagamento.gestaopagamento.dto;

public record RetornoSolicitacaoReciboDTO(Long idRecibo,
                                          Long idPagamento,
                                          String situacaoRecibo) {
}
