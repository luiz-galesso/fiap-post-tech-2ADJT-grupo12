package com.fase2.techchallenge.fiap.gestaonotificacao.service;

import com.fase2.techchallenge.fiap.gestaonotificacao.controller.exception.ControllerNotFoundException;
import com.fase2.techchallenge.fiap.gestaonotificacao.dto.NotificacaoDTO;
import com.fase2.techchallenge.fiap.gestaonotificacao.dto.NotificacaoResponseDTO;
import com.fase2.techchallenge.fiap.gestaonotificacao.entity.Notificacao;
import com.fase2.techchallenge.fiap.gestaonotificacao.repository.NotificacaoRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

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

    public void deleteById(Long id) {
        try {
            notificacaoRepository.deleteById(id);
        } catch (EntityNotFoundException e) {
            throw new ControllerNotFoundException("Notificação não localizada");
        }
    }

    public NotificacaoResponseDTO save(NotificacaoDTO notificacaoDTO) {
        Notificacao notificacao = toEntity(notificacaoDTO);
        veiculoService.getVeiculoPorId(notificacaoDTO.idVeiculo());
        notificacao = notificacaoRepository.save(notificacao);
        return toNotificacaoResponseDTO(notificacao);
    }

    public NotificacaoResponseDTO update(Long id, NotificacaoDTO notificacaoDTO) {
        try {
            Notificacao notificacao = notificacaoRepository.getReferenceById(id);
            notificacao.setIdVeiculo(notificacaoDTO.idVeiculo());
            notificacao.setDataHora(notificacaoDTO.dataHora());
            notificacao.setConteudo(notificacaoDTO.conteudo());
            notificacao.setSituacao(notificacaoDTO.situacao());
            notificacao = notificacaoRepository.save(notificacao);
            return toNotificacaoResponseDTO(notificacao);
        } catch (EntityNotFoundException e) {
            throw new ControllerNotFoundException("Notificação não localizada");
        }
    }

    private Notificacao toEntity(NotificacaoDTO notificacaoDTO) {
        return new Notificacao(notificacaoDTO.id(),
                notificacaoDTO.idVeiculo(),
                notificacaoDTO.dataHora(),
                notificacaoDTO.conteudo(),
                notificacaoDTO.situacao());
    }

    private NotificacaoResponseDTO toNotificacaoResponseDTO(Notificacao notificacao) {
        return new NotificacaoResponseDTO(notificacao.getId(),
                veiculoService.getVeiculoPorId(notificacao.getIdVeiculo()),
                notificacao.getDataHora(),
                notificacao.getConteudo(),
                notificacao.getSituacao());
    }

}
