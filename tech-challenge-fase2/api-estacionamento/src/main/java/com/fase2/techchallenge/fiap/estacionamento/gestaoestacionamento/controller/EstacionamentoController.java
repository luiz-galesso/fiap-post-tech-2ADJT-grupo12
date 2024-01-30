package com.fase2.techchallenge.fiap.estacionamento.gestaoestacionamento.controller;

import com.fase2.techchallenge.fiap.estacionamento.gestaoestacionamento.dto.EstacionamentoRequestDTO;
import com.fase2.techchallenge.fiap.estacionamento.gestaoestacionamento.dto.RenovacaoAutomaticaDTO;
import com.fase2.techchallenge.fiap.estacionamento.gestaoestacionamento.service.EstacionamentoService;
import io.swagger.v3.oas.annotations.Operation;
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

    @Operation( summary= "Cadastro um novo estacionamento"
            , description= "Serviço utilizado para cadastrar um novo estacionamento.")
    @PostMapping
    public ResponseEntity<?> insere(@RequestBody EstacionamentoRequestDTO estacionamentoRequestDTO){
        return new ResponseEntity<>(estacionamentoService.inserir(estacionamentoRequestDTO), HttpStatus.OK);
    }
    @Operation( summary= "Obter estacionamentos de um condutor"
            , description= "Serviço utilizado para consultar os estacionamentos de um condutor pelo seu email (id).")
    @GetMapping(path="/condutor/{emailCondutor}")
    public ResponseEntity<?> getByEmailCondutor(@PathVariable String emailCondutor) {
        return new ResponseEntity<>(estacionamentoService.getByEmailCondutor(emailCondutor), HttpStatus.OK);
    }

    @Operation( summary= "Obter um estacionamentor"
            , description= "Serviço utilizado para consultar o estacionamentos pelo id.")
    @GetMapping(path="/{id}")
    public ResponseEntity<?> getById(@PathVariable String id) {
        return new ResponseEntity<>(estacionamentoService.getById(id), HttpStatus.OK);
    }

    @Operation( summary= "Atualiza a renovação automatica de um estacionamento"
            , description= "Serviço utilizado para habilitar/desabilitar a renovação automática de um estacionamento.")
    @PutMapping(path="/{id}/renovacaoAutomatica")
    public ResponseEntity<?> atualizaRenovacaoAutomatica(@PathVariable String id, @RequestBody RenovacaoAutomaticaDTO renovacaoAutomaticaDTO) {
        return new ResponseEntity<>(estacionamentoService.atualizaRenovacaoAutomatica(id, renovacaoAutomaticaDTO.getRenovacaoAutomatica()), HttpStatus.OK);
    }

}