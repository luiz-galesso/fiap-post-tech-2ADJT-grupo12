package com.tech.challenge.inscricao.gestaocandidatura.service;

import com.tech.challenge.inscricao.gestaocandidatura.entity.Candidatura;
import com.tech.challenge.inscricao.gestaocandidatura.repository.CandidaturaRepository;
import com.tech.challenge.inscricao.gestaousuario.service.UsuarioService;
import com.tech.challenge.inscricao.gestaousuario.entity.Usuario;
import com.tech.challenge.inscricao.gestaovaga.service.VagaService;
import org.springframework.beans.factory.annotation.Autowired;
import com.tech.challenge.inscricao.gestaoperfil.service.PerfilService;

import com.tech.challenge.inscricao.gestaocandidatura.dto.CandidaturaRequestDTO;
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


   /* public CandidaturaRequestDTO save (CandidaturaRequestDTO candidaturaRequestDTO){
        Usuario usuario = usuarioService.findById(candidaturaRequestDTO.cpfCandidato());
        perfilService.autorizaPerfil(usuario, "CANDIDATO");
         //Precisa do service da vaga service da vaga ainda não foi mergeado
        vagaService.validarVagaExpirada(candidaturaRequestDTO.idVaga());
        Candidatura candidatura = toEntity(candidaturaRequestDTO);
        candidaturaRepository.save(candidatura);
       return toCandidaturaRequestDTO(candidatura);
    }*/


    public void aprova(Usuario candidato, Vaga vaga){
        //fazer um find na tabela de candidatura por vaga e candidato e somar + 1
    }
    public void reprova(Usuario candidato, Vaga vaga){
        // fazer um find na tabela de candidatura por vaga e candidato e zerar a posição do candidato
    }


  /*  private Candidatura toEntity(CandidaturaRequestDTO candidaturaRequestDTO) {
        return new Candidatura(
                new Vaga(candidaturaRequestDTO.idVaga()),
                new Usuario(candidaturaRequestDTO.cpfCandidato())
        );
    }
*/

    private CandidaturaRequestDTO toCandidaturaRequestDTO(Candidatura candidatura) {
        return new CandidaturaRequestDTO(
                candidatura.getUsuario().getCpf(),
                candidatura.getVaga().getId()
        );
    }
}
