package com.tech.challenge.inscricao.gestaousuario.service;

import com.tech.challenge.inscricao.gestaousuario.dto.CandidatoDTO;
import com.tech.challenge.inscricao.gestaousuario.dto.DadosPessoaisDTO;
import com.tech.challenge.inscricao.gestaousuario.dto.EnderecoDTO;
import com.tech.challenge.inscricao.gestaousuario.dto.FiliacaoDTO;
import com.tech.challenge.inscricao.gestaousuario.entity.Candidato;
import com.tech.challenge.inscricao.gestaousuario.entity.DadosPessoais;
import com.tech.challenge.inscricao.gestaousuario.repository.CandidatoRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CandidatoService {

    @Autowired
    private CandidatoRepository candidatoRepository;

    public CandidatoDTO save (CandidatoDTO candidatoDTO){
        Candidato candidato = toEntity(candidatoDTO);
        candidato = candidatoRepository.save(candidato);
        return toCandidatoDTO(candidato);
    }

    public CandidatoDTO update(Long id, CandidatoDTO candidatoDTO){
        try{
            Candidato candidato = candidatoRepository.getReferenceById(id);
            candidato.setDadosPessoais(new DadosPessoais(candidatoDTO.dadosPessoaisDTO()));
            candidato = candidatoRepository.save(candidato);

            return toCandidatoDTO(candidato);
        }
        catch (EntityNotFoundException e){
            //throw new Exception(); - Criar uma excecao especifica
            return null;
        }
    }

    public void delete(Long id){

        candidatoRepository.deleteById(id);
    }

    private Candidato toEntity(CandidatoDTO candidatoDTO){
        return new Candidato(new DadosPessoais(candidatoDTO.dadosPessoaisDTO()));
    }

    private CandidatoDTO toCandidatoDTO(Candidato candidato){
        return new CandidatoDTO(new DadosPessoaisDTO(
                candidato.getDadosPessoais().getEstadoCivil(),
                candidato.getDadosPessoais().getGenero(),
                candidato.getDadosPessoais().getDataNascimento(),
                candidato.getDadosPessoais().getCelular(),
                candidato.getDadosPessoais().getNaturalidade(),
                new FiliacaoDTO(candidato.getDadosPessoais().getFiliacao().getNomeMae(),
                        candidato.getDadosPessoais().getFiliacao().getNomePai()),
                new EnderecoDTO(candidato.getDadosPessoais().getEndereco().getCep(),
                                candidato.getDadosPessoais().getEndereco().getNumero(),
                                candidato.getDadosPessoais().getEndereco().getComplemento()
                )
        ));
    }
}
