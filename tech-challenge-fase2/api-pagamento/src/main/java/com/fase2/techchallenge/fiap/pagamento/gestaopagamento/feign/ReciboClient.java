package com.fase2.techchallenge.fiap.pagamento.gestaopagamento.feign;

import com.fase2.techchallenge.fiap.pagamento.gestaopagamento.dto.RetornoSolicitacaoReciboDTO;
import com.fase2.techchallenge.fiap.pagamento.gestaopagamento.dto.SolicitacaReciboDTO;
import com.fase2.techchallenge.fiap.pagamento.gestaorecibo.dto.RetornoCancelamentoReciboDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "api-pagamento", url = "${feign.recibo.url}")
public interface ReciboClient {
    @PostMapping("/recibo")
    RetornoSolicitacaoReciboDTO solicitaRecibo(@RequestBody SolicitacaReciboDTO solicitacaReciboDTO);

    @PutMapping("/recibo/cancelar/{idPagamento}")
    RetornoCancelamentoReciboDTO cancelarRecibo(@PathVariable Long idPagamento);

}
