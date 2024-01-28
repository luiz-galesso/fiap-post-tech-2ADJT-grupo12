package com.fase2.techchallenge.fiap.estacionamento.gestaoestacionamento.feign;

import com.fase2.techchallenge.fiap.estacionamento.gestaoestacionamento.dto.TarifaDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(name = "tarifa", url = "${feign.tarifa.url}")
public interface TarifaClient {
    @RequestMapping(method = RequestMethod.GET, value = "/tarifa/{id}")
    TarifaDTO getTarifa(@PathVariable(value = "id") String id);

}