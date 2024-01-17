package com.fase2.techchallenge.fiap.cadastro.meiopagamentocondutor.controller;

import com.fase2.techchallenge.fiap.cadastro.condutor.dto.CondutorDTO;
import com.fase2.techchallenge.fiap.cadastro.condutor.entity.Condutor;
import com.fase2.techchallenge.fiap.cadastro.condutor.service.CondutorService;
import com.fase2.techchallenge.fiap.cadastro.meiopagamentocondutor.dto.MeioPagamentoCondutorDTO;
import com.fase2.techchallenge.fiap.cadastro.meiopagamentocondutor.service.MeioPagamentoCondutorService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/meioPagamentoCondutor")
@Tag(name = "meioPagamentoCondutor", description="Serviços para manipular o meio de pagamento " +
        "favorito do condutor")
public class MeioPagamentoCondutorController {

    @Autowired
    private MeioPagamentoCondutorService meioPagamentoCondutorService;

    @Operation( summary= "Insere um novo meio de pagamento favorito para o condutor"
            , description= "Serviço utilizado para inserir um novo meio de pagamento para o condutor.")
    @PostMapping(produces = "application/json")
    public ResponseEntity<MeioPagamentoCondutorDTO> save(@RequestBody MeioPagamentoCondutorDTO meioPagamentoCondutorDTO) {
        MeioPagamentoCondutorDTO meioPagamentoCondutorResponse = meioPagamentoCondutorService.save(meioPagamentoCondutorDTO);
        return new ResponseEntity<>(meioPagamentoCondutorResponse, HttpStatus.CREATED);
    }

    @Operation( summary= "Atualiza um condutor"
            , description= "Serviço utilizado para atualizar um condutor.")
    @PutMapping(value = "/{id}", produces = "application/json")
    public ResponseEntity<MeioPagamentoCondutorDTO> update(@PathVariable String id, @RequestBody MeioPagamentoCondutorDTO meioPagamentoCondutorDTO) {
        MeioPagamentoCondutorDTO meioPagamentoCondutorResponse = meioPagamentoCondutorService.update(id, meioPagamentoCondutorDTO);
        return ResponseEntity.ok(meioPagamentoCondutorResponse);
    }

    @Operation( summary= "Consulta meio pelo condutor"
            , description= "Serviço utilizado para consultar um meio de pagamento pelo seu condutor. </br>"
    )
    @GetMapping(value="/{id}", produces = "application/json")
    public ResponseEntity<MeioPagamentoCondutorDTO> findById(@PathVariable("id") String id) {
        var meioPagamentoFavorito = meioPagamentoCondutorService.findByEmailCondutor(id);
        return ResponseEntity.ok(meioPagamentoFavorito);
    }


}
