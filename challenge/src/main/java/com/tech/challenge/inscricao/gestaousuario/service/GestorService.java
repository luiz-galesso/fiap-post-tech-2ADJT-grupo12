package com.tech.challenge.inscricao.gestaousuario.service;

import com.tech.challenge.inscricao.gestaousuario.controller.exception.ControllerNotFoundException;
import com.tech.challenge.inscricao.gestaousuario.dto.*;
import com.tech.challenge.inscricao.gestaousuario.entity.Endereco;
import com.tech.challenge.inscricao.gestaousuario.entity.Gestor;
import com.tech.challenge.inscricao.gestaousuario.entity.DadosPessoais;
import com.tech.challenge.inscricao.gestaousuario.entity.Usuario;
import com.tech.challenge.inscricao.gestaousuario.repository.GestorRepository;
import com.tech.challenge.util.StringUtils;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GestorService {

    @Autowired
    private GestorRepository gestorRepository;

    public GestorResponseDTO save(GestorRequestDTO gestorDTO) {
        Gestor gestor = toEntity(gestorDTO);
        gestor = gestorRepository.save(gestor);
        return toGestorDTO(gestor);
    }

    public GestorResponseDTO update(Long id, GestorRequestDTO gestorDTO) {
        try {
            Gestor gestor = gestorRepository.getReferenceById(id);
            gestor.setEndereco(new Endereco(gestorDTO.endereco()));
            gestor.setUsuario(new Usuario(gestorDTO.usuario()));
            gestor = gestorRepository.save(gestor);

            return toGestorDTO(gestor);
        } catch (EntityNotFoundException e) {
            throw new ControllerNotFoundException("Gestor não localizado");
        }
    }


    public void delete(Long id) {
        try {
            gestorRepository.deleteById(id);
        } catch (EntityNotFoundException e) {
            throw new ControllerNotFoundException("Gestor não localizado");
        }
    }

    public Gestor findById(Long id) {
        try {
            return gestorRepository.findById(id).orElseThrow(() -> new ControllerNotFoundException("Gestor não localizado"));
        } catch (EntityNotFoundException e) {
            throw new ControllerNotFoundException("Gestor não localizado");
        }
    }

   /* REMOÇÃO DE MÉTODO, NÃO HÁ SENTIDO SUA EXISTÊNCIA
    public Collection<Gestor> findAll() {
        try {
            return gestorRepository.findAll();
        } catch (EntityNotFoundException e) {
            throw new ControllerNotFoundException("Não existem gestors");
        }
    }*/

    private Gestor toEntity(GestorRequestDTO gestorRequestDTO) {
        return new Gestor(new Endereco(gestorRequestDTO.endereco()),
                new Usuario(gestorRequestDTO.usuario()));
    }

    private GestorResponseDTO toGestorDTO(Gestor gestor) {
        return new GestorResponseDTO(
                gestor.getId(),
                new EnderecoDTO(gestor.getEndereco().getCep(),
                        gestor.getEndereco().getNumero(),
                        gestor.getEndereco().getComplemento()),
                new UsuarioDTO(gestor.getUsuario().getNomeUsuario()));
    }
}
