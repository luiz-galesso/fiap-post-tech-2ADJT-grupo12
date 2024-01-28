package com.fase2.techchallenge.fiap.pagamento.gestaorecibo.feign;

import com.fase2.techchallenge.fiap.pagamento.gestaorecibo.dto.EstacionamentoDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "api-estacionamento", url = "${feign.estacionamento.url}")
public interface EstacionamentoClient {
    @GetMapping("/estacionamento/{idEstacionamento}")
    EstacionamentoDTO getEstacionamento(@PathVariable String idEstacionamento);

}
