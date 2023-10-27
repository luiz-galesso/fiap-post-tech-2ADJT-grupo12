package com.tech.challenge.inscricao.gestaovaga.repository;

import com.tech.challenge.inscricao.gestaovaga.entity.SolicitaVaga;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SolicitaVagaRepository extends JpaRepository<SolicitaVaga, Integer>
{
}
