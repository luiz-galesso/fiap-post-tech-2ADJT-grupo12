package com.tech.challenge.inscricao.gestaovaga.service.filtros;

import com.tech.challenge.inscricao.gestaovaga.enumeration.Nivel;

public class SolicitacaoVagaFiltro{

    private String idSolicitante;

    public String getIdAvaliador() {
        return idAvaliador;
    }

    public void setIdAvaliador(String idAvaliador) {
        this.idAvaliador = idAvaliador;
    }
    public String getNivel() {
        return nivel;
    }


    public void setNivel(String nivel) {
        this.nivel = nivel;
    }

    private String idAvaliador;

    private String situacao;

    private String nivel;
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
    public SolicitacaoVagaFiltro(String idSolicitante, String idAvaliador, String nivel, String situacao) {
        this.idSolicitante = idSolicitante;
        this.idAvaliador   = idAvaliador;
        this.situacao = situacao;
        this.nivel    = nivel;
    }
}
