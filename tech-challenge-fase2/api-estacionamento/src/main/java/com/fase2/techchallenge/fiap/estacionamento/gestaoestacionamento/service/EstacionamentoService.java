package com.fase2.techchallenge.fiap.estacionamento.gestaoestacionamento.service;

import com.fase2.techchallenge.fiap.estacionamento.gestaoestacionamento.dto.EstacionamentoRequestDTO;
import com.fase2.techchallenge.fiap.estacionamento.gestaoestacionamento.model.Estacionamento;
import com.fase2.techchallenge.fiap.estacionamento.gestaoestacionamento.repository.EstacionamentoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.util.Date;
import java.util.List;

@Service
@EnableScheduling
public class EstacionamentoService {

    @Autowired
    EstacionamentoRepository estacionamentoRepository;

    @Autowired
    TarifaService tarifaService;

    @Autowired
    NotificacaoService notificacaoService;

    public Estacionamento inserir(EstacionamentoRequestDTO estacionamentoRequestDTO){
        Estacionamento estacionamento = estacionamentoRequestDTO.toDocument();
        estacionamento.setValorTarifa(tarifaService.getTarifa().valor());
        return estacionamentoRepository.save(estacionamento);
    }

    public void atualizaNotificadoVencimento(Estacionamento estacionamento, boolean notificadoVencimento){
        estacionamento.setNotificadoVencimento(notificadoVencimento);
        Estacionamento estacionamentoLocal = estacionamentoRepository.save(estacionamento);
    }

    public Estacionamento get(String id){
        return estacionamentoRepository.findById(id).get();
    }

    public List<Estacionamento> getParaVencerNaoNotificado()
    {
        return estacionamentoRepository.findEstacionamentoBydataHoraVencimentoBetweenAndNotificadoVencimento(LocalDateTime.now().plusMinutes(5), LocalDateTime.now(), false );
    }

    @Scheduled(fixedDelay = 60000)
    public void NotificaAVencer()
    {
        List<Estacionamento> estacionamentoList = estacionamentoRepository.findEstacionamentoBydataHoraVencimentoBetweenAndNotificadoVencimento(LocalDateTime.now().plusMinutes(5), LocalDateTime.now(), false );
        estacionamentoList.stream().forEach(estacionamento ->
            {   try {
                notificacaoService.enviaNotificacao(estacionamento.getIdVeiculo(),"Seu tempo de estacionamento irá vencer em "
                        + estacionamento.getDataHoraVencimento());
                atualizaNotificadoVencimento(estacionamento, true);
            } catch (Exception e){
                System.out.println(e.getMessage());
            }
        });
    }


}
