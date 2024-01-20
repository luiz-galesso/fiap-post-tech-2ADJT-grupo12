package com.fase2.techchallenge.fiap.gestaonotificacao.service;

import com.fase2.techchallenge.fiap.gestaonotificacao.controller.exception.ControllerNotFoundException;
import com.fase2.techchallenge.fiap.gestaonotificacao.dto.NotificacaoDTO;
import com.fase2.techchallenge.fiap.gestaonotificacao.entity.Notificacao;
import com.fase2.techchallenge.fiap.gestaonotificacao.repository.NotificacaoRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class NotificacaoService {

    private NotificacaoRepository notificacaoRepository;

    public NotificacaoService(NotificacaoRepository notificacaoRepository) {
        this.notificacaoRepository = notificacaoRepository;
    }

    public Page<NotificacaoDTO> findAll(Pageable pageable) {
        Page<Notificacao> notificacaos = notificacaoRepository.findAll(pageable);
        return notificacaos.map(this::toNotificacaoDTO);
    }

    public NotificacaoDTO findById(Long id) {
        try {
            Notificacao notificacao = notificacaoRepository.findById(id).orElseThrow(() -> new ControllerNotFoundException("Notificação não localizada"));
            return toNotificacaoDTO(notificacao);
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

    public NotificacaoDTO save(NotificacaoDTO notificacaoDTO) {
        Notificacao notificacao = toEntity(notificacaoDTO);
        notificacao = notificacaoRepository.save(notificacao);
        return toNotificacaoDTO(notificacao);
    }

    public NotificacaoDTO update(Long id, NotificacaoDTO notificacaoDTO) {
        try {
            Notificacao notificacao = notificacaoRepository.getReferenceById(id);
            notificacao.setIdVeiculo(notificacaoDTO.idVeiculo());
            notificacao.setDataHora(notificacaoDTO.dataHora());
            notificacao.setConteudo(notificacaoDTO.conteudo());
            notificacao.setSituacao(notificacaoDTO.situacao());
            notificacao = notificacaoRepository.save(notificacao);
            return toNotificacaoDTO(notificacao);
        } catch (EntityNotFoundException e) {
            throw new ControllerNotFoundException("Notificação não localizada");
        }
    }


    private Notificacao toEntity(NotificacaoDTO notificacaoDTO) {
        return new Notificacao(notificacaoDTO.id(),
                notificacaoDTO.idVeiculo(),
                notificacaoDTO.dataHora(),
                notificacaoDTO.conteudo(),
                notificacaoDTO.situacao()
        );
    }

    private NotificacaoDTO toNotificacaoDTO(Notificacao notificacao) {
        return new NotificacaoDTO(notificacao.getId(),
                notificacao.getIdVeiculo(),
                notificacao.getDataHora(),
                notificacao.getConteudo(),
                notificacao.getSituacao()
        );
    }

}
