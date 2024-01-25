package com.fase2.techchallenge.fiap.pagamento.gestaorecibo.feign;

import com.fase2.techchallenge.fiap.pagamento.gestaorecibo.dto.CondutorDTO;
import com.fase2.techchallenge.fiap.pagamento.gestaorecibo.dto.VeiculoDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "api-cadastro", url = "${feign.cadastro.url}")
public interface CadastroClient {
    @GetMapping("/condutor/{idCondutor}")
    CondutorDTO getCondutor(@PathVariable String idCondutor);

    @GetMapping("/veiculos/{idVeiculo}")
    VeiculoDTO getVeiculo(@PathVariable Long idVeiculo);
}
