package com.fase2.techchallenge.fiap.fiscalizacao.gestaofiscalizacao.dto;

import com.fase2.techchallenge.fiap.fiscalizacao.gestaoestacionamento.model.Estacionamento;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Optional;

@Data
@NoArgsConstructor
public class FiscalizacaoRequestDTO {

    private String idCondutor;

    private Long idVeiculo;

    private Long idMeioPagamento;

    private String tipo;

    private Integer  quantidadeHoras = 0;

    private Boolean renovacaoAutomatica;

    public FiscalizacaoRequestDTO(String idCondutor, Long idVeiculo, Long idMeioPagamento, String tipo, Integer quantidadeHoras, Boolean renovacaoAutomatica) {
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
