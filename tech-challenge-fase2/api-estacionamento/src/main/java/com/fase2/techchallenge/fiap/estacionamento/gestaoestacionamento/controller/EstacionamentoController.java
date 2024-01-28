package com.fase2.techchallenge.fiap.estacionamento.gestaoestacionamento.controller;

import com.fase2.techchallenge.fiap.estacionamento.gestaoestacionamento.dto.EstacionamentoRequestDTO;
import com.fase2.techchallenge.fiap.estacionamento.gestaoestacionamento.dto.TarifaDTO;
import com.fase2.techchallenge.fiap.estacionamento.gestaoestacionamento.service.EstacionamentoService;
import com.fase2.techchallenge.fiap.estacionamento.gestaoestacionamento.service.TarifaService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;



@RestController
@RequestMapping("/estacionamento")
@Tag(name = "Estacionamento", description="Serviços para manipular os estacionamentos")
public class EstacionamentoController {

    @Autowired
    EstacionamentoService estacionamentoService;

    @PostMapping
    public ResponseEntity<?> insere(@RequestBody EstacionamentoRequestDTO estacionamentoRequestDTO){
        return new ResponseEntity<>(estacionamentoService.inserir(estacionamentoRequestDTO), HttpStatus.OK);
    }

    @GetMapping(path="/{id}")
    public ResponseEntity<?> get(@PathVariable String id) {
        return new ResponseEntity<>(estacionamentoService.get(id), HttpStatus.OK);
    }

}