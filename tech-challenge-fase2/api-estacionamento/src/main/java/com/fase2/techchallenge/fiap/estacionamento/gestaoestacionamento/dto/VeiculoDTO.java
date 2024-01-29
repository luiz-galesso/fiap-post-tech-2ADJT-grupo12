package com.fase2.techchallenge.fiap.estacionamento.gestaoestacionamento.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class VeiculoDTO {

        Long id;
        String placa;
        String nome;
        String emailCondutor;

}
