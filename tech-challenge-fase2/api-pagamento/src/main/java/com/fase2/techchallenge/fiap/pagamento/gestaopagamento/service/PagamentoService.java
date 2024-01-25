package com.fase2.techchallenge.fiap.pagamento.gestaopagamento.service;

import com.fase2.techchallenge.fiap.pagamento.gestaopagamento.controller.exception.ControllerNotFoundException;
import com.fase2.techchallenge.fiap.pagamento.gestaopagamento.dto.*;
import com.fase2.techchallenge.fiap.pagamento.gestaopagamento.entity.Pagamento;
import com.fase2.techchallenge.fiap.pagamento.gestaopagamento.enumeration.PagamentoSituacao;
import com.fase2.techchallenge.fiap.pagamento.gestaopagamento.feign.ReciboClient;
import com.fase2.techchallenge.fiap.pagamento.gestaopagamento.repository.PagamentoRepository;
import com.fase2.techchallenge.fiap.pagamento.gestaorecibo.dto.RetornoCancelamentoReciboDTO;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Date;

@Service
public class PagamentoService {
    private final PagamentoRepository pagamentoRepository;
    private final ReciboClient reciboClient;

    public PagamentoService(PagamentoRepository pagamentoRepository, ReciboClient reciboClient) {
        this.pagamentoRepository = pagamentoRepository;
        this.reciboClient = reciboClient;

    }


    public RetornoPagamentoDTO gerarPagamento(EnvioPagamentoDTO envioPagamentoDTO) {
        RetornoPagamentoDTO retornoPagamentoDTO;
        Pagamento pagamento = toEntity(envioPagamentoDTO);
        pagamento.setPagamentoSituacao(PagamentoSituacao.PENDENTE);
        if (realizaPagamento(pagamento)) {
            pagamento.setDataHoraPagamento(Date.from(Instant.now()));
            pagamento.setPagamentoSituacao(PagamentoSituacao.APROVADO);


        } else {
            pagamento.setPagamentoSituacao(PagamentoSituacao.RECUSADO);
        }

        pagamento = pagamentoRepository.save(pagamento);

        if (pagamento.getPagamentoSituacao() == PagamentoSituacao.APROVADO) {
            RetornoSolicitacaoReciboDTO retornoSolicitacaoReciboDTO = reciboClient.solicitaRecibo(new SolicitacaReciboDTO(pagamento.getId()));
            retornoPagamentoDTO = toRetornoPagamentoDTO(pagamento, retornoSolicitacaoReciboDTO.idRecibo());
        } else {
            retornoPagamentoDTO = toRetornoPagamentoDTO(pagamento, null);
        }
        return retornoPagamentoDTO;
    }

    private Boolean realizaPagamento(Pagamento pagamento) {
        /* TODO - Efetivar Pagamento com o gateway de pagamento*/
        return true;
    }

    public Pagamento findById(Long id) {
        try {
            return pagamentoRepository.findById(id).orElseThrow(() -> new ControllerNotFoundException("Pagamento não localizado"));
        } catch (EntityNotFoundException e) {
            throw new ControllerNotFoundException("Pagamento não localizado");
        }
    }

    private EnvioPagamentoDTO toEnvioPagamentoDTO(Pagamento pagamento) {
        return new EnvioPagamentoDTO(
                pagamento.getIdVeiculo(),
                pagamento.getIdCondutor(),
                pagamento.getIdMeioPagamentoCondutor(),
                pagamento.getValor(),
                pagamento.getIdEstacionamento());
    }

    private RetornoPagamentoDTO toRetornoPagamentoDTO(Pagamento pagamento, Long idRecibo) {
        return new RetornoPagamentoDTO(pagamento.getId(),
                pagamento.getPagamentoSituacao(),
                pagamento.getDataHoraPagamento(),
                pagamento.getIdVeiculo(),
                pagamento.getIdCondutor(),
                pagamento.getIdMeioPagamentoCondutor(),
                pagamento.getValor(),
                pagamento.getIdEstacionamento(),
                idRecibo, pagamento.getMotivoEstorno(),
                pagamento.getDataHoraEstorno());
    }


    private Pagamento toEntity(EnvioPagamentoDTO envioPagamentoDTO) {
        return new Pagamento(envioPagamentoDTO.idVeiculo(),
                envioPagamentoDTO.idCondutor(),
                envioPagamentoDTO.idMeioPagamentoCondutor(),
                envioPagamentoDTO.valor(),
                envioPagamentoDTO.idEstacionamento()
        );
    }


    private Boolean realizaEstorno(Long idPagamento) {
        /* TODO - Estornar Pagamento com o gateway de pagamento*/
        return true;
    }

    public RetornoPagamentoDTO estornarPagamento(Long idPagamento, EstornarPagamentoDTO estornarPagamentoDTO) {
        try {
            Pagamento pagamento = pagamentoRepository.getReferenceById(idPagamento);
            if (pagamento.getPagamentoSituacao() == PagamentoSituacao.APROVADO) {
                if (realizaEstorno(idPagamento)) {
                    pagamento.setDataHoraEstorno(Date.from(Instant.now()));
                    pagamento.setMotivoEstorno(estornarPagamentoDTO.motivoEstorno());
                    pagamento.setPagamentoSituacao(PagamentoSituacao.CANCELADO);
                    pagamento = pagamentoRepository.save(pagamento);
                    RetornoCancelamentoReciboDTO retornoCancelamentoReciboDTO = reciboClient.cancelarRecibo(idPagamento);
                    return toRetornoPagamentoDTO(pagamento, retornoCancelamentoReciboDTO.id());
                } else {
                    throw new RuntimeException("Problemas com efetivação do estorno");
                }
            } else {
                throw new RuntimeException("Pagamento em Situação Inválida");
            }
        } catch (EntityNotFoundException e) {
            throw new ControllerNotFoundException("Pagamento não localizado");
        }

    }

    public EstornarPagamentoDTO toEstornarPagamentoDTO(Pagamento pagamento) {
        return new EstornarPagamentoDTO(pagamento.getMotivoEstorno(), pagamento.getDataHoraEstorno());
    }

}
