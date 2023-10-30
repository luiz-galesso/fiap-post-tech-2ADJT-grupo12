package com.tech.challenge.processoseletivo.gestaoetapa.service;

import org.springframework.stereotype.Service;

@Service
public class VagaEtapaOrdemService {

    /*private final FeedbackRepository feedbackRepository;

    public VagaEtapaOrdemService(FeedbackRepository feedbackRepository) {
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

    public void delete(Long feedbackId) {
        try {
            feedbackRepository.deleteById(feedbackId);

        } catch (EntityNotFoundException e) {
            throw new ControllerNotFoundException("Feedback não localizado.");
        }
    }

/*    public FeedbackDTO findById(FeedbackId feedbackId) {
        try {
            return feedbackRepository.findById(feedbackId).map(this::toFeedbackDTO).orElseThrow(() -> new ControllerNotFoundException("Feedback não localizado."));
        } catch (EntityNotFoundException e) {
            throw new ControllerNotFoundException("Feedback não localizado.");
        }
    }*/

 /*   public Page<FeedbackDTO> findAll(Pageable pageable) {
        Page<Feedback> feedbacks = feedbackRepository.findAll(pageable);
        return feedbacks.map(this::toFeedbackDTO);
    }

 /*   private FeedbackDTO toFeedbackDTO(Feedback feedback) {
        return new FeedbackDTO(feedback.getCandidato().getCpf(),
                feedback.getVaga().getIdVaga(),
                feedback.getEtapa().getId(),
                feedback.getConteudoFeedback(),
                feedback.getAutor().getNomeUsuario()
        );

    }*/

  /*  private Feedback toEntity(FeedbackDTO feedbackDTO) {
        return new Feedback(feedbackDTO.id(),
                usuarioService.findById(StringUtils.removeMascara(feedbackDTO.idCandidato())),
                vagaService.findById(feedbackDTO.idVaga()),
                etapaService.findById(feedbackDTO.idEtapa()),
                feedbackDTO.conteudoFeedback(),
                usuarioService.findById(StringUtils.removeMascara(feedbackDTO.idAutor())),
                LocalDate.now());
    }*/
}
