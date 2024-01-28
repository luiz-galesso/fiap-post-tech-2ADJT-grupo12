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
import java.time.temporal.ChronoUnit;
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

    @Autowired
    PagamentoService pagamentoService;

    public Estacionamento inserir(EstacionamentoRequestDTO estacionamentoRequestDTO){
        Estacionamento estacionamento = estacionamentoRequestDTO.toDocument();
        estacionamento.setValorTarifa(tarifaService.getTarifa().valor());
        return estacionamentoRepository.save(estacionamento);
    }

    public void atualizaNotificadoVencimento(Estacionamento estacionamento, boolean notificadoVencimento){
        estacionamento.setNotificadoVencimento(notificadoVencimento);
        Estacionamento estacionamentoLocal = estacionamentoRepository.save(estacionamento);
    }

    public void atualizaDataHoraVencimento(Estacionamento estacionamento, LocalDateTime dataHoraVencimento){
        estacionamento.setDataHoraVencimento(dataHoraVencimento);
        Estacionamento estacionamentoLocal = estacionamentoRepository.save(estacionamento);
    }

    public void atualizaDataHoraTerminoESituacao(Estacionamento estacionamento, LocalDateTime dataHoraTermino, String situacao){
        estacionamento.setDataHoraTermino(dataHoraTermino);
        estacionamento.setSituacao(situacao);
        Estacionamento estacionamentoLocal = estacionamentoRepository.save(estacionamento);
    }

    public Estacionamento get(String id){
        return estacionamentoRepository.findById(id).get();
    }

    @Scheduled(fixedDelay = 60000)
    public void NotificaAVencer()
    {
        List<Estacionamento> estacionamentoList =
                estacionamentoRepository.findEstacionamentoBydataHoraVencimentoBetweenAndNotificadoVencimentoAndRenovacaoAutomaticaAndSituacao
                    (LocalDateTime.now().plusMinutes(5), LocalDateTime.now(), false , false, "ATIVO");
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

    @Scheduled(fixedDelay = 60000)
    public void RenovaAutomaticamente()
    {
        List<Estacionamento> estacionamentoList =
                estacionamentoRepository.findEstacionamentoBydataHoraVencimentoLowerThanAndRenovacaoAutomaticaAndTipoAndSituacao
                        (LocalDateTime.now().plusMinutes(2), true, "VARIAVEL", "ATIVO");
        estacionamentoList.stream().forEach(estacionamento ->
        {   try {
            LocalDateTime novaDataHoraVencimento = estacionamento.getDataHoraVencimento().plusHours(1);
            atualizaDataHoraVencimento(estacionamento, novaDataHoraVencimento);
            notificacaoService.enviaNotificacao(estacionamento.getIdVeiculo(),"Seu tempo de estacionamento foi renovado automaticamente em 1 hora "
                    + novaDataHoraVencimento);

        } catch (Exception e){
            System.out.println(e.getMessage());
        }
        });
    }

    @Scheduled(fixedDelay = 60000)
    public void ExecutaPagamento()
    {
        List<Estacionamento> estacionamentoList =
                estacionamentoRepository.findEstacionamentoBydataHoraVencimentoLowerThanAndRenovacaoAutomaticaAndTipoAndSituacao
                        (LocalDateTime.now().plusMinutes(2), false, "VARIAVEL", "ATIVO");
        estacionamentoList.stream().forEach(estacionamento ->
        {   try {
            LocalDateTime dataHoraTermino = LocalDateTime.now();
            Double valor = estacionamento.getValorTarifa() * ChronoUnit.HOURS.between(estacionamento.getDataHoraInicio(), dataHoraTermino);
            pagamentoService.enviaPagamento(estacionamento.getIdVeiculo(), estacionamento.getIdCondutor(),estacionamento.getIdMeioPagamento(), valor, estacionamento.getId());
            atualizaDataHoraTerminoESituacao(estacionamento, dataHoraTermino, "FINALIZADO");
            notificacaoService.enviaNotificacao(estacionamento.getIdVeiculo(),"Seu pagamento foi realizado e em instantes você receberá o recibo ");

        } catch (Exception e){
            System.out.println(e.getMessage());
        }
        });
    }

}
