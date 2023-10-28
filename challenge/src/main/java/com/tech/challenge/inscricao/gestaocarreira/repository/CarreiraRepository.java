package com.tech.challenge.inscricao.gestaocarreira.repository;

import com.tech.challenge.inscricao.gestaocarreira.entity.Carreira;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CarreiraRepository extends JpaRepository<Carreira, Long> {
    Carreira findByDescricao(String descricao);
}
