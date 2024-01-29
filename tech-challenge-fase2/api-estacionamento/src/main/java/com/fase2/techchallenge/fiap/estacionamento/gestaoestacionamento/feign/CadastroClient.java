package com.fase2.techchallenge.fiap.estacionamento.gestaoestacionamento.feign;

import com.fase2.techchallenge.fiap.estacionamento.gestaoestacionamento.dto.MeioPagamentoCondutorDTO;
import com.fase2.techchallenge.fiap.estacionamento.gestaoestacionamento.dto.TarifaDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(name = "cadastro", url = "${feign.cadastro.url}")
public interface CadastroClient {
    @RequestMapping(method = RequestMethod.GET, value = "/meioPagamentoCondutor/{id}")
    MeioPagamentoCondutorDTO getMeioPagamentoCondutor(@PathVariable(value = "id") Long id);

}