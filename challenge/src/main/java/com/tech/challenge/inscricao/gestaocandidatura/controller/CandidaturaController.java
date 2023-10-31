package com.tech.challenge.inscricao.gestaocandidatura.controller;

import com.tech.challenge.inscricao.gestaocandidatura.dto.CandidaturaRequestDTO;
import com.tech.challenge.inscricao.gestaocandidatura.service.CandidaturaService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/candidatura")
public class CandidaturaController {

    @Autowired
    public CandidaturaService candidaturaService;

    @PostMapping
    public ResponseEntity<CandidaturaRequestDTO> save(@RequestBody @Valid CandidaturaRequestDTO candidaturaRequestDTO) {
        CandidaturaRequestDTO candidatura = candidaturaService.save(candidaturaRequestDTO);
        return new ResponseEntity<>(candidatura, HttpStatus.CREATED);
    }

    @DeleteMapping("{cpf}/{vaga}")
    public ResponseEntity<CandidaturaRequestDTO> delete(@PathVariable("cpf")String cpf, @PathVariable("vaga")Long vaga) {
        candidaturaService.delete(cpf, vaga);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
