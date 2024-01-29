package com.fase2.techchallenge.fiap.fiscalizacao.gestaofiscalizacao.controller;

import com.fase2.techchallenge.fiap.fiscalizacao.gestaofiscalizacao.service.FiscalizacaoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;



@RestController
@RequestMapping("/fiscalizacao")
@Tag(name = "Fiscalizacao", description="Serviços para fiscalizar situação de veículos estacionados.")
public class FiscalizacaoController {

    @Autowired
    FiscalizacaoService fiscalizacaoService;

    @Operation( summary= "Fiscaliza um veículo"
            , description= "Fiscaliza um veículo utilizando a placa")
    @GetMapping(path="/placa/{placa}", produces = "application/json")
    public ResponseEntity<?> fiscalizar(@PathVariable String placa){
        return new ResponseEntity<>(fiscalizacaoService.fiscalizar(placa), HttpStatus.OK);
    }

}