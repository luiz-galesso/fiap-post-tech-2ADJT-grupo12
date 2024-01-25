package com.fase2.techchallenge.fiap.estacionamento.gestaoestacionamento.controller;

import com.fase2.techchallenge.fiap.estacionamento.gestaoestacionamento.dto.TarifaDTO;
import com.fase2.techchallenge.fiap.estacionamento.gestaoestacionamento.service.TarifaService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;



@RestController
@RequestMapping("/parquimetro")
@Tag(name = "Meios de Pagamento", description="Serviços para manipular meios de pagamento")
public class EstacionamentoController {

    @Autowired
    TarifaService tarifaService;

    @PostMapping(path="/inicializacao")
    public TarifaDTO iniciaParquimetro(){
        return tarifaService.getTarifa();
    }

    @GetMapping
    public TarifaDTO update() {
        return tarifaService.getTarifa();




       /*TarifaService tarifaService = ServiceGenerator.createService(TarifaService.class);
        Call<Object> callSync = tarifaService.getTarifa();

        try {
            Response<Object> response = callSync.execute();
            Object tarifa = response.body();
            System.out.println(tarifa.toString());
        } catch (Exception ex) {
            System.out.println(ex.toString());
        }*/

    }

}