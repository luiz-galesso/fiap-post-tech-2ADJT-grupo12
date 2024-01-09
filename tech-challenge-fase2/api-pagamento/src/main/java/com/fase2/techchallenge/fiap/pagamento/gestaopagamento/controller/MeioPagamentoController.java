package com.fase2.techchallenge.fiap.pagamento.gestaopagamento.controller;

import com.fase2.techchallenge.fiap.pagamento.gestaopagamento.dto.MeioPagamentoDTO;
import com.fase2.techchallenge.fiap.pagamento.gestaopagamento.entity.MeioPagamento;
import com.fase2.techchallenge.fiap.pagamento.gestaopagamento.service.MeioPagamentoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/meiospagamento")
public class MeioPagamentoController {

    @Autowired
    private MeioPagamentoService meioPagamentoService;

    @PostMapping
    public ResponseEntity<MeioPagamentoDTO> save(@RequestBody @Valid MeioPagamentoDTO meioPagamentoDTO) {
        MeioPagamentoDTO meioPagamentoResponse = meioPagamentoService.save(meioPagamentoDTO);
        return new ResponseEntity<>(meioPagamentoResponse, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<MeioPagamentoDTO> update(@PathVariable String id, @RequestBody MeioPagamentoDTO meioPagamentoDTO) {
        MeioPagamentoDTO meioPagamentoResponse = meioPagamentoService.update(id, meioPagamentoDTO);
        return ResponseEntity.ok(meioPagamentoResponse);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable String id) {
        meioPagamentoService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<MeioPagamentoDTO> findById(@PathVariable("id") String id) {
        var meioPagamento = meioPagamentoService.findById(id);
        return ResponseEntity.ok(meioPagamento);
    }

    @GetMapping
    public ResponseEntity<List<MeioPagamento>> findAll() {
        var meioPagamentoList = meioPagamentoService.findAll();
        return ResponseEntity.ok(meioPagamentoList);
    }

}