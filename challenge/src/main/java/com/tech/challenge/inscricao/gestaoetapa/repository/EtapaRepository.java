package com.tech.challenge.inscricao.gestaoetapa.repository;

import com.tech.challenge.inscricao.gestaoetapa.entity.Etapa;
import com.tech.challenge.inscricao.gestaousuario.entity.Gestor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EtapaRepository extends JpaRepository <Etapa, Long> {
}
