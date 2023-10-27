package com.tech.challenge.inscricao.gestaocarreira.service;

import com.tech.challenge.inscricao.gestaocarreira.controller.exception.UniqueException;
import com.tech.challenge.inscricao.gestaocarreira.dto.CarreiraRequestDTO;
import com.tech.challenge.inscricao.gestaocarreira.entity.Carreira;
import com.tech.challenge.inscricao.gestaocarreira.repository.CarreiraRepository;
import com.tech.challenge.inscricao.gestaousuario.controller.exception.ControllerNotFoundException;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class CarreiraService {

    @Autowired
    private CarreiraRepository carreiraRepository;

    public CarreiraRequestDTO save(CarreiraRequestDTO carreiraRequestDTO) {
        Carreira carreira = new Carreira();
        carreira = carreiraRepository.findByDescricao(carreiraRequestDTO.descricao());
        if (carreira != null) {
            throw new UniqueException("Essa carreira já existe");
        }
        carreira = toEntity(carreiraRequestDTO);
        carreira = carreiraRepository.save(carreira);
        return toCarreiraRequestDTO(carreira);
    }

    public CarreiraRequestDTO update(Long id, CarreiraRequestDTO carreiraRequestDTO) {
        try {
            Carreira carreira = carreiraRepository.getReferenceById(id);
            carreira.setDescricao(carreiraRequestDTO.descricao());
            carreira = carreiraRepository.save(carreira);

            return toCarreiraRequestDTO(carreira);
        } catch (EntityNotFoundException e) {
            throw new ControllerNotFoundException("Carreira não localizado");
        }
    }


    public void delete(Long id) {
        try {
            carreiraRepository.deleteById(id);
        } catch (EntityNotFoundException e) {
            throw new ControllerNotFoundException("Carreira não localizado");
        }
    }

    public Carreira findById(Long id) {
        try {
            return carreiraRepository.findById(id).orElseThrow(() -> new ControllerNotFoundException(
                   /* this.getClass().toString()
                    .replace(getClass().getPackageName(),"")
                    .replace(".","")
                    .replace("class","")
                    .replace("Service","")
                    + " não localizado")*/
                    "Carreira não localizado"
            ));


        } catch (EntityNotFoundException e) {
            throw new ControllerNotFoundException("Carreira não localizado");
        }
    }

    public Collection<Carreira> findAll() {
        try {
            return carreiraRepository.findAll();
        } catch (EntityNotFoundException e) {
            throw new ControllerNotFoundException("Não existem perfis");
        }
    }

    private Carreira toEntity(CarreiraRequestDTO carreiraRequestDTO) {
        Carreira carreira = new Carreira();
        carreira.setDescricao(carreiraRequestDTO.descricao());
        return carreira;
    }

    private CarreiraRequestDTO toCarreiraRequestDTO(Carreira carreira) {
        return new CarreiraRequestDTO(
                carreira.getId(),
                carreira.getDescricao()
        );
    }


}
