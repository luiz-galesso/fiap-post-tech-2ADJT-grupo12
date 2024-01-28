package com.fase2.techchallenge.fiap.gestaonotificacao.service;

import com.fase2.techchallenge.fiap.gestaonotificacao.controller.exception.ControllerNotFoundException;
import com.fase2.techchallenge.fiap.gestaonotificacao.dto.NotificacaoRequestDTO;
import com.fase2.techchallenge.fiap.gestaonotificacao.dto.NotificacaoResponseDTO;
import com.fase2.techchallenge.fiap.gestaonotificacao.entity.Notificacao;
import com.fase2.techchallenge.fiap.gestaonotificacao.repository.NotificacaoRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NotificacaoService {

    private NotificacaoRepository notificacaoRepository;
    private VeiculoService veiculoService;

    public NotificacaoService(NotificacaoRepository notificacaoRepository, VeiculoService veiculoService) {
        this.notificacaoRepository = notificacaoRepository;
        this.veiculoService = veiculoService;
    }

    public Page<NotificacaoResponseDTO> findAll(Pageable pageable) {
        Page<Notificacao> notificacoes = notificacaoRepository.findAll(pageable);
        return notificacoes.map(this::toNotificacaoResponseDTO);
    }

    public NotificacaoResponseDTO findById(Long id) {
        try {
            Notificacao notificacao = notificacaoRepository.findById(id).orElseThrow(() -> new ControllerNotFoundException("Notificação não localizada"));
            return toNotificacaoResponseDTO(notificacao);
        } catch (EntityNotFoundException e) {
            throw new ControllerNotFoundException("Notificação não localizada");
        }
    }

    public List<NotificacaoResponseDTO> findByIdVeiculo(Integer idVeiculo) {
        List<Notificacao> notificacoes = notificacaoRepository.findByidVeiculo(idVeiculo);
        return notificacoes.stream().map(this::toNotificacaoResponseDTO).toList();
    }


    public void deleteById(Long id) {
        try {
            notificacaoRepository.deleteById(id);
        } catch (EntityNotFoundException e) {
            throw new ControllerNotFoundException("Notificação não localizada");
        }
    }

    public NotificacaoResponseDTO save(NotificacaoRequestDTO notificacaoRequestDTO) {
        Notificacao notificacao = toEntity(notificacaoRequestDTO);
        veiculoService.getVeiculoPorId(notificacaoRequestDTO.idVeiculo());
        notificacao = notificacaoRepository.save(notificacao);
        return toNotificacaoResponseDTO(notificacao);
    }

    public NotificacaoResponseDTO update(Long id, NotificacaoRequestDTO notificacaoRequestDTO) {
        try {
            Notificacao notificacao = notificacaoRepository.getReferenceById(id);
            notificacao.setIdVeiculo(notificacaoRequestDTO.idVeiculo());
            notificacao.setDataHora(notificacaoRequestDTO.dataHora());
            notificacao.setConteudo(notificacaoRequestDTO.conteudo());
            notificacao.setSituacao("ENVIADA");
            notificacao = notificacaoRepository.save(notificacao);
            return toNotificacaoResponseDTO(notificacao);
        } catch (EntityNotFoundException e) {
            throw new ControllerNotFoundException("Notificação não localizada");
        }
    }

    public NotificacaoResponseDTO finaliza(Long id, NotificacaoRequestDTO notificacaoRequestDTO) {
        try {
            Notificacao notificacao = notificacaoRepository.getReferenceById(id);
            notificacao.setIdVeiculo(notificacaoRequestDTO.idVeiculo());
            notificacao.setDataHora(notificacaoRequestDTO.dataHora());
            notificacao.setConteudo(notificacaoRequestDTO.conteudo());
            notificacao.setSituacao("FINALIZADA");
            notificacao = notificacaoRepository.save(notificacao);
            return toNotificacaoResponseDTO(notificacao);
        } catch (EntityNotFoundException e) {
            throw new ControllerNotFoundException("Notificação não localizada");
        }
    }

    private Notificacao toEntity(NotificacaoRequestDTO notificacaoRequestDTO) {
        return new Notificacao(notificacaoRequestDTO.id(),
                notificacaoRequestDTO.idVeiculo(),
                notificacaoRequestDTO.dataHora(),
                notificacaoRequestDTO.conteudo(),
                "ABERTA");
    }

    private NotificacaoResponseDTO toNotificacaoResponseDTO(Notificacao notificacao) {
        return new NotificacaoResponseDTO(notificacao.getId(),
                veiculoService.getVeiculoPorId(notificacao.getIdVeiculo()),
                notificacao.getDataHora(),
                notificacao.getConteudo(),
                notificacao.getSituacao());
    }

}
