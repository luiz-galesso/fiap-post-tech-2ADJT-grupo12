package com.fase2.techchallenge.fiap.pagamento.gestaorecibo.controller;

import com.fase2.techchallenge.fiap.pagamento.gestaorecibo.dto.SolicitacaoReciboDTO;
import com.fase2.techchallenge.fiap.pagamento.gestaorecibo.entity.Recibo;
import com.fase2.techchallenge.fiap.pagamento.gestaorecibo.service.ReciboService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/recibo")
@Tag(name = "Recibo", description = "Serviços para geração e impressão de recibos")
public class ReciboController {

    private final ReciboService reciboService;

    public ReciboController(ReciboService reciboService) {
        this.reciboService = reciboService;
    }

    @GetMapping(value = "listar/{idCondutor}", produces = "application/json")
    @Operation(summary = "Lista todos os recibos de um condutor",
            description = "Serviço utilizado para listar todos os recibos pelo email de um condutor")
    public ResponseEntity<List<Recibo>> listarRecibos(@PathVariable String idCondutor) {
        List<Recibo> recibos = reciboService.listarRecibos(idCondutor);
        return ResponseEntity.ok(recibos);
    }

    @Operation(summary = "Insere uma nova solicitação de geração de Recibo"
            , description = "Serviço utilizado para inserir  uma nova solicitação de geração de Recibo.")
    @PostMapping(produces = "application/json")
    public ResponseEntity<SolicitacaoReciboDTO> gerarRecibo(@RequestBody @Valid SolicitacaoReciboDTO solicitacaoReciboDTO) {
        SolicitacaoReciboDTO reciboResponse = reciboService.gerarRecibo(solicitacaoReciboDTO);
        return new ResponseEntity<>(reciboResponse, HttpStatus.CREATED);
    }

    @Operation(summary = "Retorna conteúdo do Recibo para Impressão"
            , description = "Serviço utilizado retornar o conteúdo de um recibo previamente solicitado geração. </br>"
    )
    @GetMapping(value = "/{id}", produces = "application/json")
    public ResponseEntity<Recibo> findById(@PathVariable("id") Long id) {
        var recibo = reciboService.imprimirRecibo(id);
        return ResponseEntity.ok(recibo);
    }

    @Operation(summary = "Cancela um recibo", description = "Serviço para cancelamento de Recibos")
    @PutMapping(value = "/cancelar/{idPagamento}", produces = "application/json")
    public ResponseEntity<Recibo> cancelarRecibo(@PathVariable Long idPagamento) {
        Recibo recibo = reciboService.cancelarRecibo(idPagamento);
        return ResponseEntity.ok(recibo);
    }

}