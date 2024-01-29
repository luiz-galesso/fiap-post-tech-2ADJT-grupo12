package com.fase2.techchallenge.fiap.fiscalizacao.gestaofiscalizacao.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Optional;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FiscalizacaoResponseDTO {

    private String status;

    private LocalDateTime dataHoraInicio;

    private LocalDateTime dataHoraVencimento;

    private String mensagem;

}
