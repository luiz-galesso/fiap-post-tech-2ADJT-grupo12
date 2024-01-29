package com.fase2.techchallenge.fiap.fiscalizacao.gestaofiscalizacao.service;

import com.fase2.techchallenge.fiap.fiscalizacao.gestaofiscalizacao.model.Estacionamento;
import com.fase2.techchallenge.fiap.fiscalizacao.gestaofiscalizacao.repository.EstacionamentoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@EnableScheduling
public class EstacionamentoService {

    @Autowired
    EstacionamentoRepository estacionamentoRepository;



    public Optional<Estacionamento> getEstacionamentoPelaPlaca(String placa){
        return estacionamentoRepository.findByPlacaAndSituacao(placa, "ATIVO");
    }

}
