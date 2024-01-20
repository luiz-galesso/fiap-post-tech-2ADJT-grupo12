package com.fase2.techchallenge.fiap.estacionamento.gestaoestacionamento.service;

import com.fase2.techchallenge.fiap.estacionamento.gestaoestacionamento.dto.TarifaDTO;
import com.fase2.techchallenge.fiap.estacionamento.gestaoestacionamento.feign.TarifaClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
@Service
public class TarifaService {

    @Autowired
    private TarifaClient tarifaClient;

    public TarifaDTO getTarifa(){
        TarifaDTO tarifa= tarifaClient.getTarifa("NORMAL");
        return tarifa;
    }
}
