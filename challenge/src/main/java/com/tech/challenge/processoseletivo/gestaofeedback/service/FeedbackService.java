package com.tech.challenge.processoseletivo.gestaofeedback.service;

import com.tech.challenge.inscricao.gestaoetapa.entity.Etapa;
import com.tech.challenge.inscricao.gestaousuario.controller.exception.ControllerNotFoundException;
import com.tech.challenge.inscricao.gestaousuario.entity.Candidato;
import com.tech.challenge.inscricao.gestaousuario.entity.Usuario;
import com.tech.challenge.inscricao.gestaovaga.entity.Vaga;
import com.tech.challenge.processoseletivo.gestaofeedback.dto.AtualizarFeedbackDTO;
import com.tech.challenge.processoseletivo.gestaofeedback.dto.FeedbackDTO;
import com.tech.challenge.processoseletivo.gestaofeedback.entity.Feedback;
import com.tech.challenge.processoseletivo.gestaofeedback.entity.FeedbackId;
import com.tech.challenge.processoseletivo.gestaofeedback.repository.FeedbackRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class FeedbackService {

    private final FeedbackRepository feedbackRepository;

    public FeedbackService(FeedbackRepository feedbackRepository) {
        this.feedbackRepository = feedbackRepository;
    }

    public FeedbackDTO save(FeedbackDTO feedbackDTO) {
        Feedback feedback = toEntity(feedbackDTO);
        feedback = feedbackRepository.save(feedback);
        return toFeedbackDTO(feedback);
    }

    public FeedbackDTO update(FeedbackId feedbackId, AtualizarFeedbackDTO atualizarFeedbackDTO) {
        try {
            Feedback feedback = feedbackRepository.getReferenceById(feedbackId);
            feedback.setConteudoFeedback(atualizarFeedbackDTO.conteudoFeedback());
            Usuario autor = new Usuario();
            autor.setNomeUsuario(atualizarFeedbackDTO.idAutor());
            feedback.setAutor(autor);
            feedback.setDataFeedback(LocalDate.now());
            feedback = feedbackRepository.save(feedback);
            return toFeedbackDTO(feedback);
        } catch (EntityNotFoundException e) {
            throw new ControllerNotFoundException("Feedback não localizado.");
        }
    }

    public void delete(FeedbackId feedbackId) {
        try {
            feedbackRepository.deleteById(feedbackId);

        } catch (EntityNotFoundException e) {
            throw new ControllerNotFoundException("Feedback não localizado.");
        }
    }

    public FeedbackDTO findById(FeedbackId feedbackId) {
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
        return new FeedbackDTO(feedback.getCandidato().getCpf(),
                feedback.getVaga().getIdVaga(),
                feedback.getEtapa().getId(),
                feedback.getConteudoFeedback(),
                feedback.getAutor().getNomeUsuario()
        );

    }

    private Feedback toEntity(FeedbackDTO feedbackDTO) {
        Candidato candidato = new Candidato();
        candidato.setCpf(feedbackDTO.idCandidato());

        Vaga vaga = new Vaga();
        vaga.setIdVaga(feedbackDTO.idVaga());

        Etapa etapa = new Etapa();
        etapa.setId(feedbackDTO.idEtapa());

        Usuario usuario = new Usuario();

        usuario.setNomeUsuario(feedbackDTO.idAutor());

        return new Feedback(candidato,
                vaga,
                etapa,
                feedbackDTO.conteudoFeedback(),
                usuario,
                LocalDate.now());
    }
}
