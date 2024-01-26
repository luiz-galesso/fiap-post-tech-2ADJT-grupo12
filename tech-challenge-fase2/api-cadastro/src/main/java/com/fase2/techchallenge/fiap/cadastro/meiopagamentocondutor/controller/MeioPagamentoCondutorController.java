package com.fase2.techchallenge.fiap.cadastro.meiopagamentocondutor.controller;

import com.fase2.techchallenge.fiap.cadastro.meiopagamentocondutor.dto.MeioPagamentoCondutorRequestDTO;
import com.fase2.techchallenge.fiap.cadastro.meiopagamentocondutor.dto.MeioPagamentoCondutorResponseDTO;
import com.fase2.techchallenge.fiap.cadastro.meiopagamentocondutor.service.MeioPagamentoCondutorService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/meioPagamentoCondutor")
@Tag(name = "Meio Pagamento do Condutor", description = "Serviços para manipular o meio de pagamento")
public class MeioPagamentoCondutorController {

    @Autowired
    private MeioPagamentoCondutorService meioPagamentoCondutorService;

    @Operation(summary = "Insere um novo meio de pagamento favorito para o condutor"
            , description = "Serviço utilizado para inserir um novo meio de pagamento para o condutor.")
    @PostMapping(produces = "application/json")
    public ResponseEntity<MeioPagamentoCondutorResponseDTO> save(@RequestBody MeioPagamentoCondutorRequestDTO meioPagamentoCondutorRequestDTO) {
        return new ResponseEntity<>(meioPagamentoCondutorService.save(meioPagamentoCondutorRequestDTO), HttpStatus.CREATED);
    }

    @Operation(summary = "Atualiza o meio de pagamento"
            , description = "Serviço utilizado para atualizar o meio de pagamento.")
    @PutMapping(value = "/{id}", produces = "application/json")
    public ResponseEntity<MeioPagamentoCondutorResponseDTO> update(@PathVariable Long id, @RequestBody MeioPagamentoCondutorRequestDTO meioPagamentoCondutorRequestDTO) {
        return ResponseEntity.ok(meioPagamentoCondutorService.update(id, meioPagamentoCondutorRequestDTO));
    }

    @Operation(summary = "Consulta meio pagamento pelo condutor"
            , description = "Serviço utilizado para consultar um meio de pagamento pelo seu condutor. </br>"
    )
    @GetMapping(value = "/{emailCondutor}", produces = "application/json")
    public ResponseEntity<MeioPagamentoCondutorResponseDTO> findByCondutor(@PathVariable("emailCondutor") String emailCondutor) {
        return ResponseEntity.ok(meioPagamentoCondutorService.findByEmailCondutor(emailCondutor));
    }

    @Operation(summary = "Remove o meio de pagamento", description = "Serviço utilizado para remover o meio de pagamento.")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Long id) {
        meioPagamentoCondutorService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

}
