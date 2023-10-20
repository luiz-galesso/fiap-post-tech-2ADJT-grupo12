package com.tech.challenge.inscricao.gestaousuario.controller;

import com.tech.challenge.inscricao.gestaousuario.dto.CandidatoDTO;
import com.tech.challenge.inscricao.gestaousuario.service.CandidatoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/candidatos")
public class CandidatoController {
    @Autowired
    private CandidatoService candidatoService;

    @PostMapping
    public ResponseEntity<CandidatoDTO> save (@RequestBody CandidatoDTO candidatoDTO){
        CandidatoDTO candidato = candidatoService.save(candidatoDTO);
        return new ResponseEntity<>(candidato, HttpStatus.CREATED);

    }

}
