package com.tech.challenge.inscricao.gestaocandidatura.service;

import com.tech.challenge.inscricao.gestaocandidatura.controller.exception.EntityFoundException;
import com.tech.challenge.inscricao.gestaocandidatura.entity.Candidatura;
import com.tech.challenge.inscricao.gestaocandidatura.repository.CandidaturaRepository;
import com.tech.challenge.inscricao.gestaousuario.service.UsuarioService;
import com.tech.challenge.inscricao.gestaousuario.entity.Usuario;
import com.tech.challenge.inscricao.gestaovaga.service.VagaService;
import com.tech.challenge.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import com.tech.challenge.inscricao.gestaoperfil.service.PerfilService;

import com.tech.challenge.inscricao.gestaocandidatura.dto.CandidaturaRequestDTO;
import com.tech.challenge.inscricao.gestaovaga.entity.Vaga;
import org.springframework.stereotype.Service;

@Service
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
            String cpf = StringUtils.removeMascara(candidaturaRequestDTO.cpfCandidato());
            Usuario usuario = usuarioService.findById(cpf);
            perfilService.autorizaPerfil(usuario, "CANDIDATO");
            //Precisa do service da vaga service da vaga ainda não foi mergeado
            vagaService.validarVagaExpirada(candidaturaRequestDTO.idVaga());
            validaSeJaCadastrado(candidaturaRequestDTO.idVaga(), candidaturaRequestDTO.cpfCandidato());
            Candidatura candidatura = toEntity(candidaturaRequestDTO);
            candidaturaRepository.save(candidatura);
            return toCandidaturaRequestDTO(candidatura);
    }

    public void delete(String cpfCandidato, Long idVaga){
        //validar no cadastro da canidatura se vai cadastrar duas vezes a nesma candidatura
        Usuario usuario = usuarioService.findById(cpfCandidato);
        Vaga vaga = vagaService.findById(idVaga);
        Candidatura candidatura = findByVagaAndCandidato(idVaga, cpfCandidato);
        candidaturaRepository.deleteById(candidatura.getId());
    }

    public Candidatura findByVagaAndCandidato(Long idVaga, String cpf){
        return candidaturaRepository.findByVagaAndCandidato(idVaga, cpf);
    }

    public void validaSeJaCadastrado(Long idVaga, String cpf){
        Candidatura candidatura = findByVagaAndCandidato(idVaga, cpf);
        if(candidatura != null){
           throw new EntityFoundException("Já cadastrado!");
        }
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
}
