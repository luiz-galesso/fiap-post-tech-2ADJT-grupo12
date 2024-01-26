package com.fase2.techchallenge.fiap.cadastro.meiopagamentocondutor.dto;

import java.util.Date;

public record MeioPagamentoCondutorResponseDTO(
        Long id,
        String tipoMeioPagamento,

        Long numeroCartao,

        Date validadeCartao,

        String emailCondutor

) {
}
