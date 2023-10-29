package com.tech.challenge.processoseletivo.gestaoentrevista.controller;


import com.tech.challenge.processoseletivo.gestaoentrevista.dto.EntrevistaRequestDTO;
import com.tech.challenge.processoseletivo.gestaoentrevista.dto.EntrevistaResponseDTO;
import com.tech.challenge.processoseletivo.gestaoentrevista.service.EntrevistaService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@RequestMapping("/entrevistas")
public class EntrevistaController {

    private final EntrevistaService entrevistaService;

    @Autowired
    public EntrevistaController(EntrevistaService entrevistaService) {
        this.entrevistaService = entrevistaService;
    }

    @GetMapping
    public ResponseEntity<Collection<EntrevistaResponseDTO>> findAll() {
        var entrevistas = entrevistaService.findAll();
        return ResponseEntity.ok(entrevistas);
    }

    @GetMapping("/{id}")
    public ResponseEntity<EntrevistaResponseDTO> findById(@PathVariable Long id) {
        var entrevista = entrevistaService.findById(id);
        return ResponseEntity.ok(entrevista);
    }

    @GetMapping("/candidato/{candidato}")
    public ResponseEntity<EntrevistaResponseDTO> findByCandidato(@PathVariable String candidato) {
        var entrevista = entrevistaService.findByUsuario(candidato,"Candidato");
        return ResponseEntity.ok(entrevista);
    }

    @GetMapping("/entrevistador/{entrevistador}")
    public ResponseEntity<EntrevistaResponseDTO> findByEntrevistador(@PathVariable String entrevistador) {
        var entrevista = entrevistaService.findByUsuario(entrevistador,"Entrevistador");
        return ResponseEntity.ok(entrevista);
    }

    @PostMapping
    public ResponseEntity<EntrevistaResponseDTO> save(@Valid @RequestBody EntrevistaRequestDTO EntrevistaDTO) {
        EntrevistaResponseDTO entrevistaRespDTO = entrevistaService.save(EntrevistaDTO);
        return ResponseEntity.status(HttpStatusCode.valueOf(201)).body(entrevistaRespDTO);
    }

    @PutMapping("/{id}")
    public ResponseEntity<EntrevistaResponseDTO> update(@PathVariable Long id, @RequestBody EntrevistaRequestDTO EntrevistaDTO) {
        EntrevistaResponseDTO entrevistaRespDTO = entrevistaService.update(id, EntrevistaDTO);
        return ResponseEntity.ok(entrevistaRespDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        entrevistaService.delete(id);
        return ResponseEntity.noContent().build();
    }

}
