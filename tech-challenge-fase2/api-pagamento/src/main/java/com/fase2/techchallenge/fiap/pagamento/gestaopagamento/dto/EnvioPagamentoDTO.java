package com.fase2.techchallenge.fiap.pagamento.gestaopagamento.dto;

public record EnvioPagamentoDTO(
        Long id,
        Long idVeiculo,
        String idCondutor,
        Long idMeioPagamentoCondutor,
        Double valor,
        Long idEstacionamento
) {
}
