package com.fase2.techchallenge.fiap.tarifa.gestaotarifa.controller;

import com.fase2.techchallenge.fiap.tarifa.gestaotarifa.dto.TarifaDTO;
import com.fase2.techchallenge.fiap.tarifa.gestaotarifa.dto.TarifaUpdateDTO;
import com.fase2.techchallenge.fiap.tarifa.gestaotarifa.service.TarifaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
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
    public ResponseEntity<?> update(@PathVariable String id, @RequestBody TarifaUpdateDTO tarifaUpdateDTO) {
        return ResponseEntity.ok(tarifaService.update(id, tarifaUpdateDTO).toTarifaDTO());
    }

    @Operation( summary= "Consulta tarifa pelo identificador"
              , description= "Serviço utilizado para consultar uma tarifa pelo seu identificador. </br>"
    )
    @GetMapping(value="/{id}", produces = "application/json")
    public ResponseEntity<?> findById(@PathVariable("id") String id) {
        var tarifa = tarifaService.findById(id);
        return ResponseEntity.ok(tarifa.toTarifaDTO());
    }

}