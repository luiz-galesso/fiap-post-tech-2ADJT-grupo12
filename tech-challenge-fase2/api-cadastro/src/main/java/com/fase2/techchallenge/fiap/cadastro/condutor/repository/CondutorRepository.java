package com.fase2.techchallenge.fiap.cadastro.condutor.repository;

import com.fase2.techchallenge.fiap.cadastro.condutor.entity.Condutor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CondutorRepository extends JpaRepository<Condutor, String> {
    @Override
    Optional<Condutor> findById(String id);

    @Override
    List<Condutor> findAll();
}
