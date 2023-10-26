package com.tech.challenge.inscricao.carreira.repository;

import com.tech.challenge.inscricao.carreira.entity.Carreira;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CarreiraRepository extends JpaRepository<Carreira, Long> {
}
