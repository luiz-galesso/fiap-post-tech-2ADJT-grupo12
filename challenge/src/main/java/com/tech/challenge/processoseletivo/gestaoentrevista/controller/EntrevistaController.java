package com.tech.challenge.processoseletivo.gestaoentrevista.controller;


import com.tech.challenge.processoseletivo.gestaoentrevista.dto.EntrevistaDTO;
import com.tech.challenge.processoseletivo.gestaoentrevista.service.EntrevistaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@RequestMapping("/entrevistas")
public class EntrevistaController {

    @Autowired
    private EntrevistaService entrevistaService;

    @GetMapping
    public ResponseEntity<Collection<EntrevistaDTO>> findAll() {
        var entrevistas = entrevistaService.findAll();
        return ResponseEntity.ok(entrevistas);
    }

    @GetMapping("/{id}")
    public ResponseEntity<EntrevistaDTO> findById(@PathVariable Long id) {
        var entrevista = entrevistaService.findById(id);
        return ResponseEntity.ok(entrevista);
    }

    @PostMapping
    public ResponseEntity<EntrevistaDTO> save(@RequestBody EntrevistaDTO EntrevistaDTO) {
        EntrevistaDTO = entrevistaService.save(EntrevistaDTO);
        return ResponseEntity.status(HttpStatusCode.valueOf(201)).body(EntrevistaDTO);
    }

    @PutMapping("/{id}")
    public ResponseEntity<EntrevistaDTO> update(@PathVariable Long id, @RequestBody EntrevistaDTO EntrevistaDTO) {
        EntrevistaDTO = entrevistaService.update(id, EntrevistaDTO);
        return ResponseEntity.ok(EntrevistaDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        entrevistaService.delete(id);
        return ResponseEntity.noContent().build();
    }

}
