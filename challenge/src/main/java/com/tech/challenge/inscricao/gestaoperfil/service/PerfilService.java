package com.tech.challenge.inscricao.gestaoperfil.service;

import com.tech.challenge.inscricao.gestaoperfil.controller.exception.AuthenticationException;
import com.tech.challenge.inscricao.gestaoperfil.dto.PerfilRequestDTO;
import com.tech.challenge.inscricao.gestaoperfil.entity.Perfil;
import com.tech.challenge.inscricao.gestaoperfil.repository.PerfilRepository;
import com.tech.challenge.inscricao.gestaousuario.controller.exception.ControllerNotFoundException;

import com.tech.challenge.inscricao.gestaousuario.dto.UsuarioDTO;
import com.tech.challenge.inscricao.gestaousuario.entity.Usuario;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class PerfilService {

    @Autowired
    private PerfilRepository perfilRepository;

    public PerfilRequestDTO save(PerfilRequestDTO perfilRequestDTO) {
        Perfil perfil = toEntity(perfilRequestDTO);
        perfil = perfilRepository.save(perfil);
        return toPerfilRequestDTO(perfil);
    }

    public PerfilRequestDTO update(Long id, PerfilRequestDTO perfilRequestDTO) {
        try {
            Perfil perfil = perfilRepository.getReferenceById(id);
            perfil.setDescricao(perfilRequestDTO.descricao());
            perfil = perfilRepository.save(perfil);

            return toPerfilRequestDTO(perfil);
        } catch (EntityNotFoundException e) {
            throw new ControllerNotFoundException("Perfil não localizado");
        }
    }


    public void delete(Long id) {
        try {
            perfilRepository.deleteById(id);
        } catch (EntityNotFoundException e) {
            throw new ControllerNotFoundException("Perfil não localizado");
        }
    }

    public Perfil findById(Long id) {
        try {
            return perfilRepository.findById(id).orElseThrow(() -> new ControllerNotFoundException(
                   /* this.getClass().toString()
                    .replace(getClass().getPackageName(),"")
                    .replace(".","")
                    .replace("class","")
                    .replace("Service","")
                    + " não localizado")*/
                    "Perfil não localizado"
            ));


        } catch (EntityNotFoundException e) {
            throw new ControllerNotFoundException("Perfil não localizado");
        }
    }

    public Collection<Perfil> findAll() {
        try {
            return perfilRepository.findAll();
        } catch (EntityNotFoundException e) {
            throw new ControllerNotFoundException("Não existem perfis");
        }
    }

    private Perfil toEntity(PerfilRequestDTO perfilRequestDTO) {
        Perfil perfil = new Perfil();
        perfil.setDescricao(perfilRequestDTO.descricao());
        return perfil;
    }

    private PerfilRequestDTO toPerfilRequestDTO(Perfil perfil) {
        return new PerfilRequestDTO(
                perfil.getIdPerfil(),
               perfil.getDescricao()
        );
    }

    public boolean verificaPerfil(Usuario usuario, String perfil){

     Perfil perfilLocal = findById(usuario.getPerfil().getId());
        return perfilLocal.getDescricao().equals(perfil);

    }

    public void autorizaPerfil(Usuario usuario, String perfil){
        if(!verificaPerfil(usuario, perfil)){
            throw new AuthenticationException("Perfil não autorizado");
        }
    }

    public void verificaEntidade(Usuario usuario){
        if(findById(usuario.getPerfil().getId()) == null){
            throw new EntityNotFoundException();
        }
    }




}
