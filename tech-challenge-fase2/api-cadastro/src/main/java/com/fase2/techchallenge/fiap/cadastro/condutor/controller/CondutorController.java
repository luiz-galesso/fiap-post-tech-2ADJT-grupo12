package com.fase2.techchallenge.fiap.cadastro.condutor.controller;

import com.fase2.techchallenge.fiap.cadastro.condutor.dto.CondutorDTO;
import com.fase2.techchallenge.fiap.cadastro.condutor.entity.Condutor;
import com.fase2.techchallenge.fiap.cadastro.condutor.service.CondutorService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/condutor")
@Tag(name = "Condutor", description="Serviços para manipular o condutor")
public class CondutorController {

    @Autowired
    private CondutorService condutorService;

    @Operation( summary= "Insere um novo condutor"
              , description= "Serviço utilizado para inserir um condutor.")
    @PostMapping(produces = "application/json")
    public ResponseEntity<CondutorDTO> save(@Valid @RequestBody CondutorDTO condutorServiceDTO) {
        CondutorDTO condutorResponse = condutorService.save(condutorServiceDTO);
        return new ResponseEntity<>(condutorResponse, HttpStatus.CREATED);
    }
    @Operation( summary= "Atualiza um condutor"
              , description= "Serviço utilizado para atualizar um condutor.")
    @PutMapping(value = "/{id}", produces = "application/json")
    //COLOCAR @VALID
    public ResponseEntity<CondutorDTO> update(@PathVariable String id, @RequestBody CondutorDTO condutorServiceDTO) {
        CondutorDTO condutorResponse = condutorService.update(id, condutorServiceDTO);
        return ResponseEntity.ok(condutorResponse);
    }
    @Operation( summary= "Remove um condutor"
              , description= "Serviço utilizado para remover um condutor.")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable String id) {
        condutorService.delete(id);
        return ResponseEntity.noContent().build();
    }
    @Operation( summary= "Consulta condutor pelo identificador"
              , description= "Serviço utilizado para consultar um condutor pelo seu identificador. </br>"
    )
    @GetMapping(value="/{id}", produces = "application/json")
    public ResponseEntity<CondutorDTO> findById(@PathVariable("id") String id) {
        var condutor = condutorService.findById(id);
        return ResponseEntity.ok(condutor);
    }
    @Operation( summary= "Consulta condutor com filtro"
            , description= "Serviço utilizado para consultar condutor. </br>" +
            "Filtros: </br>" +
            "situacao -> Situação do condutor - (ATIVO ou INATIVO)"
    )
    @GetMapping(produces = "application/json")
    public ResponseEntity<List<Condutor>> findAll() {
        var condutorList = condutorService.findAll();
        return ResponseEntity.ok(condutorList);
    }

}