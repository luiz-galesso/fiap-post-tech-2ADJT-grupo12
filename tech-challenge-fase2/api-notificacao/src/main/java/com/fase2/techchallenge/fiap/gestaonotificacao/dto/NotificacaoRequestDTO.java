package com.fase2.techchallenge.fiap.gestaonotificacao.dto;

import java.time.LocalDateTime;

public record NotificacaoRequestDTO(Long id,
                                    Integer idVeiculo,
                                    LocalDateTime dataHora,
                                    String conteudo
) {
}
