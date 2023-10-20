package com.tech.challenge.inscricao.gestaoetapa.controller;


import com.tech.challenge.inscricao.gestaoetapa.dto.EtapaDTO;
import com.tech.challenge.inscricao.gestaoetapa.dto.EtapaRequestDTO;
import com.tech.challenge.inscricao.gestaoetapa.entity.Etapa;
import com.tech.challenge.inscricao.gestaoetapa.service.EtapaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@RequestMapping("/etapas")
public class EtapaController {

    @Autowired
    private EtapaService etapaService;

    @PostMapping
    public ResponseEntity<EtapaDTO> save(@RequestBody EtapaRequestDTO etapaRequestDTO) {
        EtapaDTO etapa = etapaService.save(etapaRequestDTO);
        return new ResponseEntity<>(etapa, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<EtapaDTO> update(@PathVariable Long id, @RequestBody EtapaDTO etapaDTO) {
        EtapaDTO etapaAtualizada = etapaService.update(id, etapaDTO);
        return ResponseEntity.ok(etapaAtualizada);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        etapaService.delete(id);
        return ResponseEntity.noContent().build();
    }


    @GetMapping
    public ResponseEntity<Collection<Etapa>> findAll() {
        Collection<Etapa> etapas = etapaService.findAll();
        return ResponseEntity.ok(etapas);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Etapa> findById(@PathVariable("id") Long id) {
        Etapa etapa = etapaService.findById(id); //Converter
        return ResponseEntity.ok(etapa);
    }

}

