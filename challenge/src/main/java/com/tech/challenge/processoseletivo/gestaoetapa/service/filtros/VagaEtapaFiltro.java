package com.tech.challenge.processoseletivo.gestaoetapa.service.filtros;

public class VagaEtapaFiltro {

    private Long idVaga;

    public Long idEtapa;

    public Long getIdVaga() {
        return idVaga;
    }

    public void setIdVaga(Long idVaga) {
        this.idVaga = idVaga;
    }

    public Long getIdEtapa() {
        return idEtapa;
    }

    public void setIdEtapa(Long idEtapa) {
        this.idEtapa = idEtapa;
    }

    public VagaEtapaFiltro(Long idVaga, Long idEtapa) {
        this.idVaga = idVaga;
        this.idEtapa = idEtapa;
    }
}
