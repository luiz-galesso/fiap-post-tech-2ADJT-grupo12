package com.tech.challenge.processoseletivo.gestaoentrevista.service;

import com.tech.challenge.processoseletivo.gestaoentrevista.dto.EntrevistaDTO;
import com.tech.challenge.processoseletivo.gestaoentrevista.entity.Entrevista;
import com.tech.challenge.processoseletivo.gestaoentrevista.repository.EntrevistaRepository;
import com.tech.challenge.inscricao.gestaousuario.controller.exception.ControllerNotFoundException;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.Collection;
import java.util.stream.Collectors;

@Service
public class EntrevistaService {

    @Autowired
    private EntrevistaRepository entrevistaRepository;

    public Collection<EntrevistaDTO> findAll() {
        var entrevistas = entrevistaRepository.findAll();
        return entrevistas
                .stream()
                .map(this::toEntrevistaDTO)
                .collect(Collectors.toList());
    }

    public EntrevistaDTO findById(Long id) {
        var entrevista = entrevistaRepository.findById(id).orElseThrow(() -> new ControllerNotFoundException("Entrevista não encontrada"));
        return toEntrevistaDTO(entrevista);
    }

    public EntrevistaDTO save(EntrevistaDTO entrevistaDTO) {
        Entrevista entrevista = toEntrevista(entrevistaDTO);
        entrevista = entrevistaRepository.save(entrevista);
        return toEntrevistaDTO(entrevista);
    }

    public EntrevistaDTO update(Long id, EntrevistaDTO entrevistaDTO) {
        try {
            Entrevista entrevista = entrevistaRepository.getReferenceById(id);
            entrevista.setDataEntrevista(entrevistaDTO.dataEntrevista());
            entrevista.setLocal(entrevistaDTO.local());
            entrevista.setCandidato(entrevistaDTO.candidato());
            entrevista.setEntrevistador(entrevistaDTO.entrevistador());
            entrevista = entrevistaRepository.save(entrevista);

            return toEntrevistaDTO(entrevista);
        } catch (EntityNotFoundException e) {
            throw new ControllerNotFoundException("Entrevista não encontrada");
        }
    }

    public void delete(Long id) {
        entrevistaRepository.deleteById(id);
    }

    private EntrevistaDTO toEntrevistaDTO(Entrevista entrevista) {
        return new EntrevistaDTO(entrevista.getId(),
                entrevista.getDataEntrevista(),
                entrevista.getLocal(),
                entrevista.getCandidato(),
                entrevista.getEntrevistador()
        );
    }

    private Entrevista toEntrevista(EntrevistaDTO entrevistaDTO) {
        return new Entrevista(entrevistaDTO.id(),
                entrevistaDTO.dataEntrevista(),
                entrevistaDTO.local(),
                entrevistaDTO.candidato(),
                entrevistaDTO.entrevistador()
        );
    }

}
