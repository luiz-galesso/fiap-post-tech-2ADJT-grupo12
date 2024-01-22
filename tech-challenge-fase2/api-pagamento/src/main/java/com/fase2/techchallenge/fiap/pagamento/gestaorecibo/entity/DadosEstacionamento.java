package com.fase2.techchallenge.fiap.pagamento.gestaorecibo.entity;

import jakarta.persistence.Embeddable;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

import java.util.Date;
@Embeddable
public class DadosEstacionamento {
    private String tipoEstacionamento;
    @Temporal(TemporalType.DATE)
    private Date dataInicioEstacionamento;
    // @NotNull(message = "A data de termino do estacionamento, é obrigatório.")
    @Temporal(TemporalType.DATE)
    private Date dataTerminoEstacionamento;

    public DadosEstacionamento() {
    }

    public DadosEstacionamento(String tipoEstacionamento, Date dataInicioEstacionamento, Date dataTerminoEstacionamento) {
        this.tipoEstacionamento = tipoEstacionamento;
        this.dataInicioEstacionamento = dataInicioEstacionamento;
        this.dataTerminoEstacionamento = dataTerminoEstacionamento;
    }

    public String getTipoEstacionamento() {
        return tipoEstacionamento;
    }

    public void setTipoEstacionamento(String tipoEstacionamento) {
        this.tipoEstacionamento = tipoEstacionamento;
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
}
