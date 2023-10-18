package com.tech.challenge.inscricao.gestaousuario.repository;

import com.tech.challenge.inscricao.gestaousuario.model.Gestor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IGestorRepository extends JpaRepository <Gestor, Long> {
}
