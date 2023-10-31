package com.tech.challenge.inscricao.gestaocarreira.controller;

import com.tech.challenge.inscricao.gestaocarreira.dto.CarreiraRequestDTO;
import com.tech.challenge.inscricao.gestaocarreira.entity.Carreira;
import com.tech.challenge.inscricao.gestaocarreira.service.CarreiraService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;


@RestController
@RequestMapping("/carreiras")
public class CarreiraController {
      @Autowired
    private CarreiraService carreiraService;

    @PostMapping
    public ResponseEntity<CarreiraRequestDTO> save(@RequestBody @Valid CarreiraRequestDTO carreiraDTO) {
        CarreiraRequestDTO carreira = carreiraService.save(carreiraDTO);
        return new ResponseEntity<>(carreira, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CarreiraRequestDTO> update(@PathVariable Long id, @RequestBody CarreiraRequestDTO carreiraRequestDTO) {
        CarreiraRequestDTO carreiraAtualizado = carreiraService.update(id, carreiraRequestDTO);
        return ResponseEntity.ok(carreiraAtualizado);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        carreiraService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Carreira> findById(@PathVariable("id") Long id) {
        var carreira = carreiraService.findById(id);
        return ResponseEntity.ok(carreira);
    }

    @GetMapping
    public ResponseEntity<Collection<Carreira>> findAll() {
        var carreira = carreiraService.findAll();
        return ResponseEntity.ok(carreira);
    }
}