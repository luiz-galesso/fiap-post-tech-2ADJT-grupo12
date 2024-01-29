package com.fase2.techchallenge.fiap.gestaonotificacao.dto;

import java.time.LocalDateTime;

public record NotificacaoResponseDTO(Long id,
                                     VeiculoDTO veiculo,
                                     LocalDateTime dataHora,
                                     String conteudo,
                                     String situacao) {
}
