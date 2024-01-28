package com.fase2.techchallenge.fiap.gestaonotificacao.controller;

import com.fase2.techchallenge.fiap.gestaonotificacao.dto.NotificacaoRequestDTO;
import com.fase2.techchallenge.fiap.gestaonotificacao.dto.NotificacaoResponseDTO;
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

import java.util.List;

@RestController
@RequestMapping("/notificacao")
@Tag(name = "Notificacao", description = "Serviços para manipular as notificações")
public class NotificacaoController {

    @Autowired
    private NotificacaoService notificacaoService;

    @Operation(summary = "Consulta Notificações", description = "Serviço utilizado para consultar as notificações paginadas.")
    @GetMapping
    public ResponseEntity<Page<NotificacaoResponseDTO>> findAll(Pageable pageable) {
        var notificacoes = notificacaoService.findAll(pageable);
        return ResponseEntity.ok(notificacoes);
    }

    @Operation(summary = "Consulta a Notificação pelo Identificador", description = "Serviço utilizado para consultar a notificação pelo seu identificador.")
    @GetMapping("/{id}")
    public ResponseEntity<NotificacaoResponseDTO> findById(@PathVariable Long id) {
        var notificacao = notificacaoService.findById(id);
        return ResponseEntity.ok(notificacao);
    }

    @Operation(summary = "Consulta a Notificação pelo Veiculo", description = "Serviço utilizado para consultar a notificação pelo Veiculo.")
    @GetMapping("/veiculo/{idVeiculo}")
    public ResponseEntity<List<NotificacaoResponseDTO>> findByIdVeiculo(@PathVariable Integer idVeiculo) {
        var notificacao = notificacaoService.findByIdVeiculo(idVeiculo);
        return ResponseEntity.ok(notificacao);
    }

    @Operation(summary = "Remove a Notificação", description = "Serviço utilizado para remover a notificação.")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Long id) {
        notificacaoService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @Operation(summary = "Insere uma nova notificação", description = "Serviço utilizado para inserir uma notificação.")
    @PostMapping
    public ResponseEntity<NotificacaoResponseDTO> save(@RequestBody @Valid NotificacaoRequestDTO notificacaoRequestDTO) {
        NotificacaoResponseDTO notifacaoResponse = notificacaoService.save(notificacaoRequestDTO);
        return new ResponseEntity<>(notifacaoResponse, HttpStatus.CREATED);
    }

    @Operation(summary = "Atualiza a notificação", description = "Serviço utilizado para atualizar notificação.")
    @PutMapping(value = "/{id}", produces = "application/json")
    public ResponseEntity<NotificacaoResponseDTO> update(@PathVariable Long id, @RequestBody NotificacaoRequestDTO notificacaoRequestDTO) {
        NotificacaoResponseDTO notificacaoResponse = notificacaoService.update(id, notificacaoRequestDTO);
        return ResponseEntity.ok(notificacaoResponse);
    }

    @Operation(summary = "Finaliza a notificação", description = "Serviço utilizado para finalizar a notificação.")
    @PutMapping(value = "/finaliza-notificacao/{id}", produces = "application/json")
    public ResponseEntity<NotificacaoResponseDTO> finalizaNotificacao(@PathVariable Long id, @RequestBody NotificacaoRequestDTO notificacaoRequestDTO) {
        NotificacaoResponseDTO notificacaoResponse = notificacaoService.finaliza(id, notificacaoRequestDTO);
        return ResponseEntity.ok(notificacaoResponse);
    }
}
