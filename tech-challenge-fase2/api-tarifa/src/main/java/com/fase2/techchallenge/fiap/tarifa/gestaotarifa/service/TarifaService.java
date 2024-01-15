package com.fase2.techchallenge.fiap.tarifa.gestaotarifa.service;

import com.fase2.techchallenge.fiap.tarifa.gestaotarifa.controller.exception.ControllerNotFoundException;
import com.fase2.techchallenge.fiap.tarifa.gestaotarifa.controller.exception.EntityFoundException;
import com.fase2.techchallenge.fiap.tarifa.gestaotarifa.dto.TarifaDTO;
import com.fase2.techchallenge.fiap.tarifa.gestaotarifa.entity.Tarifa;
import com.fase2.techchallenge.fiap.tarifa.gestaotarifa.repository.TarifaRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TarifaService {

    @Autowired
    private TarifaRepository tarifaRepository;

    public TarifaDTO update(String id, TarifaDTO tarifaDTO) {
        try {
            Tarifa tarifa = tarifaRepository.getReferenceById(id);
            tarifa.setDescricao(tarifaDTO.descricao());
            tarifa.setValor(tarifaDTO.valor());
            tarifa = tarifaRepository.save(tarifa);

            return toTarifaDTO(tarifa);
        } catch (EntityNotFoundException e) {
            throw new ControllerNotFoundException("Tarifa não localizada");
        }
    }

    public TarifaDTO findById(String id) {
        try {
            return toTarifaDTO(tarifaRepository.findById(id).orElseThrow(() -> new ControllerNotFoundException("Tarifa não localizada")));
        } catch (EntityNotFoundException e) {
            /*TODO VERIFICAR SE ESTOURA ESSA EXCEPTION */
            throw new ControllerNotFoundException("Tarifa não localizada");
        }
    }

    public List<Tarifa> findAll() {
        try {
            return tarifaRepository.findAll();
        } catch (EntityNotFoundException e) {
            throw new ControllerNotFoundException("Não existem tarifas cadastradas");
        }
    }

    private Tarifa toEntity(TarifaDTO tarifaDTO) {
        return new Tarifa(
                tarifaDTO.id(),
                tarifaDTO.descricao(),
                tarifaDTO.valor()
        );
    }

    private TarifaDTO toTarifaDTO(Tarifa tarifa) {
        return new TarifaDTO(
                tarifa.getId(),
                tarifa.getDescricao(),
                tarifa.getValor()
        );
    }
}
