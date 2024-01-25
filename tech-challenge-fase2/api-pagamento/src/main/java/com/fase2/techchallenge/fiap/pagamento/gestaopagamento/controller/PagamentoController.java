package com.fase2.techchallenge.fiap.pagamento.gestaopagamento.controller;

import com.fase2.techchallenge.fiap.pagamento.gestaopagamento.dto.EnvioPagamentoDTO;
import com.fase2.techchallenge.fiap.pagamento.gestaopagamento.dto.EstornarPagamentoDTO;
import com.fase2.techchallenge.fiap.pagamento.gestaopagamento.dto.RetornoPagamentoDTO;
import com.fase2.techchallenge.fiap.pagamento.gestaopagamento.service.PagamentoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/pagamento")
@Tag(name = "Pagamento", description = "Serviços para manipular pagamentos")
public class PagamentoController {
    private final PagamentoService pagamentoService;

    public PagamentoController(PagamentoService pagamentoService) {
        this.pagamentoService = pagamentoService;
    }

    @Operation(summary = "Solicita um novo pagamento"
            , description = "Serviço utilizado para solicitar um pagamento")
    @PostMapping(produces = "application/json")
    public ResponseEntity<RetornoPagamentoDTO> registrar(@RequestBody @Valid EnvioPagamentoDTO envioPagamentoDTO) {
        RetornoPagamentoDTO retornoPagamentoDTO = pagamentoService.gerarPagamento(envioPagamentoDTO);
        return new ResponseEntity<>(retornoPagamentoDTO, HttpStatus.CREATED);
    }

    @Operation(summary = "Estornar um Pagamento"
            , description = "Serviço utilizado para atualizar um condutor.")
    @PutMapping(value = "/{id}", produces = "application/json")
    public ResponseEntity<RetornoPagamentoDTO> estornarPagamento(@PathVariable Long id, @RequestBody EstornarPagamentoDTO estornarPagamentoDTO) {
        RetornoPagamentoDTO retornoPagamentoDTO = pagamentoService.estornarPagamento(id, estornarPagamentoDTO);
        return ResponseEntity.ok(retornoPagamentoDTO);
    }
}
