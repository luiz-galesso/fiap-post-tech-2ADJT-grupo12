package com.fase2.techchallenge.fiap.cadastro.meiopagamentocondutor.controller;

import com.fase2.techchallenge.fiap.cadastro.meiopagamentocondutor.dto.MeioPagamentoCondutorDTO;
import com.fase2.techchallenge.fiap.cadastro.meiopagamentocondutor.service.MeioPagamentoCondutorService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/meioPagamentoCondutor")
@Tag(name = "meioPagamentoCondutor", description = "Serviços para manipular o meio de pagamento " +
        "favorito do condutor")
public class MeioPagamentoCondutorController {

    @Autowired
    private MeioPagamentoCondutorService meioPagamentoCondutorService;

    @Operation(summary = "Insere um novo meio de pagamento favorito para o condutor"
            , description = "Serviço utilizado para inserir um novo meio de pagamento para o condutor.")
    @PostMapping(produces = "application/json")
    public ResponseEntity<MeioPagamentoCondutorDTO> save(@RequestBody MeioPagamentoCondutorDTO meioPagamentoCondutorDTO) {
        MeioPagamentoCondutorDTO meioPagamentoCondutorResponse = meioPagamentoCondutorService.save(meioPagamentoCondutorDTO);
        return new ResponseEntity<>(meioPagamentoCondutorResponse, HttpStatus.CREATED);
    }

    @Operation(summary = "Atualiza o meio de pagamento"
            , description = "Serviço utilizado para atualizar o meio de pagamento.")
    @PutMapping(value = "/{id}", produces = "application/json")
    public ResponseEntity<MeioPagamentoCondutorDTO> update(@PathVariable Long id, @RequestBody MeioPagamentoCondutorDTO meioPagamentoCondutorDTO) {
        MeioPagamentoCondutorDTO meioPagamentoCondutorResponse = meioPagamentoCondutorService.update(id, meioPagamentoCondutorDTO);
        return ResponseEntity.ok(meioPagamentoCondutorResponse);
    }

    @Operation(summary = "Consulta meio pagamento pelo condutor"
            , description = "Serviço utilizado para consultar um meio de pagamento pelo seu condutor. </br>"
    )
    @GetMapping(value = "/{emailCondutor}", produces = "application/json")
    public ResponseEntity<MeioPagamentoCondutorDTO> findByCondutor(@PathVariable("emailCondutor") String emailCondutor) {
        var meioPagamentoFavorito = meioPagamentoCondutorService.findByEmailCondutor(emailCondutor);
        return ResponseEntity.ok(meioPagamentoFavorito);
    }

    @Operation(summary = "Remove o meio de pagamento", description = "Serviço utilizado para remover o meio de pagamento.")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Long id) {
        meioPagamentoCondutorService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

}
