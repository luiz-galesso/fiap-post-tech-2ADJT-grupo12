package com.fase2.techchallenge.fiap.cadastro.meiopagamentocondutor.feign;

import com.fase2.techchallenge.fiap.cadastro.meiopagamentocondutor.dto.MeioPagamentoCondutorResponseDTO;
import com.fase2.techchallenge.fiap.cadastro.meiopagamentocondutor.dto.MeioPagamentoDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "api-pagamento", url = "${feign.pagamento.url}")
public interface MeioPagamentoClient {
    @GetMapping(value = "/meiospagamento/{id}", produces = "application/json")
    public ResponseEntity<MeioPagamentoDTO> findByIdMeioPagamento(@PathVariable("id") String id);
}
