package com.fase2.techchallenge.fiap.gestaonotificacao.dto;

import java.time.LocalDateTime;

public record NotificacaoDTO(Long id,
                             Integer idVeiculo,
                             LocalDateTime dataHora,
                             String conteudo,
                             String situacao
) {
}
