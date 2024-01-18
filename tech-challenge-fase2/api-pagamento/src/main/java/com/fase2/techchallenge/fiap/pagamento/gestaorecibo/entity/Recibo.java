package com.fase2.techchallenge.fiap.pagamento.gestaorecibo.entity;

import com.fase2.techchallenge.fiap.pagamento.gestaorecibo.enumeration.ReciboSituacao;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

import java.util.Date;

@Entity
@Table(name="tb_recibo")
public class Recibo {

    public Recibo() {
    }

    public Recibo(Long idPagamento) {
        this.idPagamento = idPagamento;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="recibo_generator")
    @SequenceGenerator(name="vaga_generator", sequenceName="vaga_sequence", allocationSize = 1)
    private Long id;

    @NotNull(message = "O código do pagamento do qual o recibo é referente, é obrigatório.")
    private Long idPagamento;
    private String nomeCondutor;
    private Long cpf;
    private String email;
    private String placaVeiculo;
    private String descricaoVeiculo;
    private String meioPagamento;
    private Long valor;
    @Temporal(TemporalType.DATE)
    private Date dataHoraPagamento;
    private String tipoEstacionamento;
    @NotNull(message = "A situação do recibo, é obrigatório.")
    @Enumerated(EnumType.STRING)
    private ReciboSituacao reciboSituacao;
    //@NotNull(message = "A data de inicio do estacionamento, é obrigatório.")
    @Temporal(TemporalType.DATE)
    private Date dataInicioEstacionamento;
   // @NotNull(message = "A data de termino do estacionamento, é obrigatório.")
    @Temporal(TemporalType.DATE)
    private Date dataTerminoEstacionamento;
    @Temporal(TemporalType.DATE)
    private Date dataImpressaoRecibo;


    public Long getId() {
        return id;
    }

    public Long getIdPagamento() {
        return idPagamento;
    }

    public void setIdPagamento(Long idPagamento) {
        this.idPagamento = idPagamento;
    }

    public ReciboSituacao getReciboSituacao() {
        return reciboSituacao;
    }

    public void setReciboSituacao(ReciboSituacao reciboSituacao) {
        this.reciboSituacao = reciboSituacao;
    }

    public Date getDataInicioEstacionamento() {
        return dataInicioEstacionamento;
    }

    public void setDataInicioEstacionamento(Date dataInicioEstacionamento) {
        this.dataInicioEstacionamento = dataInicioEstacionamento;
    }

    public Date getDataTerminoEstacionamento() {
        return dataTerminoEstacionamento;
    }

    public void setDataTerminoEstacionamento(Date dataTerminoEstacionamento) {
        this.dataTerminoEstacionamento = dataTerminoEstacionamento;
    }

    public Date getDataImpressaoRecibo() {
        return dataImpressaoRecibo;
    }

    public void setDataImpressaoRecibo(Date dataImpressaoRecibo) {
        this.dataImpressaoRecibo = dataImpressaoRecibo;
    }

    public String getNomeCondutor() {
        return nomeCondutor;
    }

    public void setNomeCondutor(String nomeCondutor) {
        this.nomeCondutor = nomeCondutor;
    }

    public Long getCpf() {
        return cpf;
    }

    public void setCpf(Long cpf) {
        this.cpf = cpf;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPlacaVeiculo() {
        return placaVeiculo;
    }

    public void setPlacaVeiculo(String placaVeiculo) {
        this.placaVeiculo = placaVeiculo;
    }

    public String getDescricaoVeiculo() {
        return descricaoVeiculo;
    }

    public void setDescricaoVeiculo(String descricaoVeiculo) {
        this.descricaoVeiculo = descricaoVeiculo;
    }

    public String getMeioPagamento() {
        return meioPagamento;
    }

    public void setMeioPagamento(String meioPagamento) {
        this.meioPagamento = meioPagamento;
    }

    public Long getValor() {
        return valor;
    }

    public void setValor(Long valor) {
        this.valor = valor;
    }

    public Date getDataHoraPagamento() {
        return dataHoraPagamento;
    }

    public void setDataHoraPagamento(Date dataHoraPagamento) {
        this.dataHoraPagamento = dataHoraPagamento;
    }

    public String getTipoEstacionamento() {
        return tipoEstacionamento;
    }

    public void setTipoEstacionamento(String tipoEstacionamento) {
        this.tipoEstacionamento = tipoEstacionamento;
    }
}
