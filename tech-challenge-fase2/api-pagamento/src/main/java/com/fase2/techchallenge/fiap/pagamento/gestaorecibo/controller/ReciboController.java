package com.fase2.techchallenge.fiap.pagamento.gestaorecibo.controller;

import com.fase2.techchallenge.fiap.pagamento.gestaorecibo.dto.SolicitacaoReciboDTO;
import com.fase2.techchallenge.fiap.pagamento.gestaorecibo.service.ReciboService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;



@RestController
@RequestMapping("/recibo")
@Tag(name = "Recibo", description="Serviços para geração e impressão de recibos")
public class ReciboController {

    private final ReciboService reciboService;

    public ReciboController(ReciboService reciboService) {
        this.reciboService = reciboService;
    }

    @Operation( summary= "Insere uma nova solicitação de geração de Recibo"
              , description= "Serviço utilizado para inserir  uma nova solicitação de geração de Recibo.")
    @PostMapping(produces = "application/json")
    public ResponseEntity<SolicitacaoReciboDTO> gerarRecibo(@RequestBody @Valid SolicitacaoReciboDTO solicitacaoReciboDTO) {
        SolicitacaoReciboDTO reciboResponse = reciboService.gerarRecibo(solicitacaoReciboDTO);
        return new ResponseEntity<>(reciboResponse, HttpStatus.CREATED);
    }
}