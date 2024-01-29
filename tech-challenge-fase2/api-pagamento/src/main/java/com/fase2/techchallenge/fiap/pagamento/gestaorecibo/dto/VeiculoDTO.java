package com.fase2.techchallenge.fiap.pagamento.gestaorecibo.dto;

public record VeiculoDTO(
        Long id,
        String placa,
        String nome,
        CondutorDTO condutor
) {
}
