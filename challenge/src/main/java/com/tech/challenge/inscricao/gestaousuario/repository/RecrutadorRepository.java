package com.tech.challenge.inscricao.gestaousuario.repository;

import com.tech.challenge.inscricao.gestaousuario.entity.Recrutador;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RecrutadorRepository extends JpaRepository <Recrutador, Long> {
}
