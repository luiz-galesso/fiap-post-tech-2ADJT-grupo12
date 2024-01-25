package com.fase2.techchallenge.fiap.cadastro.condutor.service;

import com.fase2.techchallenge.fiap.cadastro.exception.ControllerNotFoundException;
import com.fase2.techchallenge.fiap.cadastro.exception.EntityFoundException;
import com.fase2.techchallenge.fiap.cadastro.condutor.dto.CondutorDTO;
import com.fase2.techchallenge.fiap.cadastro.condutor.dto.DadosPessoaisDTO;
import com.fase2.techchallenge.fiap.cadastro.condutor.dto.EnderecoDTO;
import com.fase2.techchallenge.fiap.cadastro.condutor.entity.Condutor;
import com.fase2.techchallenge.fiap.cadastro.condutor.entity.DadosPessoais;
import com.fase2.techchallenge.fiap.cadastro.condutor.entity.Endereco;
import com.fase2.techchallenge.fiap.cadastro.condutor.repository.CondutorRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CondutorService {

    @Autowired
    private CondutorRepository condutorRepository;

    public CondutorDTO save(CondutorDTO condutorDTO) {
        Condutor condutor = toEntity(condutorDTO);
        condutorExistente(condutor);
        condutor = condutorRepository.save(condutor);
        return toCondutorDTO(condutor);
    }

    public CondutorDTO update(String email, CondutorDTO condutorDTO) {
        try {
            Condutor condutor = condutorRepository.getReferenceById(email);
            condutor.setDadosPessoais(new DadosPessoais(condutorDTO.dadosPessoais()));
            condutor.setLogradouro(new Endereco(condutorDTO.endereco()));
            condutor.setAtivacaoAutomatica(condutor.isAtivacaoAutomatica());
            condutor = condutorRepository.save(condutor);

            return toCondutorDTO(condutor);
        } catch (EntityNotFoundException e) {
            throw new ControllerNotFoundException("Condutor não localizado");
        }
    }

    public void delete(String email) {
        try {
            condutorRepository.deleteById(email);
        } catch (EntityNotFoundException e) {
            throw new ControllerNotFoundException("Condutor não localizado");
        }
    }

    public CondutorDTO findById(String id) {
        try {
            return toCondutorDTO(condutorRepository.findById(id).orElseThrow(() -> new ControllerNotFoundException("Condutor não localizado")));
        } catch (EntityNotFoundException e) {
            throw new ControllerNotFoundException("Condutor não localizado");
        }
    }

    public List<Condutor> findAll() {
        try {
            return condutorRepository.findAll();
        } catch (EntityNotFoundException e) {
            throw new ControllerNotFoundException("Não existem condutores cadastrados");
        }
    }

    public void condutorExistente(Condutor condutor){
        Optional<Condutor> condutorLocal = condutorRepository.findById(condutor.getEmail());
        if(condutorLocal.isPresent()){
            throw new EntityFoundException("Condutor já cadastrado!");
        }
    }

    private Condutor toEntity(CondutorDTO condutorDTO) {
        return new Condutor(
                condutorDTO.email(),
                new DadosPessoais(
                        condutorDTO.dadosPessoais().nome(),
                        condutorDTO.dadosPessoais().cpf(),
                        condutorDTO.dadosPessoais().nrCelular()
                ),
                 new Endereco(
                         condutorDTO.endereco().descricao(),
                         condutorDTO.endereco().numero(),
                         condutorDTO.endereco().cidade(),
                         condutorDTO.endereco().estado(),
                         condutorDTO.endereco().CEP(),
                         condutorDTO.endereco().complemento()
                 ),
                condutorDTO.ativacaoAutomatica()
        );
    }

    private CondutorDTO toCondutorDTO(Condutor condutor) {
        return new CondutorDTO(
                condutor.getEmail(),
                new DadosPessoaisDTO(
                        condutor.getDadosPessoais().getNome(),
                        condutor.getDadosPessoais().getCpf(),
                        condutor.getDadosPessoais().getNrCelular()
                ),
                new EnderecoDTO(
                        condutor.getLogradouro().getDescricao(),
                        condutor.getLogradouro().getNumero(),
                        condutor.getLogradouro().getCidade(),
                        condutor.getLogradouro().getEstado(),
                        condutor.getLogradouro().getCEP(),
                        condutor.getLogradouro().getComplemento()
                ),
                condutor.isAtivacaoAutomatica()
        );
    }
}
