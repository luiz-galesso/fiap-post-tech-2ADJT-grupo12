package com.tech.challenge.inscricao.gestaousuario.repository;

import com.tech.challenge.inscricao.gestaousuario.entity.Gestor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GestorRepository extends JpaRepository <Gestor, Long> {
}
