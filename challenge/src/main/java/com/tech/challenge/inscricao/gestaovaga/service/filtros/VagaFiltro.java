package com.tech.challenge.inscricao.gestaovaga.service.filtros;

public class VagaFiltro {

    private String idSolicitante;

    private String situacao;

    private String nivel;

    public String getNivel() {
        return nivel;
    }

    public void setNivel(String nivel) {
        this.nivel = nivel;
    }
    public String getIdSolicitante() {
        return idSolicitante;
    }

    public void setIdSolicitante(String idSolicitante) {
        this.idSolicitante = idSolicitante;
    }

    public String getSituacao() {
        return situacao;
    }

    public void setSituacao(String situacao) {
        this.situacao = situacao;
    }
    public VagaFiltro(String idSolicitante, String nivel, String situacao) {
        this.idSolicitante = idSolicitante;
        this.situacao = situacao;
        this.nivel    = nivel;
    }
}
