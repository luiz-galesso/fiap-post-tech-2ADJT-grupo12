package com.tech.challenge.inscricao.gestaoetapa.service;

import com.tech.challenge.inscricao.gestaoetapa.dto.EtapaDTO;
import com.tech.challenge.inscricao.gestaoetapa.dto.EtapaRequestDTO;
import com.tech.challenge.inscricao.gestaoetapa.entity.Etapa;
import com.tech.challenge.inscricao.gestaoetapa.repository.EtapaRepository;
import com.tech.challenge.inscricao.gestaousuario.controller.exception.ControllerNotFoundException;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class EtapaService {

    @Autowired
    private EtapaRepository etapaRepository;

    public EtapaDTO save(EtapaRequestDTO etapaRequestDTO) {
        Etapa etapa = toEntity(etapaRequestDTO);
        etapa = etapaRepository.save(etapa);
        return toEtapaResponseDTO(etapa);
    }

    public EtapaDTO update(Long id, EtapaDTO etapaDTO) {
        try {
            Etapa etapa = etapaRepository.getReferenceById(id);
            etapa.setDescricao(etapaDTO.descricao());
            etapa = etapaRepository.save(etapa);

            return toEtapaResponseDTO(etapa);
        } catch (EntityNotFoundException e) {
            throw new ControllerNotFoundException("Etapa não localizada");
        }
    }

    public void delete(Long id) {
        try {
            etapaRepository.deleteById(id);
        } catch (EntityNotFoundException e) {
            throw new ControllerNotFoundException("Etapa não localizada");
        }
    }

    public Etapa findById(Long id) {
        try {
            return etapaRepository.findById(id).orElseThrow(() -> new ControllerNotFoundException("Etapa não localizada"));
        } catch (EntityNotFoundException e) {
            throw new ControllerNotFoundException("Etapa não localizada");
        }
    }


    public Collection<Etapa> findAll() {
        try {
            return etapaRepository.findAll();
        } catch (EntityNotFoundException e) {
            throw new ControllerNotFoundException("Não existem etapas cadastradas");
        }
    }

    private Etapa toEntity(EtapaRequestDTO etapaRequestDTO) {
        return new Etapa(etapaRequestDTO.descricao());
    }

    private EtapaDTO toEtapaResponseDTO(Etapa etapa) {
        return new EtapaDTO(etapa.getId(),
                etapa.getDescricao());
    }
}
