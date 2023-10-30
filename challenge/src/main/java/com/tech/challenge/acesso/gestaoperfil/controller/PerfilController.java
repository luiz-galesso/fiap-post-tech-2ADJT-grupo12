package com.tech.challenge.acesso.gestaoperfil.controller;

import com.tech.challenge.acesso.gestaoperfil.dto.PerfilRequestDTO;

import com.tech.challenge.acesso.gestaoperfil.service.PerfilService;
import com.tech.challenge.acesso.gestaoperfil.entity.Perfil;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;


@RestController
@RequestMapping("/perfis")
public class PerfilController {
      @Autowired
    private PerfilService perfilService;

    @PostMapping// @Valid
    public ResponseEntity<PerfilRequestDTO> save(@RequestBody @Valid PerfilRequestDTO perfilDTO) {
        PerfilRequestDTO perfil = perfilService.save(perfilDTO);
        return new ResponseEntity<>(perfil, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<PerfilRequestDTO> update(@PathVariable Long id, @RequestBody PerfilRequestDTO perfilRequestDTO) {
        PerfilRequestDTO perfilAtualizado = perfilService.update(id, perfilRequestDTO);
        return ResponseEntity.ok(perfilAtualizado);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        perfilService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Perfil> findById(@PathVariable("id") Long id) {
        var perfil = perfilService.findById(id);
        return ResponseEntity.ok(perfil);
    }

    @GetMapping
    public ResponseEntity<Collection<Perfil>> findAll() {
        var perfil = perfilService.findAll();
        return ResponseEntity.ok(perfil);
    }
}