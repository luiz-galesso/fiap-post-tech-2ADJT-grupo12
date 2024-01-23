package com.fase2.techchallenge.fiap.pagamento.gestaorecibo.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "api-cadastro", url = "${feign.cadastro.url}")
public interface CadastroClient {
    @GetMapping("/condutor/{idCondutor}")
    Object getCondutor(@PathVariable String idCondutor);

    @GetMapping("/veiculo/{idVeiculo}")
    Object getVeiculo(@PathVariable Long idVeiculo);
}
