package com.tech.challenge.inscricao.gestaovaga.service;

import com.tech.challenge.processoseletivo.gestaoetapa.entity.Etapa;
import com.tech.challenge.acesso.gestaousuario.controller.exception.ControllerNotFoundException;
import com.tech.challenge.inscricao.gestaovaga.controller.exception.DataExpiradaException;
import com.tech.challenge.inscricao.gestaovaga.entity.Vaga;
import com.tech.challenge.inscricao.gestaovaga.repository.VagaRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.ArrayList;
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

    /*public void adicionaEtapas(Vaga vaga, ArrayList<Etapa> etapas) {
        for (Etapa etapa : etapas) {
            vaga.getEtapas().add(etapa);
        }
        //validar para não deixar adicionar a mesma etapa duas vezes
        vagaRepository.save(vaga);
    }*/

    //public void removeEtapas(Vaga vaga, ArrayList<Etapa> etapas) {
//        for (Etapa etapa : etapas) {
//            vaga.getEtapas().remove(etapa);
//        }
    //    vagaRepository.delete(vaga);
    //}

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
