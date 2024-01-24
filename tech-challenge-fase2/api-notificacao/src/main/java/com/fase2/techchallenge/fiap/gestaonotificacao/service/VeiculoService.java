package com.fase2.techchallenge.fiap.gestaonotificacao.service;

import com.fase2.techchallenge.fiap.gestaonotificacao.controller.exception.ControllerNotFoundException;
import com.fase2.techchallenge.fiap.gestaonotificacao.dto.VeiculoDTO;
import com.fase2.techchallenge.fiap.gestaonotificacao.feign.VeiculoClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class VeiculoService {

    @Autowired
    private VeiculoClient veiculoClient;

    public VeiculoDTO getVeiculoPorId(Integer id) {
        try {
            VeiculoDTO veiculoDTO = veiculoClient.getVeiculoPorId(id).getBody();
            return veiculoDTO;
        } catch (RuntimeException e) {
            throw new ControllerNotFoundException("Veiculo não cadastrado");
        }
    }

}
