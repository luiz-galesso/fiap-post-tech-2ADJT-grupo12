package com.tech.challenge.processoseletivo.gestaoetapa.service;

import com.tech.challenge.acesso.gestaousuario.controller.exception.ControllerNotFoundException;
import com.tech.challenge.acesso.gestaousuario.entity.Usuario;
import com.tech.challenge.inscricao.gestaocandidatura.controller.exception.EntityFoundException;
import com.tech.challenge.inscricao.gestaovaga.entity.Vaga;
import com.tech.challenge.inscricao.gestaovaga.service.VagaService;
import com.tech.challenge.processoseletivo.gestaoetapa.dto.VagaEtapaDTO;
import com.tech.challenge.processoseletivo.gestaoetapa.entity.VagaEtapa;
import com.tech.challenge.processoseletivo.gestaoetapa.entity.VagaEtapaID;
import com.tech.challenge.processoseletivo.gestaoetapa.repository.VagaEtapaRepository;
import com.tech.challenge.processoseletivo.gestaoetapa.service.filtros.VagaEtapaFiltro;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class VagaEtapaService {

    private final VagaEtapaRepository vagaEtapaRepository;

    private final VagaService vagaService;
    private final EtapaService etapaService;

    public VagaEtapaService(VagaEtapaRepository vagaEtapaRepository, VagaService vagaService, EtapaService etapaService) {
        this.vagaService = vagaService;
        this.etapaService = etapaService;
        this.vagaEtapaRepository = vagaEtapaRepository;
    }

    public VagaEtapaDTO save(VagaEtapaDTO etapaVagaRequestDTO) {
        VagaEtapa vagaEtapa = toEntity(etapaVagaRequestDTO);
        Optional<VagaEtapa> vagaEtapaLocal = vagaEtapaRepository.findById(vagaEtapa.getEtapaVagaID());
        if(vagaEtapaLocal.isPresent()){
            throw new EntityFoundException("Vaga etapa já cadastrada!");
        }
        vagaEtapa = vagaEtapaRepository.save(vagaEtapa);
        return toEtapaVagaDTO(vagaEtapa);
    }

    public void delete(Long vagaId, Long etapaId, Integer ordem) {
        try {
            VagaEtapaID vagaEtapaID = new VagaEtapaID(vagaService.findById(vagaId), etapaService.findById(etapaId),ordem);
            vagaEtapaRepository.deleteById(vagaEtapaID);
        } catch (Exception e) {
            throw new ControllerNotFoundException("Erro ao deletar etapa vaga ordem.");
        }
    }

    public VagaEtapaDTO findById(Long vagaId, Long etapaId, Integer ordem) {
        try {
            VagaEtapaID vagaEtapaID = new VagaEtapaID(vagaService.findById(vagaId),etapaService.findById(etapaId), ordem);
            Optional<VagaEtapa> etapaVaga = vagaEtapaRepository.findById(vagaEtapaID);

            return toEtapaVagaDTO(etapaVaga.get());


        } catch (EntityNotFoundException e) {
            throw new ControllerNotFoundException("Etapa Vaga não localizada.");
        }
    }

    public List<VagaEtapa> findByFiltro(Long idVaga, Long idEtapa)
    {
        VagaEtapaFiltro filtro = new VagaEtapaFiltro(idVaga, idEtapa);
        return vagaEtapaRepository.findAll(filtro);
    }

    private VagaEtapaDTO toEtapaVagaDTO(VagaEtapa vagaEtapa) {
        return new VagaEtapaDTO(vagaEtapa.getEtapaVagaID().getEtapa().getId(),
                vagaEtapa.getEtapaVagaID().getVaga().getId(),
                vagaEtapa.getEtapaVagaID().getOrdem()
        );
    }

    private VagaEtapa toEntity(VagaEtapaDTO etapaVagaDTO) {
        VagaEtapaID vagaEtapaID = new VagaEtapaID(vagaService.findById(etapaVagaDTO.vaga()), etapaService.findById(etapaVagaDTO.etapa()),etapaVagaDTO.ordem());
        return new VagaEtapa(vagaEtapaID);
    }

}
