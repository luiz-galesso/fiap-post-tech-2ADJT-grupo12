package com.fase2.techchallenge.fiap.estacionamento.gestaoestacionamento.dto;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import com.fase2.techchallenge.fiap.estacionamento.gestaoestacionamento.model.Estacionamento;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EstacionamentoRequestDTO {

    private String idCondutor;

    private Long idVeiculo;

    private Long idMeioPagamento;

    private String tipo;

    private Integer  quantidadeHoras;

    private Boolean renovacaoAutomatica;

    public Estacionamento toDocument(){

        return new Estacionamento(
                null,
                this.idCondutor,
                this.idVeiculo,
                this.idMeioPagamento,
                null,
                this.tipo,
                "ATIVO",
                LocalDateTime.now(),
                LocalDateTime.now().plusHours(quantidadeHoras),
                null,
                false,
                this.renovacaoAutomatica);
    }
}
