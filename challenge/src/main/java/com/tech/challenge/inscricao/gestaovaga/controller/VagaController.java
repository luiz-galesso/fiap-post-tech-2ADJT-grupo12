package com.tech.challenge.inscricao.gestaovaga.controller;

import com.tech.challenge.inscricao.gestaoetapa.service.EtapaService;
import com.tech.challenge.inscricao.gestaoperfil.entity.Perfil;
import com.tech.challenge.inscricao.gestaovaga.dto.AdicionaEtapaDTO;
import com.tech.challenge.inscricao.gestaovaga.dto.SolicitaVagaDTO;
import com.tech.challenge.inscricao.gestaovaga.dto.SolicitaVagaRespostaDTO;
import com.tech.challenge.inscricao.gestaovaga.entity.SolicitaVaga;
import com.tech.challenge.inscricao.gestaovaga.entity.Vaga;
import com.tech.challenge.inscricao.gestaovaga.service.VagaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("/vaga")
public class VagaController {

    @Autowired
    private VagaService vagaService;

    @Autowired
    private EtapaService etapaService;

    @PostMapping("/adicionarEtapas")
    public ResponseEntity<?> adicionaEtapa(@RequestBody AdicionaEtapaDTO adicionaEtapaDTO)
    {
        try
        {
            Vaga vaga = vagaService.findById(adicionaEtapaDTO.idVaga());
            etapaService.validaEtapas(adicionaEtapaDTO.etapas());
            vagaService.adicionaEtapas(vaga, adicionaEtapaDTO.etapas());

            return ResponseEntity.ok().build();
        }
        catch (Exception e)
        {
            //Criar exception especifica
            System.out.println("Erro ao adicionar etapa: " + e.getMessage());
            return ResponseEntity.badRequest().body("Erro ao adicionar etapa");
        }
    }

    @DeleteMapping("/removerEtapas")
    public ResponseEntity<?> removerEtapa(@RequestBody AdicionaEtapaDTO adicionaEtapaDTO)
    {
        try
        {
            Vaga vaga = vagaService.findById(adicionaEtapaDTO.idVaga());
            etapaService.validaEtapas(adicionaEtapaDTO.etapas());
            vagaService.removeEtapas(vaga, adicionaEtapaDTO.etapas());

            return ResponseEntity.ok().build();
        }
        catch (Exception e)
        {
            //Criar exception especifica
            System.out.println("Erro ao remover etapa: " + e.getMessage());
            return ResponseEntity.badRequest().body("Erro ao remover Etapa");
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Vaga> findById(@PathVariable("id") Long id) {
        var vaga = vagaService.findById(id);
        return ResponseEntity.ok(vaga);
    }


}
