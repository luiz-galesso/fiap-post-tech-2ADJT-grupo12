package com.fase2.techchallenge.fiap.fiscalizacao.gestaofiscalizacao.repository;

import com.fase2.techchallenge.fiap.fiscalizacao.gestaofiscalizacao.model.Estacionamento;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface EstacionamentoRepository extends MongoRepository<Estacionamento, String> {

    @Query("{  $and: [{'placa' : {  $eq: ?0}}, { 'situacao' : { $eq: ?1 }}]}")
    Optional<Estacionamento> findByPlacaAndSituacao(String placa, String situacao);

}
