package com.fase2.techchallenge.fiap.estacionamento.gestaoestacionamento.dto;

import java.time.LocalDateTime;
import java.util.Date;

public record MeioPagamentoCondutorDTO(
    Long id,
    String tipoMeioPagamento,
    Long numeroCartao,
    Date validadeCartao,
    String emailCondutor,
    Boolean favorito
) {
}
