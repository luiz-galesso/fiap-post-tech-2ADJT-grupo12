package com.fase2.techchallenge.fiap.pagamento.gestaopagamento.dto;

import com.fase2.techchallenge.fiap.pagamento.gestaopagamento.enumeration.PagamentoSituacao;

import java.util.Date;

public record RetornoPagamentoDTO(
        Long idPagamento,
        PagamentoSituacao pagamentoSituacao,

        Date dataHoraPagamento,
        Long idVeiculo,
        String idCondutor,
        Long idMeioPagamentoCondutor,
        Double valor,
        Long idEstacionamento,
        Long idRecibo,
        String motivoEstorno,
        Date dataHoraEstorno
) {
}
