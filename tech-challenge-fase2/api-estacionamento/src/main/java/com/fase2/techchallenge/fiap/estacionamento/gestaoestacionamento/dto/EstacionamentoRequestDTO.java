package com.fase2.techchallenge.fiap.estacionamento.gestaoestacionamento.dto;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.Optional;
import java.util.concurrent.TimeUnit;

import com.fase2.techchallenge.fiap.estacionamento.gestaoestacionamento.model.Estacionamento;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.*;

@Data
@NoArgsConstructor
public class EstacionamentoRequestDTO {

    private String idCondutor;

    private Long idVeiculo;

    private Long idMeioPagamento;

    private String tipo;

    private Integer  quantidadeHoras = 0;

    private Boolean renovacaoAutomatica;

    public EstacionamentoRequestDTO(String idCondutor, Long idVeiculo, Long idMeioPagamento, String tipo, Integer quantidadeHoras, Boolean renovacaoAutomatica) {
        this.idCondutor = idCondutor;
        this.idVeiculo = idVeiculo;
        this.idMeioPagamento = idMeioPagamento;
        this.tipo = tipo;
        this.quantidadeHoras = Optional.ofNullable(quantidadeHoras).orElse(0);
        this.renovacaoAutomatica = renovacaoAutomatica;
    }

    public Estacionamento toDocument(){

        return new Estacionamento(
                null,
                this.idCondutor,
                this.idVeiculo,
                null,
                this.idMeioPagamento,
                null,
                this.tipo,
                "ATIVO",
                LocalDateTime.now(),
                LocalDateTime.now().plusHours(Optional.ofNullable(quantidadeHoras).orElse(0)),
                null,
                false,
                this.renovacaoAutomatica);
    }

}
