package com.fase2.techchallenge.fiap.estacionamento.gestaoestacionamento.feign;

import com.fase2.techchallenge.fiap.estacionamento.gestaoestacionamento.dto.PagamentoRequestDTO;
import com.fase2.techchallenge.fiap.estacionamento.gestaoestacionamento.dto.TarifaDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(name = "pagamento", url = "${feign.pagamento.url}")
public interface PagamentoClient {
    @RequestMapping(method = RequestMethod.POST, value = "/pagamento")
    TarifaDTO postPagamento(@RequestBody PagamentoRequestDTO pagamentoRequestDTO);

}