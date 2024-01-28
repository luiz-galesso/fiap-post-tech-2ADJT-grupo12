package com.fase2.techchallenge.fiap.estacionamento.gestaoestacionamento.service;

import com.fase2.techchallenge.fiap.estacionamento.exception.WebServiceException;
import com.fase2.techchallenge.fiap.estacionamento.gestaoestacionamento.dto.NotificacaoRequestDTO;
import com.fase2.techchallenge.fiap.estacionamento.gestaoestacionamento.feign.NotificacaoClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class NotificacaoService {

    @Autowired
    NotificacaoClient notificacaoClient;

    public void enviaNotificacao(Long idVeiculo, String conteudo) {
        try {
            notificacaoClient.postNotificacao(new NotificacaoRequestDTO(idVeiculo, LocalDateTime.now(),conteudo));
        } catch (Exception e){
            throw new WebServiceException("Erro ao enviar notificação "+ e.getMessage());
        }
    }
}
