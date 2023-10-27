package com.tech.challenge.processoseletivo.gestaoentrevista.repository;


import com.tech.challenge.processoseletivo.gestaoentrevista.entity.Entrevista;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EntrevistaRepository extends JpaRepository<Entrevista, Long> {
}
