package com.fase2.techchallenge.fiap.estacionamento.gestaoestacionamento.service;

import com.fase2.techchallenge.fiap.estacionamento.gestaoestacionamento.dto.MeioPagamentoCondutorDTO;
import com.fase2.techchallenge.fiap.estacionamento.gestaoestacionamento.dto.TarifaDTO;
import com.fase2.techchallenge.fiap.estacionamento.gestaoestacionamento.dto.VeiculoDTO;
import com.fase2.techchallenge.fiap.estacionamento.gestaoestacionamento.feign.CadastroClient;
import com.fase2.techchallenge.fiap.estacionamento.gestaoestacionamento.feign.TarifaClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CadastroService {

    @Autowired
    private CadastroClient cadastroClient;

    public MeioPagamentoCondutorDTO getMeioPagamentoCondutor(Long id){
        MeioPagamentoCondutorDTO meioPagamentoCondutorDTO= cadastroClient.getMeioPagamentoCondutor(id);
        return meioPagamentoCondutorDTO;
    }

    public VeiculoDTO getVeiculo(Long id){
        return cadastroClient.getVeiculo(id);
    }
}
