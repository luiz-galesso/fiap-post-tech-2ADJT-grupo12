package com.fase2.techchallenge.fiap.estacionamento.gestaoestacionamento.repository;

import com.fase2.techchallenge.fiap.estacionamento.gestaoestacionamento.model.Estacionamento;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Repository
public interface EstacionamentoRepository extends MongoRepository<Estacionamento, String> {

    @Query("{  $and: [{'dataHoraVencimento' : {  $lte: ?0, $gte: ?1}}, { 'notificadoVencimento' : { $eq: ?2 }}]}")
    List<Estacionamento> findEstacionamentoBydataHoraVencimentoBetweenAndNotificadoVencimento
            (LocalDateTime dataHoraLTE, LocalDateTime dataHoraGTE, Boolean notificacaoVencimento);
}
