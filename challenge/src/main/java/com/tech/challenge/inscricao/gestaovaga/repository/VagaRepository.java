package com.tech.challenge.inscricao.gestaovaga.repository;

import com.tech.challenge.inscricao.gestaovaga.entity.Vaga;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VagaRepository extends JpaRepository<Vaga, Long> {
}
