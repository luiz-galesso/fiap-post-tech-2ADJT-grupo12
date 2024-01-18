package com.fase2.techchallenge.fiap.pagamento.gestaopagamento.controller;

import com.fase2.techchallenge.fiap.pagamento.gestaopagamento.dto.SolicitacaoReciboDTO;
import com.fase2.techchallenge.fiap.pagamento.gestaopagamento.entity.MeioPagamento;
import com.fase2.techchallenge.fiap.pagamento.gestaopagamento.service.MeioPagamentoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/meiospagamento")
@Tag(name = "Meios de Pagamento", description="Serviços para manipular meios de pagamento")
public class MeioPagamentoController {

    @Autowired
    private MeioPagamentoService meioPagamentoService;

    @Operation( summary= "Insere um novo meio de pagamento"
              , description= "Serviço utilizado para inserir um meio de pagamento.")
    @PostMapping(produces = "application/json")
    public ResponseEntity<SolicitacaoReciboDTO> save(@RequestBody @Valid SolicitacaoReciboDTO solicitacaoReciboDTO) {
        SolicitacaoReciboDTO meioPagamentoResponse = meioPagamentoService.save(solicitacaoReciboDTO);
        return new ResponseEntity<>(meioPagamentoResponse, HttpStatus.CREATED);
    }
    @Operation( summary= "Atualiza um meio de pagamento"
              , description= "Serviço utilizado para atualizar um meio de pagamento.")
    @PutMapping(value = "/{id}", produces = "application/json")
    public ResponseEntity<SolicitacaoReciboDTO> update(@PathVariable String id, @RequestBody SolicitacaoReciboDTO solicitacaoReciboDTO) {
        SolicitacaoReciboDTO meioPagamentoResponse = meioPagamentoService.update(id, solicitacaoReciboDTO);
        return ResponseEntity.ok(meioPagamentoResponse);
    }
    @Operation( summary= "Remove um meio de pagamento"
              , description= "Serviço utilizado para remover um meio de pagamento.")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable String id) {
        meioPagamentoService.delete(id);
        return ResponseEntity.noContent().build();
    }
    @Operation( summary= "Consulta meios de pagamento pelo identificador"
              , description= "Serviço utilizado para consultar um meio de pagamento pelo seu identificador. </br>"
    )
    @GetMapping(value="/{id}", produces = "application/json")
    public ResponseEntity<SolicitacaoReciboDTO> findById(@PathVariable("id") String id) {
        var meioPagamento = meioPagamentoService.findById(id);
        return ResponseEntity.ok(meioPagamento);
    }
    @Operation( summary= "Consulta meios de pagamento com filtro"
            , description= "Serviço utilizado para consultar meios de pagamento. </br>" +
            "Filtros: </br>" +
            "situacao -> Situação do meio de pagamento - (ATIVO ou INATIVO)"
    )
    @GetMapping(produces = "application/json")
    public ResponseEntity<List<MeioPagamento>> findAll() {
        var meioPagamentoList = meioPagamentoService.findAll();
        return ResponseEntity.ok(meioPagamentoList);
    }

}