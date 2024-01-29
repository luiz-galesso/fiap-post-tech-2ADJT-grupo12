package com.fase2.techchallenge.fiap.estacionamento.gestaoestacionamento.service;

import com.fase2.techchallenge.fiap.estacionamento.exception.WebServiceException;
import com.fase2.techchallenge.fiap.estacionamento.gestaoestacionamento.dto.NotificacaoRequestDTO;
import com.fase2.techchallenge.fiap.estacionamento.gestaoestacionamento.dto.PagamentoRequestDTO;
import com.fase2.techchallenge.fiap.estacionamento.gestaoestacionamento.dto.TarifaDTO;
import com.fase2.techchallenge.fiap.estacionamento.gestaoestacionamento.feign.NotificacaoClient;
import com.fase2.techchallenge.fiap.estacionamento.gestaoestacionamento.feign.PagamentoClient;
import com.fase2.techchallenge.fiap.estacionamento.gestaoestacionamento.feign.TarifaClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class PagamentoService {

    @Autowired
    PagamentoClient pagamentoClient;

    public void enviaPagamento(Long idVeiculo, String idCondutor, Long idMeioPagamentoCondutor, Double valor, String idEstacionamento) {
        try {
            pagamentoClient.postPagamento(new PagamentoRequestDTO(idVeiculo, idCondutor, idMeioPagamentoCondutor, valor, idEstacionamento));
        } catch (Exception e){
            throw new WebServiceException("Erro ao enviar pagamento "+ e.getMessage());
        }
    }
}

