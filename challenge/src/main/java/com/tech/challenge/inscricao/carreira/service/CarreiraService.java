package com.tech.challenge.inscricao.carreira.service;

import com.tech.challenge.inscricao.carreira.dto.CarreiraRequestDTO;
import com.tech.challenge.inscricao.carreira.entity.Carreira;
import com.tech.challenge.inscricao.carreira.repository.CarreiraRepository;
import com.tech.challenge.inscricao.gestaousuario.controller.exception.ControllerNotFoundException;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class CarreiraService {

    @Autowired
    private CarreiraRepository perfilRepository;

    public CarreiraRequestDTO save(CarreiraRequestDTO perfilRequestDTO) {
        Carreira perfil = toEntity(perfilRequestDTO);
        perfil = perfilRepository.save(perfil);
        return toPerfilRequestDTO(perfil);
    }

    public CarreiraRequestDTO update(Long id, CarreiraRequestDTO perfilRequestDTO) {
        try {
            Carreira perfil = perfilRepository.getReferenceById(id);
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

    public Carreira findById(Long id) {
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

    public Collection<Carreira> findAll() {
        try {
            return perfilRepository.findAll();
        } catch (EntityNotFoundException e) {
            throw new ControllerNotFoundException("Não existem perfis");
        }
    }

    private Carreira toEntity(CarreiraRequestDTO carreiraRequestDTO) {
        Carreira perfil = new Carreira();
        perfil.setDescricao(carreiraRequestDTO.descricao());
        return perfil;
    }

    private CarreiraRequestDTO toPerfilRequestDTO(Carreira perfil) {
        return new CarreiraRequestDTO(
                perfil.getId(),
               perfil.getDescricao()
        );
    }


}
