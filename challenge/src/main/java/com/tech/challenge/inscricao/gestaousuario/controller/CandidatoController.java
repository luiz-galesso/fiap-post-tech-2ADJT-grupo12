package com.tech.challenge.inscricao.gestaousuario.controller;

import com.tech.challenge.inscricao.gestaousuario.dto.AtualizarCandidatoDTO;
import com.tech.challenge.inscricao.gestaousuario.dto.CandidatoDTO;
import com.tech.challenge.inscricao.gestaousuario.entity.Candidato;
import com.tech.challenge.inscricao.gestaousuario.service.CandidatoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@RequestMapping("/candidatos")
public class CandidatoController {
    @Autowired
    private CandidatoService candidatoService;

    @PostMapping
    public ResponseEntity<CandidatoDTO> save(@RequestBody CandidatoDTO candidatoDTO) {
        CandidatoDTO candidato = candidatoService.save(candidatoDTO);
        return new ResponseEntity<>(candidato, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CandidatoDTO> update(@PathVariable String id, @RequestBody AtualizarCandidatoDTO atualizarCandidatoDTO) {
        CandidatoDTO candidatoAtualizado = candidatoService.update(id, atualizarCandidatoDTO);
        return ResponseEntity.ok(candidatoAtualizado);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable String id) {
        candidatoService.delete(id);
        return ResponseEntity.noContent().build();
    }

    /* REMOÇÃO DE MÉTODO, NÃO HÁ SENTIDO SUA EXISTÊNCIA
    @GetMapping
    public ResponseEntity<Collection<Candidato>> findAll() {
        var candidatos = candidatoService.findAll();
        return ResponseEntity.ok(candidatos);
    }*/

    @GetMapping("/{id}")
    public ResponseEntity<Candidato> findById(@PathVariable("id") String id) {
        var candidato = candidatoService.findById(id);
        return ResponseEntity.ok(candidato);
    }
}