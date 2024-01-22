package com.fase2.techchallenge.fiap.pagamento.gestaopagamento.service;

import com.fase2.techchallenge.fiap.pagamento.gestaopagamento.controller.exception.ControllerNotFoundException;
import com.fase2.techchallenge.fiap.pagamento.gestaopagamento.dto.EnvioPagamentoDTO;
import com.fase2.techchallenge.fiap.pagamento.gestaopagamento.entity.Pagamento;
import com.fase2.techchallenge.fiap.pagamento.gestaopagamento.enumeration.PagamentoSituacao;
import com.fase2.techchallenge.fiap.pagamento.gestaopagamento.repository.PagamentoRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Date;

@Service
public class PagamentoService {
    private final PagamentoRepository pagamentoRepository;



    public PagamentoService(PagamentoRepository pagamentoRepository) {
        this.pagamentoRepository = pagamentoRepository;
    }



    public EnvioPagamentoDTO gerarPagamento(EnvioPagamentoDTO envioPagamentoDTO) {
        Pagamento pagamento = toEntity(envioPagamentoDTO);
        pagamento.setPagamentoSituacao(PagamentoSituacao.PENDENTE);
        pagamento.setDataPagamento(Date.from(Instant.now()));
        pagamento = pagamentoRepository.save(pagamento);
        return toEnvioPagamentoDTO(pagamento);
    }

    public Pagamento findById(Long id){
        try {
            return pagamentoRepository.findById(id).orElseThrow(() -> new ControllerNotFoundException("Pagamento não localizado"));
        } catch (EntityNotFoundException e){
            throw new ControllerNotFoundException("Pagamento não localizado");
        }
    }

    private EnvioPagamentoDTO toEnvioPagamentoDTO(Pagamento pagamento) {
        return new EnvioPagamentoDTO(pagamento.getId(),
                pagamento.getIdVeiculo(),
                pagamento.getIdCondutor(),
                pagamento.getIdMeioPagamentoCondutor(),
                pagamento.getValor(),
                pagamento.getIdEstacionamento());
    }

    private Pagamento toEntity(EnvioPagamentoDTO envioPagamentoDTO) {
        return new Pagamento(envioPagamentoDTO.idVeiculo(),
                envioPagamentoDTO.idCondutor(),
                envioPagamentoDTO.idMeioPagamentoCondutor(),
                envioPagamentoDTO.valor(),
                envioPagamentoDTO.iEstacionamento()
        );
    }


}
