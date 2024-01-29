package com.fase2.techchallenge.fiap.pagamento.gestaorecibo.entity;

import com.fase2.techchallenge.fiap.pagamento.gestaorecibo.enumeration.ReciboSituacao;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

import java.util.Date;

@Entity
@Table(name = "tb_recibo")
public class Recibo {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "recibo_generator")
    @SequenceGenerator(name = "recibo_generator", sequenceName = "recibo_sequence", allocationSize = 1)
    private Long id;
    @NotNull(message = "A situação do recibo, é obrigatório.")
    @Enumerated(EnumType.STRING)
    private ReciboSituacao reciboSituacao;
    @Temporal(TemporalType.TIMESTAMP)
    private Date dataImpressaoRecibo;
    private DadosPagamento dadosPagamento;
    private DadosCondutor dadosCondutor;
    private DadosVeiculo dadosVeiculo;
    private DadosEstacionamento dadosEstacionamento;

    public Recibo() {

    }

    public Recibo(DadosPagamento dadosPagamento) {
        this.dadosPagamento = dadosPagamento;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public ReciboSituacao getReciboSituacao() {
        return reciboSituacao;
    }

    public void setReciboSituacao(ReciboSituacao reciboSituacao) {
        this.reciboSituacao = reciboSituacao;
    }

    public Date getDataImpressaoRecibo() {
        return dataImpressaoRecibo;
    }

    public void setDataImpressaoRecibo(Date dataImpressaoRecibo) {
        this.dataImpressaoRecibo = dataImpressaoRecibo;
    }

    public DadosPagamento getDadosPagamento() {
        return dadosPagamento;
    }

    public void setDadosPagamento(DadosPagamento dadosPagamento) {
        this.dadosPagamento = dadosPagamento;
    }

    public DadosCondutor getDadosCondutor() {
        return dadosCondutor;
    }

    public void setDadosCondutor(DadosCondutor dadosCondutor) {
        this.dadosCondutor = dadosCondutor;
    }

    public DadosVeiculo getDadosVeiculo() {
        return dadosVeiculo;
    }

    public void setDadosVeiculo(DadosVeiculo dadosVeiculo) {
        this.dadosVeiculo = dadosVeiculo;
    }

    public DadosEstacionamento getDadosEstacionamento() {
        return dadosEstacionamento;
    }

    public void setDadosEstacionamento(DadosEstacionamento dadosEstacionamento) {
        this.dadosEstacionamento = dadosEstacionamento;
    }
}
