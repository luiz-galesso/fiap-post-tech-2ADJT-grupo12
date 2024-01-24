package com.fase2.techchallenge.fiap.gestaonotificacao.feign;

import com.fase2.techchallenge.fiap.gestaonotificacao.dto.VeiculoDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "api-cadastro", url = "${feign.cadastro.url}")
public interface VeiculoClient {

    @GetMapping("/veiculos/{id}")
    ResponseEntity<VeiculoDTO> getVeiculoPorId(@PathVariable("id") Integer id);

}
