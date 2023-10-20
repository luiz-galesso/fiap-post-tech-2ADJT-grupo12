package com.tech.challenge.processoseletivo.gestaofeedback.controller;

import com.tech.challenge.processoseletivo.gestaofeedback.dto.AtualizarFeedbackDTO;
import com.tech.challenge.processoseletivo.gestaofeedback.dto.FeedbackDTO;
import com.tech.challenge.processoseletivo.gestaofeedback.entity.FeedbackId;
import com.tech.challenge.processoseletivo.gestaofeedback.service.FeedbackService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("feedbacks")
public class FeedbackController {
    private final FeedbackService feedbackService;

    public FeedbackController(FeedbackService feedbackService) {
        this.feedbackService = feedbackService;
    }

    @PostMapping
    public ResponseEntity<FeedbackDTO> save(@RequestBody FeedbackDTO feedbackDTO) {
        FeedbackDTO feedback = feedbackService.save(feedbackDTO);
        return new ResponseEntity<>(feedback, HttpStatus.CREATED);
    }

    @PutMapping("/id_candidato={idCandidato}&id_vaga={idVaga}&id_etapa={idEtapa}")
    public ResponseEntity<FeedbackDTO> update(@PathVariable String idCandidato, @PathVariable Long idVaga, @PathVariable Long idEtapa, @RequestBody AtualizarFeedbackDTO atualizarFeedBackDTO) {
        FeedbackId feedbackId = new FeedbackId(idCandidato, idVaga, idEtapa);
        FeedbackDTO feedbackAtualizado = feedbackService.update(feedbackId, atualizarFeedBackDTO);
        return ResponseEntity.ok(feedbackAtualizado);
    }

    @DeleteMapping("/id_candidato={idCandidato}&id_vaga={idVaga}&id_etapa={idEtapa}")
    public ResponseEntity<Void> delete(@PathVariable String idCandidato, @PathVariable Long idVaga, @PathVariable Long idEtapa) {
        FeedbackId feedbackId = new FeedbackId(idCandidato, idVaga, idEtapa);
        feedbackService.delete(feedbackId);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/id_candidato={idCandidato}&id_vaga={idVaga}&id_etapa={idEtapa}")
    public ResponseEntity<FeedbackDTO> findById(@PathVariable String idCandidato, @PathVariable Long idVaga, @PathVariable Long idEtapa) {
        FeedbackId feedbackId = new FeedbackId(idCandidato, idVaga, idEtapa);
        FeedbackDTO feedbackDTO = feedbackService.findById(feedbackId);
        return ResponseEntity.ok(feedbackDTO);
    }

    @GetMapping
    public ResponseEntity<Page<FeedbackDTO>> findAll(@PageableDefault(size = 100, sort = "idCandidato") Pageable pageable) {
        Page<FeedbackDTO> feedbackDTOS = feedbackService.findAll(pageable);
        return ResponseEntity.ok(feedbackDTOS);

    }


}
