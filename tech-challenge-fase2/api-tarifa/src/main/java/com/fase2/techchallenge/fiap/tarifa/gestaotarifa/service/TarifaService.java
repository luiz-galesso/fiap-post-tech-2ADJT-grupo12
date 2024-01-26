package com.fase2.techchallenge.fiap.tarifa.gestaotarifa.service;

import com.fase2.techchallenge.fiap.tarifa.exception.ControllerNotFoundException;
import com.fase2.techchallenge.fiap.tarifa.gestaotarifa.dto.TarifaDTO;
import com.fase2.techchallenge.fiap.tarifa.gestaotarifa.dto.TarifaUpdateDTO;
import com.fase2.techchallenge.fiap.tarifa.gestaotarifa.entity.Tarifa;
import com.fase2.techchallenge.fiap.tarifa.gestaotarifa.repository.TarifaRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TarifaService {

    @Autowired
    private TarifaRepository tarifaRepository;

    public Tarifa update(String id, TarifaUpdateDTO tarifaUpdateDTO) {
        try {
            Tarifa tarifa = tarifaRepository.getReferenceById(id);
            tarifa.setDescricao(tarifaUpdateDTO.descricao());
            tarifa.setValor(tarifaUpdateDTO.valor());
            tarifa = tarifaRepository.save(tarifa);

            return tarifa;
        } catch (EntityNotFoundException e) {
            throw new ControllerNotFoundException("Tarifa não localizada");
        }
    }

    public Tarifa findById(String id) {
        try {
            return tarifaRepository.findById(id).orElseThrow(() -> new ControllerNotFoundException("Tarifa não localizada"));
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

}
