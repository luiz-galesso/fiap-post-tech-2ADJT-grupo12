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

    @Query("{  $and: [{'dataHoraVencimento' : {  $lte: ?0, $gte: ?1}}, { 'notificadoVencimento' : { $eq: ?2 }}, { 'renovacaoAutomatica' : { $eq: ?3 }}, { 'situacao' : { $eq: ?3 }}]}")
    List<Estacionamento> findEstacionamentoBydataHoraVencimentoBetweenAndNotificadoVencimentoAndRenovacaoAutomaticaAndSituacao
            (LocalDateTime dataHoraLTE, LocalDateTime dataHoraGTE, Boolean notificacaoVencimento, Boolean renovacaoAutomatica, String situacao);

    @Query("{  $and: [{'dataHoraVencimento' : { $lte: ?0}}, { 'renovacaoAutomatica' : { $eq: ?1 }}, { 'tipo' : { $eq: ?2 }}, { 'situacao' : { $eq: ?3 }}]}")
    List<Estacionamento> findEstacionamentoBydataHoraVencimentoLowerThanAndRenovacaoAutomaticaAndTipoAndSituacao
            (LocalDateTime dataHoraLTE, Boolean renovacaoAutomatica, String tipo, String situacao);

    @Query("{  $and: [{'dataHoraVencimento' : { $lte: ?0}}, { 'tipo' : { $eq: ?1 }}, { 'situacao' : { $eq: ?2 }}]}")
    List<Estacionamento> findEstacionamentoBydataHoraVencimentoLowerThanAndTipoAndSituacao
            (LocalDateTime dataHoraLTE, String tipo, String situacao);

    List<Estacionamento> findByIdCondutor(String emailCondutor);

}
