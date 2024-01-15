package com.fase2.techchallenge.fiap.tarifa.gestaotarifa.controller;

import com.fase2.techchallenge.fiap.tarifa.gestaotarifa.dto.TarifaDTO;
import com.fase2.techchallenge.fiap.tarifa.gestaotarifa.service.TarifaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/tarifa")
@Tag(name = "Tarifa", description="Serviços para manipular tarifa")
public class TarifaController {

    @Autowired
    private TarifaService tarifaService;

    @Operation( summary= "Atualiza uma tarifa"
              , description= "Serviço utilizado para atualizar uma tarifa.")
    @PutMapping(value = "/{id}", produces = "application/json")
    public ResponseEntity<TarifaDTO> update(@PathVariable String id, @RequestBody TarifaDTO tarifaDTO) {
        TarifaDTO tarifaResponse = tarifaService.update(id, tarifaDTO);
        return ResponseEntity.ok(tarifaResponse);
    }

    @Operation( summary= "Consulta tarifa pelo identificador"
              , description= "Serviço utilizado para consultar uma tarifa pelo seu identificador. </br>"
    )
    @GetMapping(value="/{id}", produces = "application/json")
    public ResponseEntity<TarifaDTO> findById(@PathVariable("id") String id) {
        var tarifa = tarifaService.findById(id);
        return ResponseEntity.ok(tarifa);
    }

}