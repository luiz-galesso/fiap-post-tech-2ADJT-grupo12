package com.fase2.techchallenge.fiap.cadastro.veiculo.controller;

import com.fase2.techchallenge.fiap.cadastro.condutor.entity.Condutor;
import com.fase2.techchallenge.fiap.cadastro.veiculo.dto.VeiculoRequestDTO;
import com.fase2.techchallenge.fiap.cadastro.veiculo.dto.VeiculoUpdateDTO;
import com.fase2.techchallenge.fiap.cadastro.veiculo.entity.Veiculo;
import com.fase2.techchallenge.fiap.cadastro.veiculo.service.VeiculoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.transaction.TransactionalException;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

/**
 * @author thiago-ribeiro
 * @see VeiculoService
 * @see Veiculo
 * @see VeiculoRequestDTO
 */
@RestController
@RequestMapping("/veiculos")
@Tag(name = "Veiculo", description="Controladora de requisições de veículos")
public class VeiculoController
{
    private final VeiculoService service;

    public VeiculoController(VeiculoService service) {
        this.service = service;
    }

    @GetMapping
    @Operation( summary= "Lista veículos"
            , description= "Lista todos os veículos salvos.")
    public ResponseEntity<?> getVeiculos()
    {
        return ResponseEntity.ok(service.listAll());
    }

    @GetMapping("/{id}")
    @Operation( summary= "Retorna veículo"
            , description= "Retorna veículo pelo ID")
    public ResponseEntity<?> getVeiculo(@PathVariable Integer id)
    {
        return ResponseEntity.ok(service.findById(id));
    }

    @GetMapping("/condutor")
    @Operation( summary= "Lista veículos"
            , description= "Lista todos os veículos salvos por condutor")
    public ResponseEntity<?> getVeiculosPorCondutor(@RequestParam String email)
    {
        return ResponseEntity.ok(this.service.listAllByCondutor(email));
    }

    @PostMapping
    @Operation( summary= "Salva veículo"
            , description= "Salva um novo veículo vinculado ao condutor")
    public ResponseEntity<?> postVeiculo(@RequestBody VeiculoRequestDTO veiculoRequestDTO)
    {
        Condutor condutor = new Condutor();
        try
        {
            Veiculo veiculo = new Veiculo();
            condutor.setEmail(veiculoRequestDTO.emailCondutor());
            veiculo.setPlaca(veiculoRequestDTO.placa());
            veiculo.setCondutor(condutor);
            return ResponseEntity.status(HttpStatusCode.valueOf(201)).body(this.service.save(veiculo));
        }
        catch (TransactionalException t)
        {
            return ResponseEntity.internalServerError().body("Ocorreu um erro na persistência do veículo.");
        }
    }

    @PutMapping("/{id}")
    @Operation( summary= "Atualiza veículo"
            , description= "Atualiza um veículo vinculado ao condutor")
    public ResponseEntity<?> putVeiculo(@PathVariable Integer id, @RequestBody VeiculoUpdateDTO veiculoUpdateDTO)
    {
        try
        {
            Optional<Veiculo> optionalVeiculo = this.service.findById(id);
            if(optionalVeiculo.isEmpty()) return ResponseEntity.noContent().build();
            return ResponseEntity.ok(this.service.update(optionalVeiculo.get(), veiculoUpdateDTO));
        }
        catch (Exception e)
        {
            System.out.println("ERRO ao atualizar veiculo: " + e.getMessage());
            return ResponseEntity.internalServerError().body("Erro ao atualizar veículo");
        }
    }

    @DeleteMapping("/{id}")
    @Operation( summary= "Apaga veículo"
            , description= "Apaga um veículo vinculado ao condutor")
    public ResponseEntity<?> deleteVeiculo(@PathVariable Integer id)
    {
        this.service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
