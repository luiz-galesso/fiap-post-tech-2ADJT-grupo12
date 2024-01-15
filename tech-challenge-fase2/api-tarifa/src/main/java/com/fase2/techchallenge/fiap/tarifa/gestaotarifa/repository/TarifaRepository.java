package com.fase2.techchallenge.fiap.tarifa.gestaotarifa.repository;

import com.fase2.techchallenge.fiap.tarifa.gestaotarifa.entity.Tarifa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TarifaRepository extends JpaRepository<Tarifa, String> {
    @Override
    Optional<Tarifa> findById(String id);

    @Override
    List<Tarifa> findAll();
}
