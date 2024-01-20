package com.fase2.techchallenge.fiap.estacionamento.gestaoestacionamento.controller;

import com.fase2.techchallenge.fiap.estacionamento.gestaoestacionamento.dto.TarifaDTO;
import com.fase2.techchallenge.fiap.estacionamento.gestaoestacionamento.feign.TarifaClient;
import com.fase2.techchallenge.fiap.estacionamento.gestaoestacionamento.retrofit.ServiceGenerator;
//import com.fase2.techchallenge.fiap.estacionamento.gestaoestacionamento.retrofit.TarifaService;
import com.fase2.techchallenge.fiap.estacionamento.gestaoestacionamento.service.TarifaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import retrofit2.Call;
import retrofit2.Response;

import java.util.List;


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