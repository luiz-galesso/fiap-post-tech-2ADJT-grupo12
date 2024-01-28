package com.fase2.techchallenge.fiap.estacionamento.gestaoestacionamento.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class NotificacaoRequestDTO
{
    private Long idVeiculo;

    private LocalDateTime dataHora;

    private String conteudo;

}
