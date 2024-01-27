package com.fase2.techchallenge.fiap.pagamento.gestaorecibo.service;

import com.fase2.techchallenge.fiap.pagamento.gestaopagamento.controller.exception.ControllerNotFoundException;
import com.fase2.techchallenge.fiap.pagamento.gestaopagamento.entity.Pagamento;
import com.fase2.techchallenge.fiap.pagamento.gestaopagamento.repository.PagamentoRepository;
import com.fase2.techchallenge.fiap.pagamento.gestaorecibo.dto.CondutorDTO;
import com.fase2.techchallenge.fiap.pagamento.gestaorecibo.dto.SolicitacaoReciboDTO;
import com.fase2.techchallenge.fiap.pagamento.gestaorecibo.dto.VeiculoDTO;
import com.fase2.techchallenge.fiap.pagamento.gestaorecibo.entity.DadosCondutor;
import com.fase2.techchallenge.fiap.pagamento.gestaorecibo.entity.DadosPagamento;
import com.fase2.techchallenge.fiap.pagamento.gestaorecibo.entity.DadosVeiculo;
import com.fase2.techchallenge.fiap.pagamento.gestaorecibo.entity.Recibo;
import com.fase2.techchallenge.fiap.pagamento.gestaorecibo.enumeration.ReciboSituacao;
import com.fase2.techchallenge.fiap.pagamento.gestaorecibo.feign.CadastroClient;
import com.fase2.techchallenge.fiap.pagamento.gestaorecibo.repository.ReciboRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Date;
import java.util.List;

@Service
@EnableScheduling
public class ReciboService {

    final
    PagamentoRepository pagamentoRepository;
    final
    CadastroClient cadastroClient;
    private final ReciboRepository reciboRepository;

    public ReciboService(ReciboRepository reciboRepository, PagamentoRepository pagamentoRepository, CadastroClient cadastroClient) {
        this.reciboRepository = reciboRepository;
        this.pagamentoRepository = pagamentoRepository;
        this.cadastroClient = cadastroClient;
    }

    private Recibo toEntity(SolicitacaoReciboDTO solicitacaoReciboDTO) {
        return new Recibo(new DadosPagamento(solicitacaoReciboDTO.idPagamento()));
    }

    private SolicitacaoReciboDTO toReciboDTO(Recibo recibo) {
        return new SolicitacaoReciboDTO(
                recibo.getId(),
                recibo.getDadosPagamento().getIdPagamento(),
                recibo.getReciboSituacao().toString()
        );
    }

    public SolicitacaoReciboDTO gerarRecibo(SolicitacaoReciboDTO solicitacaoReciboDTO) {

        Recibo recibo = toEntity(solicitacaoReciboDTO);
        recibo.setReciboSituacao(ReciboSituacao.SOLICITADO);
        recibo = reciboRepository.save(recibo);
        return toReciboDTO(recibo);
    }

    @Scheduled(fixedDelay = 1000)
    public void preencherDetalhes() {
        List<Recibo> recibos = reciboRepository.findByReciboSituacao(ReciboSituacao.SOLICITADO);
        for (Recibo recibo : recibos) {
            Pagamento pagamento = pagamentoRepository.findById(recibo.getDadosPagamento().getIdPagamento()).orElseThrow(() -> new ControllerNotFoundException("Pagamento não localizado"));
            ;
            recibo.getDadosPagamento().setValor(pagamento.getValor());
            System.out.println("......................DataPagamento:" + pagamento.getDataHoraPagamento());
            recibo.getDadosPagamento().setDataHoraPagamento(pagamento.getDataHoraPagamento());

            if (recibo.getDadosCondutor() == null) {
                CondutorDTO condutorDTO = cadastroClient.getCondutor(pagamento.getIdCondutor());
                recibo.setDadosCondutor(new DadosCondutor(condutorDTO.email(), condutorDTO.dadosPessoais().nome(), condutorDTO.dadosPessoais().cpf()));
            }

            if (recibo.getDadosVeiculo() == null) {
                VeiculoDTO veiculoDTO = cadastroClient.getVeiculo(pagamento.getIdVeiculo());
                recibo.setDadosVeiculo(new DadosVeiculo(veiculoDTO.placa(), veiculoDTO.nome()));
            }


            recibo.setReciboSituacao(ReciboSituacao.GERADO);
            reciboRepository.save(recibo);
        }

    }

    public Recibo imprimirRecibo(Long id) {
        try {
            Recibo recibo = reciboRepository.findById(id).orElseThrow(() -> new ControllerNotFoundException("Recibo não localizado"));
            if (recibo.getReciboSituacao() != ReciboSituacao.GERADO && recibo.getReciboSituacao() != ReciboSituacao.IMPRESSO) {
                throw new RuntimeException("Recibo não gerado ou Cancelado, tente novamente mais tarde!");
            }
            recibo.setDataImpressaoRecibo(Date.from(Instant.now()));
            recibo.setReciboSituacao(ReciboSituacao.IMPRESSO);
            recibo = reciboRepository.save(recibo);
            return recibo;
        } catch (
                EntityNotFoundException e) {
            throw new ControllerNotFoundException("Recibo não localizado");
        }

    }

    public Recibo cancelarRecibo(Long idPagamento) {
        try {

            Recibo recibo = reciboRepository.findByDadosPagamento_IdPagamento(idPagamento);
            if (recibo.getReciboSituacao() == ReciboSituacao.CANCELADO) {
                throw new RuntimeException("Recibo já esta cancelado");
            }
            recibo.setReciboSituacao(ReciboSituacao.CANCELADO);
            recibo = reciboRepository.save(recibo);
            return (recibo);
        } catch (EntityNotFoundException e) {
            throw new ControllerNotFoundException("Recibo não encontrado");
        }
    }

}
