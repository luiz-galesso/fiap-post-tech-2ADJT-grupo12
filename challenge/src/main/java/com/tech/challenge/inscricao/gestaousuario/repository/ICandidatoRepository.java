package com.tech.challenge.inscricao.gestaousuario.repository;

import com.tech.challenge.inscricao.gestaousuario.model.Candidato;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ICandidatoRepository extends JpaRepository<Candidato, Long> {
}
