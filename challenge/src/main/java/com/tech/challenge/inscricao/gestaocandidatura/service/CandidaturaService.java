package com.tech.challenge.inscricao.gestaocandidatura.service;

import com.tech.challenge.inscricao.gestaocandidatura.entity.Candidatura;
import com.tech.challenge.inscricao.gestaocandidatura.repository.CandidaturaRepository;
import com.tech.challenge.inscricao.gestaousuario.service.UsuarioService;
import com.tech.challenge.inscricao.gestaousuario.entity.Usuario;
import com.tech.challenge.inscricao.gestaovaga.service.VagaService;
import org.springframework.beans.factory.annotation.Autowired;
import com.tech.challenge.inscricao.gestaoperfil.service.PerfilService;

import com.tech.challenge.inscricao.gestaocandidatura.dto.CandidaturaRequestDTO;
import com.tech.challenge.inscricao.gestaoperfil.controller.exception.AuthenticationException;
import com.tech.challenge.inscricao.gestaovaga.entity.Vaga;

public class CandidaturaService {

    @Autowired
    CandidaturaRepository candidaturaRepository;

    @Autowired
    UsuarioService usuarioService;

    @Autowired
    PerfilService perfilService;

    @Autowired
    VagaService vagaService;

    public CandidaturaRequestDTO save (CandidaturaRequestDTO candidaturaRequestDTO){
        Usuario usuario = usuarioService.findById(candidaturaRequestDTO.cpfCandidato());
        perfilService.autorizaPerfil(usuario, "CANDIDATO");
        vagaService.validarVagaExpirada(candidaturaRequestDTO.idVaga());
        Candidatura candidatura = toEntity(candidaturaRequestDTO);
        candidaturaRepository.save(candidatura);
       return toCandidaturaRequestDTO(candidatura);
    }


    private Candidatura toEntity(CandidaturaRequestDTO candidaturaRequestDTO) {
        return new Candidatura(
                new Vaga(candidaturaRequestDTO.idVaga()),
                new Usuario(candidaturaRequestDTO.cpfCandidato())
        );
    }


    private CandidaturaRequestDTO toCandidaturaRequestDTO(Candidatura candidatura) {
        return new CandidaturaRequestDTO(
                candidatura.getUsuario().getCpf(),
                candidatura.getVaga().getId()
        );
    }



    //    private Candidatura toEntity(CandidaturaRequestDTO candidaturaRequestDTO) {
//        return new Candidatura(candidaturaRequestDTO.cpfCandidato(),
//                candidaturaRequestDTO.idVaga());
//    }
//
//    private CandidaturaRequestDTO toCandidaturaRequestDTO(Candidatura candidatura) {
//        return new CandidaturaRequestDTO(
//                candidatura.getId(),
//                candidatura.getDescricao()
//        );
//    }
}
