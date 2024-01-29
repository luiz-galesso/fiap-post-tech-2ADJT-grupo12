package com.fase2.techchallenge.fiap.estacionamento.gestaoestacionamento.model;

import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.MongoId;

import java.time.LocalDateTime;
import java.util.Date;

@Data
@Document("estacionamento")
@AllArgsConstructor
@NoArgsConstructor
public class Estacionamento {

    @MongoId
    private String id;

    private String idCondutor;

    private Long idVeiculo;

    private String placa;

    private Long idMeioPagamento;

    private Double valorTarifa;

    private String tipo;

    private String situacao;

    private LocalDateTime dataHoraInicio;

    private LocalDateTime dataHoraVencimento;

    private LocalDateTime dataHoraTermino;

    private Boolean notificadoVencimento;

    private Boolean renovacaoAutomatica;

}


