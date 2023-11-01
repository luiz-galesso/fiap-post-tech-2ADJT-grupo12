package com.tech.challenge.inscricao.gestaovaga.service;

import com.tech.challenge.inscricao.gestaovaga.entity.SolicitacaoVaga;
import com.tech.challenge.inscricao.gestaovaga.service.filtros.VagaFiltro;
import com.tech.challenge.acesso.gestaousuario.controller.exception.ControllerNotFoundException;
import com.tech.challenge.inscricao.gestaovaga.controller.exception.DataExpiradaException;
import com.tech.challenge.inscricao.gestaovaga.entity.Vaga;
import com.tech.challenge.inscricao.gestaovaga.repository.VagaRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class VagaService {
    @Autowired
    private VagaRepository vagaRepository;

    public Vaga criarVaga(Vaga vaga) {
        return vagaRepository.save(vaga);
    }

    public List<Vaga> findByFiltro(String idSolicitante, String nivel, String idAvaliador, String situacao)
    {
        VagaFiltro filtro = new VagaFiltro(idSolicitante, nivel, situacao);
        return vagaRepository.findAll(filtro);
    }

    public Vaga findById(Long id) {
        try {
            return vagaRepository.findById(id).orElseThrow(() -> new ControllerNotFoundException(
                  "Vaga não localizada"
            ));


        } catch (EntityNotFoundException e) {
            throw new ControllerNotFoundException("Vaga não localizada");
        }
    }

    public void validarVagaExpirada(Long idVaga) {
        Vaga vaga = findById(idVaga);
        if(!vaga.getDataExpiracao().after(new Date().from(Instant.now()))){
            throw new DataExpiradaException("A vaga está expirada");
        }

    }

    public Optional<List<Vaga>> listaVagasDisponiveis()
    {
        return vagaRepository.listaVagasDisponiveis();
    }

}
