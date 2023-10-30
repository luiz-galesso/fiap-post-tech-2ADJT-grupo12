package com.tech.challenge.processoseletivo.gestaofeedback.service;

import com.tech.challenge.inscricao.gestaoetapa.service.EtapaService;
import com.tech.challenge.inscricao.gestaousuario.controller.exception.ControllerNotFoundException;
import com.tech.challenge.inscricao.gestaousuario.service.UsuarioService;
import com.tech.challenge.inscricao.gestaovaga.service.VagaService;
import com.tech.challenge.processoseletivo.gestaofeedback.dto.AtualizarFeedbackDTO;
import com.tech.challenge.processoseletivo.gestaofeedback.dto.FeedbackDTO;
import com.tech.challenge.processoseletivo.gestaofeedback.entity.Feedback;
import com.tech.challenge.processoseletivo.gestaofeedback.repository.FeedbackRepository;
import com.tech.challenge.util.StringUtils;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class FeedbackService {

    private final UsuarioService usuarioService;

    private final VagaService vagaService;

    private final EtapaService etapaService;

    private final FeedbackRepository feedbackRepository;

    public FeedbackService(UsuarioService usuarioService, VagaService vagaService, EtapaService etapaService, FeedbackRepository feedbackRepository) {
        this.usuarioService = usuarioService;
        this.vagaService = vagaService;
        this.etapaService = etapaService;
        this.feedbackRepository = feedbackRepository;
    }

    public FeedbackDTO save(FeedbackDTO feedbackDTO) {
        Feedback feedback = toEntity(feedbackDTO);
        feedback = feedbackRepository.save(feedback);
        return toFeedbackDTO(feedback);
    }

    public FeedbackDTO update(Long feedbackId, AtualizarFeedbackDTO atualizarFeedbackDTO) {
        try {
            Feedback feedback = feedbackRepository.getReferenceById(feedbackId);
            feedback.setConteudoFeedback(atualizarFeedbackDTO.conteudoFeedback());
            feedback.setAutor(usuarioService.findById(atualizarFeedbackDTO.idAutor()));
            feedback.setVaga(vagaService.findById(atualizarFeedbackDTO.idVaga()));
            feedback.setEtapa(etapaService.findById(atualizarFeedbackDTO.idEtapa()));
            feedback.setDataFeedback(LocalDate.now());
            feedback.setCandidato(usuarioService.findById(atualizarFeedbackDTO.idCandidato()));
            feedback = feedbackRepository.save(feedback);
            return toFeedbackDTO(feedback);
        } catch (EntityNotFoundException e) {
            throw new ControllerNotFoundException("Feedback não localizado.");
        }
    }

    public void delete(Long feedbackId) {
        try {
            feedbackRepository.deleteById(feedbackId);

        } catch (EntityNotFoundException e) {
            throw new ControllerNotFoundException("Feedback não localizado.");
        }
    }

    public FeedbackDTO findById(Long feedbackId) {
        try {
            return feedbackRepository.findById(feedbackId).map(this::toFeedbackDTO).orElseThrow(() -> new ControllerNotFoundException("Feedback não localizado."));
        } catch (EntityNotFoundException e) {
            throw new ControllerNotFoundException("Feedback não localizado.");
        }
    }

    public Page<FeedbackDTO> findAll(Pageable pageable) {
        Page<Feedback> feedbacks = feedbackRepository.findAll(pageable);
        return feedbacks.map(this::toFeedbackDTO);
    }

    private FeedbackDTO toFeedbackDTO(Feedback feedback) {
        return new FeedbackDTO(feedback.getId(), feedback.getUsuario().getCpf(),
                feedback.getVaga().getId(),
                feedback.getEtapa().getId(),
                feedback.getConteudoFeedback(),
                feedback.getAutor().getCpf()
        );

    }

    private Feedback toEntity(FeedbackDTO feedbackDTO) {
        return new Feedback(feedbackDTO.id(),
                            usuarioService.findById(StringUtils.removeMascara(feedbackDTO.idCandidato())),
                            vagaService.findById(feedbackDTO.idVaga()),
                            etapaService.findById(feedbackDTO.idEtapa()),
                            feedbackDTO.conteudoFeedback(),
                            usuarioService.findById(StringUtils.removeMascara(feedbackDTO.idAutor())),
                            LocalDate.now());
    }
}
