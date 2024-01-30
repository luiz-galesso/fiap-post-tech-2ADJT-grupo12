package com.fase2.techchallenge.fiap.estacionamento.gestaoestacionamento.service;

import com.fase2.techchallenge.fiap.estacionamento.exception.BadRequestException;
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
import java.util.Optional;

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

    @Autowired
    CadastroService cadastroService;

    public Estacionamento inserir(EstacionamentoRequestDTO estacionamentoRequestDTO){
        if (estacionamentoRequestDTO.getTipo().equals("VARIAVEL")) {estacionamentoRequestDTO.setQuantidadeHoras(1);}
        Estacionamento estacionamento = estacionamentoRequestDTO.toDocument();
        estacionamento.setValorTarifa(tarifaService.getTarifa().valor());
        estacionamento.setPlaca(cadastroService.getVeiculo(estacionamento.getIdVeiculo()).getPlaca());
        if (estacionamentoRequestDTO.getTipo().equals("FIXO")) {
            if (Optional.ofNullable(estacionamentoRequestDTO.getQuantidadeHoras()).orElse(0) == 0) {
                throw new BadRequestException("Informe a quantidade de horas.");
            }
            if (!cadastroService.getMeioPagamentoCondutor(estacionamentoRequestDTO.getIdMeioPagamento()).tipoMeioPagamento().equals("PIX")) {
                throw new BadRequestException("Estacionamento do tipo FIXO só permite pagamento via PIX");
            }
            Long horasCheias = ChronoUnit.HOURS.between(estacionamento.getDataHoraInicio(), estacionamento.getDataHoraVencimento());
            pagamentoService.enviaPagamento(estacionamento.getIdVeiculo(), estacionamento.getIdCondutor(),estacionamento.getIdMeioPagamento(), estacionamento.getValorTarifa() * horasCheias , estacionamento.getId());
        }
        estacionamento = estacionamentoRepository.save(estacionamento);
        return estacionamento;
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

    public List<Estacionamento> getByEmailCondutor(String emailCondutor){
        return estacionamentoRepository.findByIdCondutor(emailCondutor);
    }

    public Estacionamento getById(String id){
        return estacionamentoRepository.findById(id).get();
    }

    public Estacionamento atualizaRenovacaoAutomatica(String id, Boolean renovacaoAutomatica){
        Estacionamento estacionamento = estacionamentoRepository.findById(id).get();
        estacionamento.setRenovacaoAutomatica(renovacaoAutomatica);
        return estacionamentoRepository.save(estacionamento);
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
                        (LocalDateTime.now().plusMinutes(1), false, "VARIAVEL", "ATIVO");
        estacionamentoList.stream().forEach(estacionamento ->
        {   try {
            LocalDateTime dataHoraTermino = LocalDateTime.now();
            Long horasCheias = ChronoUnit.HOURS.between(estacionamento.getDataHoraInicio(), dataHoraTermino);
            Long minutoParcial = (ChronoUnit.MINUTES.between(estacionamento.getDataHoraInicio(), dataHoraTermino) - (horasCheias * 60));
            if (minutoParcial > 0) {
                horasCheias = horasCheias + 1;
            }
            Double valor = estacionamento.getValorTarifa() * horasCheias;
            pagamentoService.enviaPagamento(estacionamento.getIdVeiculo(), estacionamento.getIdCondutor(),estacionamento.getIdMeioPagamento(), valor, estacionamento.getId());
            atualizaDataHoraTerminoESituacao(estacionamento, dataHoraTermino, "FINALIZADO");
            notificacaoService.enviaNotificacao(estacionamento.getIdVeiculo(),"Seu pagamento foi realizado e em instantes você receberá o recibo ");

        } catch (Exception e){
            System.out.println(e.getMessage());
        }
        });
    }

    @Scheduled(fixedDelay = 60000)
    public void FinalizaFixo()
    {
        List<Estacionamento> estacionamentoList =
                estacionamentoRepository.findEstacionamentoBydataHoraVencimentoLowerThanAndTipoAndSituacao
                        (LocalDateTime.now().plusMinutes(1), "VARIAVEL", "ATIVO");
        estacionamentoList.stream().forEach(estacionamento ->
        {   try {
                atualizaDataHoraTerminoESituacao(estacionamento, LocalDateTime.now(), "FINALIZADO");
                notificacaoService.enviaNotificacao(estacionamento.getIdVeiculo(),"Seu tempo de permanência foi esgotado em " + estacionamento.getDataHoraVencimento());

            } catch (Exception e){
                System.out.println(e.getMessage());
        }
        });
    }
}
