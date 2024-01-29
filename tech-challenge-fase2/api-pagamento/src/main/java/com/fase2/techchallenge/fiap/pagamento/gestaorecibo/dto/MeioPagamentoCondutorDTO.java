package com.fase2.techchallenge.fiap.pagamento.gestaorecibo.dto;

import java.util.Date;

public record MeioPagamentoCondutorDTO(Long id, String tipoMeioPagamento, Long numeroCartao, Date validadeCartao,
                                       String emailCondutor) {
}
