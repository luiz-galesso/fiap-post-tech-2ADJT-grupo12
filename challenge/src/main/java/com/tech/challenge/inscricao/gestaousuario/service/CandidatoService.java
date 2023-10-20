package com.tech.challenge.inscricao.gestaousuario.service;

import com.tech.challenge.inscricao.gestaousuario.controller.exception.ControllerNotFoundException;
import com.tech.challenge.inscricao.gestaousuario.dto.*;
import com.tech.challenge.inscricao.gestaousuario.entity.Candidato;
import com.tech.challenge.inscricao.gestaousuario.entity.DadosPessoais;
import com.tech.challenge.inscricao.gestaousuario.entity.Usuario;
import com.tech.challenge.inscricao.gestaousuario.repository.CandidatoRepository;
import com.tech.challenge.util.StringUtils;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CandidatoService {

    @Autowired
    private CandidatoRepository candidatoRepository;

    public CandidatoDTO save(CandidatoDTO candidatoDTO) {
        Candidato candidato = toEntity(candidatoDTO);
        candidato = candidatoRepository.save(candidato);
        return toCandidatoDTO(candidato);
    }

    public CandidatoDTO update(String id, AtualizarCandidatoDTO candidatoDTO) {
        try {
            Candidato candidato = candidatoRepository.getReferenceById(id);
            candidato.setDadosPessoais(new DadosPessoais(candidatoDTO.dadosPessoais()));
            candidato = candidatoRepository.save(candidato);

            return toCandidatoDTO(candidato);
        } catch (EntityNotFoundException e) {
            throw new ControllerNotFoundException("Candidato não localizado");
        }
    }

    public void delete(String id) {
        try {
            candidatoRepository.deleteById(id);
        } catch (EntityNotFoundException e) {
            throw new ControllerNotFoundException("Candidato não localizado");
        }
    }

    public Candidato findById(String id) {
        try {
            return candidatoRepository.findById(id).orElseThrow(() -> new ControllerNotFoundException("Candidato não localizado"));
        } catch (EntityNotFoundException e) {
            throw new ControllerNotFoundException("Candidato não localizado");
        }
    }

   /* REMOÇÃO DE MÉTODO, NÃO HÁ SENTIDO SUA EXISTÊNCIA
    public Collection<Candidato> findAll() {
        try {
            return candidatoRepository.findAll();
        } catch (EntityNotFoundException e) {
            throw new ControllerNotFoundException("Não existem candidatos");
        }
    }*/

    private Candidato toEntity(CandidatoDTO candidatoDTO) {
        return new Candidato(candidatoDTO.nomeCompleto(),
                StringUtils.removeMascara(candidatoDTO.cpf()),
                new DadosPessoais(candidatoDTO.dadosPessoais()),
                new Usuario(candidatoDTO.usuario()));
    }

    private CandidatoDTO toCandidatoDTO(Candidato candidato) {
        return new CandidatoDTO(candidato.getNome(),
                candidato.getCpf().toString(),
                new DadosPessoaisDTO(candidato.getDadosPessoais().getEstadoCivil(),
                        candidato.getDadosPessoais().getGenero(),
                        candidato.getDadosPessoais().getDataNascimento(),
                        candidato.getDadosPessoais().getCelular(),
                        candidato.getDadosPessoais().getNaturalidade(),
                        new FiliacaoDTO(candidato.getDadosPessoais().getFiliacao().getNomeMae(),
                                candidato.getDadosPessoais().getFiliacao().getNomePai()),
                        new EnderecoDTO(candidato.getDadosPessoais().getEndereco().getCep(),
                                candidato.getDadosPessoais().getEndereco().getNumero(),
                                candidato.getDadosPessoais().getEndereco().getComplemento())),
                new UsuarioDTO(candidato.getUsuario().getNomeUsuario()));
    }
}
