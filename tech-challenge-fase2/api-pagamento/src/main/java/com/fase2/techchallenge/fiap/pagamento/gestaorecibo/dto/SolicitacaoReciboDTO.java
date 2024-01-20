package com.fase2.techchallenge.fiap.pagamento.gestaorecibo.dto;

public record SolicitacaoReciboDTO(
        Long idRecibo,
        Long idPagamento,
        String situacaoRecibo
) {
}
