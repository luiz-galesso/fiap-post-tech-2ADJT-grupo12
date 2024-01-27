package com.fase2.techchallenge.fiap.pagamento.gestaopagamento.entity;

import com.fase2.techchallenge.fiap.pagamento.gestaopagamento.enumeration.PagamentoSituacao;
import jakarta.persistence.*;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@Entity
@Table(name = "tb_pagamento")
@AllArgsConstructor
@NoArgsConstructor
public class Pagamento {
    @Id
    @NotNull(message = "O id do pagamento é obrigatório")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "pagamento_generator")
    @SequenceGenerator(name = "pagamento_generator", sequenceName = "pagamento_sequence", allocationSize = 1)
    private Long id;
    @NotNull(message = "O id do veículo é obrigatório")
    private Long idVeiculo;
    @NotNull(message = "O id do condutor é obrigatório")
    private String idCondutor;
    @NotNull(message = "O id do meio de pagamento do condutor é obrigatório")
    private Long idMeioPagamentoCondutor;
    @NotNull(message = "O valor é obrigatório")
    @DecimalMin(value = "0", inclusive = false, message = "O valor deve ser maior do que zero")
    private Double valor;
    private Long idEstacionamento;
    @NotNull(message = "A situação do Pagamento é obrigatória")
    private PagamentoSituacao pagamentoSituacao;
    @NotNull(message = "A data do Pagamento é obrigatória")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dataHoraPagamento;

    private String motivoEstorno;
    @Temporal(TemporalType.TIMESTAMP)
    private Date dataHoraEstorno;

    public Pagamento(Long idVeiculo, String idCondutor, Long idMeioPagamentoCondutor, Double valor, Long idEstacionamento) {
        this.idVeiculo = idVeiculo;
        this.idCondutor = idCondutor;
        this.idMeioPagamentoCondutor = idMeioPagamentoCondutor;
        this.valor = valor;
        this.idEstacionamento = idEstacionamento;
    }
}
