package com.tech.challenge.inscricao.gestaocandidatura.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.tech.challenge.inscricao.gestaocandidatura.entity.Candidatura;

public interface CandidaturaRepository extends JpaRepository<Candidatura, Long> {
}
