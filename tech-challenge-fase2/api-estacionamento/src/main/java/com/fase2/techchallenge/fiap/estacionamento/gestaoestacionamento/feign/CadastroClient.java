package com.fase2.techchallenge.fiap.estacionamento.gestaoestacionamento.feign;

import com.fase2.techchallenge.fiap.estacionamento.gestaoestacionamento.dto.MeioPagamentoCondutorDTO;
import com.fase2.techchallenge.fiap.estacionamento.gestaoestacionamento.dto.VeiculoDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@FeignClient(name = "cadastro", url = "${feign.cadastro.url}")
public interface CadastroClient {
    @RequestMapping(method = RequestMethod.GET, value = "/meioPagamentoCondutor/{id}")
    MeioPagamentoCondutorDTO getMeioPagamentoCondutor(@PathVariable(value = "id") Long id);
    @RequestMapping(method = RequestMethod.GET, value = "/veiculos/{id}")
    VeiculoDTO getVeiculo(@PathVariable(value = "id") Long id);

}