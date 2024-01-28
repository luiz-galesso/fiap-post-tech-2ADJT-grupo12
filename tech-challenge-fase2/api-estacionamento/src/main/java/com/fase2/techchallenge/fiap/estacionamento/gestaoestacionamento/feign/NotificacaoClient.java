package com.fase2.techchallenge.fiap.estacionamento.gestaoestacionamento.feign;

import org.springframework.cloud.openfeign.FeignClient;
import com.fase2.techchallenge.fiap.estacionamento.gestaoestacionamento.dto.NotificacaoRequestDTO;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(name = "notificacao", url = "${feign.notificacao.url}")
public interface NotificacaoClient {
    @RequestMapping(method = RequestMethod.POST, value = "/notificacao")
    void postNotificacao(@RequestBody NotificacaoRequestDTO notificaoRequestDTO);

}