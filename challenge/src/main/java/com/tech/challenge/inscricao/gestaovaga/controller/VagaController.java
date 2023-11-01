package com.tech.challenge.inscricao.gestaovaga.controller;

import com.tech.challenge.processoseletivo.gestaoetapa.service.EtapaService;
import com.tech.challenge.inscricao.gestaovaga.entity.Vaga;
import com.tech.challenge.inscricao.gestaovaga.service.VagaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/vagas")
public class VagaController {

    @Autowired
    private VagaService vagaService;

    @Autowired
    private EtapaService etapaService;

    @GetMapping("/{id}")
    public ResponseEntity<Vaga> findById(@PathVariable("id") Long id) {
        var vaga = vagaService.findById(id);
        return ResponseEntity.ok(vaga);
    }

    //@GetMapping
    //public ResponseEntity<?> listarVagasDisponiveis()
    //{
      //  return ResponseEntity.ok(vagaService.listaVagasDisponiveis());
    //}
    @GetMapping
    public ResponseEntity<?> getSolicitacoes(
            @RequestParam(required = false) String idSolicitante,
            @RequestParam(required = false) String nivel,
            @RequestParam(required = false) String idAvaliador,
            @RequestParam(required = false) String situacao)

    {
        return ResponseEntity.ok(vagaService.findByFiltro(idSolicitante, nivel, idAvaliador, situacao));
    }

}
