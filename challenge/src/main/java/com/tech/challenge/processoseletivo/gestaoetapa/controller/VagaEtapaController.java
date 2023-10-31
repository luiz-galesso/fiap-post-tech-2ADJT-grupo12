package com.tech.challenge.processoseletivo.gestaoetapa.controller;

import com.tech.challenge.processoseletivo.gestaoetapa.dto.VagaEtapaDTO;
import com.tech.challenge.processoseletivo.gestaoetapa.service.VagaEtapaService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/vagaetapas")
public class VagaEtapaController {

    private final VagaEtapaService vagaEtapaService;

    public VagaEtapaController(VagaEtapaService vagaEtapaCandidatoService) {
        this.vagaEtapaService = vagaEtapaCandidatoService;
    }

    @PostMapping
    public ResponseEntity<VagaEtapaDTO> save(@RequestBody VagaEtapaDTO vagaEtapaDTO) {
        VagaEtapaDTO vagaEtapa = vagaEtapaService.save(vagaEtapaDTO);
        return new ResponseEntity<>(vagaEtapa, HttpStatus.CREATED);
    }

    @DeleteMapping("/{idVaga}/{idEtapa}/{ordem}")
    public ResponseEntity<Void> delete(@PathVariable Long idVaga, @PathVariable Long idEtapa, @PathVariable Integer ordem) {
        vagaEtapaService.delete(idVaga,idEtapa,ordem );
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{idVaga}/{idEtapa}/{ordem}")
    public ResponseEntity<VagaEtapaDTO> findById(@PathVariable Long idVaga, @PathVariable Long idEtapa, @PathVariable Integer ordem) {
        VagaEtapaDTO vagaEtapaDTO = vagaEtapaService.findById(idVaga, idEtapa,ordem );
        return ResponseEntity.ok(vagaEtapaDTO);
    }

    @GetMapping
    public ResponseEntity<?> findByFiltro(
            @RequestParam(required = false) Long idVaga,
            @RequestParam(required = false) Long idEtapa)
    {
        return ResponseEntity.ok(vagaEtapaService.findByFiltro(idVaga, idEtapa));
    }
}
