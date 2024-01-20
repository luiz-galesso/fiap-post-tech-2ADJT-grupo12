package com.fase2.techchallenge.fiap.pagamento.gestaopagamento.entity;

import com.fase2.techchallenge.fiap.pagamento.gestaopagamento.enumeration.PagamentoSituacao;
import jakarta.persistence.*;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;

import java.util.Date;

@Entity
@Table(name="tb_pagamento")
public class Pagamento {
    public Pagamento(Long idVeiculo, String idCondutor, Long idMeioPagamentoCondutor, Long valor, Long idEstacionamento) {
        this.idVeiculo = idVeiculo;
        this.idCondutor = idCondutor;
        this.idMeioPagamentoCondutor = idMeioPagamentoCondutor;
        this.valor = valor;
        this.idEstacionamento = idEstacionamento;
    }

    @Id
    @NotNull(message = "O id do pagamento é obrigatório")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="pagamento_generator")
    @SequenceGenerator(name="pagamento_generator", sequenceName="pagamento_sequence", allocationSize = 1)
    private Long id;
    @NotNull(message = "O id do veículo é obrigatório")
    private Long idVeiculo;
    @NotNull(message = "O id do condutor é obrigatório")
    private String idCondutor;
    @NotNull(message = "O id do meio de pagamento do condutor é obrigatório")
    private Long idMeioPagamentoCondutor;
    @NotNull(message = "O valor é obrigatório")
    @DecimalMin(value="0",inclusive = false,message = "O valor deve ser maior do que zero")
    private Long valor;
    private Long idEstacionamento;
    @NotNull(message = "A situação do Pagamento é obrigatória")
    private PagamentoSituacao pagamentoSituacao;
    @NotNull(message = "A data do Pagamento é obrigatória")
    @Temporal(TemporalType.DATE)
    private Date dataPagamento;

    public Pagamento() {

    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public Long getIdVeiculo() {
        return idVeiculo;
    }

    public void setIdVeiculo(Long idVeiculo) {
        this.idVeiculo = idVeiculo;
    }

    public String getIdCondutor() {
        return idCondutor;
    }

    public void setIdCondutor(String idCondutor) {
        this.idCondutor = idCondutor;
    }

    public Long getIdMeioPagamentoCondutor() {
        return idMeioPagamentoCondutor;
    }

    public void setIdMeioPagamentoCondutor(Long idMeioPagamentoCondutor) {
        this.idMeioPagamentoCondutor = idMeioPagamentoCondutor;
    }

    public Long getValor() {
        return valor;
    }

    public void setValor(Long valor) {
        this.valor = valor;
    }

    public Long getIdEstacionamento() {
        return idEstacionamento;
    }

    public void setIdEstacionamento(Long idEstacionamento) {
        this.idEstacionamento = idEstacionamento;
    }

    public PagamentoSituacao getPagamentoSituacao() {
        return pagamentoSituacao;
    }

    public void setPagamentoSituacao(PagamentoSituacao pagamentoSituacao) {
        this.pagamentoSituacao = pagamentoSituacao;
    }

    public Date getDataPagamento() {
        return dataPagamento;
    }

    public void setDataPagamento(Date dataPagamento) {
        this.dataPagamento = dataPagamento;
    }
}
