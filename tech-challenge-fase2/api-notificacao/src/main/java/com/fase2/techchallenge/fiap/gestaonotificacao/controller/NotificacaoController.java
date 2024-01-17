package com.fase2.techchallenge.fiap.gestaonotificacao.controller;

import com.fase2.techchallenge.fiap.gestaonotificacao.dto.NotificacaoDTO;
import com.fase2.techchallenge.fiap.gestaonotificacao.service.NotificacaoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/notificoes")
@Tag(name = "Notificacao", description = "Serviços para manipular as notificações")
public class NotificacaoController {

    @Autowired
    private NotificacaoService notificacaoService;

    @Operation(summary = "Consulta Notificações", description = "Serviço utilizado para consultar as notificações paginadas.")
    @GetMapping
    public ResponseEntity<Page<NotificacaoDTO>> findAll(Pageable pageable) {
        var notificacoes = notificacaoService.findAll(pageable);
        return ResponseEntity.ok(notificacoes);
    }

    @Operation(summary = "Consulta a Notificação pelo Identificador", description = "Serviço utilizado para consultar a notificação pelo seu identificador.")
    @GetMapping("/{id}")
    public ResponseEntity<NotificacaoDTO> findById(@PathVariable Long id) {
        var notificacao = notificacaoService.findById(id);
        return ResponseEntity.ok(notificacao);
    }

    @Operation(summary = "Remove a Notificação", description = "Serviço utilizado para remover a notificação.")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(Long id) {
        notificacaoService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @Operation(summary = "Insere uma nova notificação", description = "Serviço utilizado para inserir uma notificação.")
    @PostMapping
    public ResponseEntity<NotificacaoDTO> save(@RequestBody @Valid NotificacaoDTO notificacaoDTO) {
        NotificacaoDTO notifacaoResponse = notificacaoService.save(notificacaoDTO);
        return new ResponseEntity<>(notifacaoResponse, HttpStatus.CREATED);
    }

    @Operation(summary = "Atualiza a notificação", description = "Serviço utilizado para atualizar notificação.")
    @PutMapping(value = "/{id}", produces = "application/json")
    public ResponseEntity<NotificacaoDTO> update(@PathVariable Long id, @RequestBody NotificacaoDTO notificacaoDTO) {
        NotificacaoDTO notificacaoResponse = notificacaoService.update(id, notificacaoDTO);
        return ResponseEntity.ok(notificacaoResponse);
    }
}
