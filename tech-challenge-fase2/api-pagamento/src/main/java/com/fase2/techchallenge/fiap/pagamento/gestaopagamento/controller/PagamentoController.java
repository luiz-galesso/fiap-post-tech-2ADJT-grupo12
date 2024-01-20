package com.fase2.techchallenge.fiap.pagamento.gestaopagamento.controller;

import com.fase2.techchallenge.fiap.pagamento.gestaopagamento.dto.EnvioPagamentoDTO;
import com.fase2.techchallenge.fiap.pagamento.gestaopagamento.service.PagamentoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/pagamento")
@Tag(name = "Pagamento", description = "Serviços para manipular pagamentos")
public class PagamentoController {
    private final PagamentoService pagamentoService;

    public PagamentoController(PagamentoService pagamentoService) {
        this.pagamentoService = pagamentoService;
    }

    @Operation(summary = "Registra um novo pagamento"
            , description = "Serviço utilizado para registrar um pagamento")
    @PostMapping(produces = "application/json")
    public ResponseEntity<EnvioPagamentoDTO> registrar(@RequestBody @Valid EnvioPagamentoDTO envioPagamentoDTO) {
        EnvioPagamentoDTO envioPagamentoResponse = pagamentoService.gerarPagamento(envioPagamentoDTO);
        return new ResponseEntity<>(envioPagamentoResponse, HttpStatus.CREATED);
    }
}
