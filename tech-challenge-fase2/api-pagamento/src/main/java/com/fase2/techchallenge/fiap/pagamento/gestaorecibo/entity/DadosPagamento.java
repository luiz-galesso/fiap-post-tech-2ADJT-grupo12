package com.fase2.techchallenge.fiap.pagamento.gestaorecibo.entity;

import jakarta.persistence.Embeddable;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.validation.constraints.NotNull;

import java.util.Date;

@Embeddable
public class DadosPagamento {
    @NotNull(message = "O código do pagamento do qual o recibo é referente, é obrigatório.")
    private Long idPagamento;
    private Double valor;
    @Temporal(TemporalType.TIMESTAMP)
    private Date dataHoraPagamento;
    private String meioPagamento;

    public DadosPagamento(Long idPagamento) {
        this.idPagamento = idPagamento;
    }

    public DadosPagamento() {

    }


    public DadosPagamento(Long idPagamento, Double valor, Date dataHoraPagamento, String meioPagamento) {
        this.idPagamento = idPagamento;
        this.valor = valor;
        this.dataHoraPagamento = dataHoraPagamento;
        this.meioPagamento = meioPagamento;
    }

    public Long getIdPagamento() {
        return idPagamento;
    }

    public void setIdPagamento(Long idPagamento) {
        this.idPagamento = idPagamento;
    }

    public Double getValor() {
        return valor;
    }

    public void setValor(Double valor) {
        this.valor = valor;
    }

    public Date getDataHoraPagamento() {
        return dataHoraPagamento;
    }

    public void setDataHoraPagamento(Date dataHoraPagamento) {
        this.dataHoraPagamento = dataHoraPagamento;
    }

    public String getMeioPagamento() {
        return meioPagamento;
    }

    public void setMeioPagamento(String meioPagamento) {
        this.meioPagamento = meioPagamento;
    }
}
