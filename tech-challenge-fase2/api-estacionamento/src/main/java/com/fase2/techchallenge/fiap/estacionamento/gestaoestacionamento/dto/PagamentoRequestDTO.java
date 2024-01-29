package com.fase2.techchallenge.fiap.estacionamento.gestaoestacionamento.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PagamentoRequestDTO {
    private Long idVeiculo;
    private String idCondutor;
    private Long idMeioPagamentoCondutor;
    private Double valor;
    private String idEstacionamento;
}
