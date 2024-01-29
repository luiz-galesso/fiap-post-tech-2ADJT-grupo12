package com.fase2.techchallenge.fiap.fiscalizacao.gestaofiscalizacao.service;

import com.fase2.techchallenge.fiap.fiscalizacao.gestaofiscalizacao.dto.FiscalizacaoResponseDTO;
import com.fase2.techchallenge.fiap.fiscalizacao.gestaofiscalizacao.model.Estacionamento;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class FiscalizacaoService {

    @Autowired
    EstacionamentoService estacionamentoService;

    public FiscalizacaoResponseDTO fiscalizar(String placa) {
        Optional<Estacionamento> estacionamentoOptional = estacionamentoService.getEstacionamentoPelaPlaca(placa);
        if (estacionamentoOptional.isEmpty()){
            return new FiscalizacaoResponseDTO("IRREGULAR", null, null, "Veículo em situação irregular passível de autuação. ");
        }else{
            return new FiscalizacaoResponseDTO("REGULAR", estacionamentoOptional.get().getDataHoraInicio(), estacionamentoOptional.orElseThrow().getDataHoraVencimento(), "Veículo em situação regular. ");

        }

    }
}
