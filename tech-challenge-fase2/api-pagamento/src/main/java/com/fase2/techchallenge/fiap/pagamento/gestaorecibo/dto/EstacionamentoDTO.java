package com.fase2.techchallenge.fiap.pagamento.gestaorecibo.dto;

import java.time.LocalDateTime;

public record EstacionamentoDTO(String id,
                                String idCondutor,
                                Long idVeiculo,
                                Long idMeioPagamento,
                                Double valorTarifa,
                                String tipo,
                                String situacao,
                                LocalDateTime dataHoraInicio,
                                LocalDateTime dataHoraVencimento,
                                LocalDateTime dataHoraTermino,
                                Boolean notificadoVencimento,
                                Boolean renovacaoAutomatica) {
}
