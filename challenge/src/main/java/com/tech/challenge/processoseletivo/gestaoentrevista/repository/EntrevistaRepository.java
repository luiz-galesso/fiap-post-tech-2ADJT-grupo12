package com.tech.challenge.processoseletivo.gestaoentrevista.repository;


import com.tech.challenge.inscricao.gestaousuario.entity.Usuario;
import com.tech.challenge.processoseletivo.gestaoentrevista.entity.Entrevista;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface EntrevistaRepository extends JpaRepository<Entrevista, Long> {

    Optional<Entrevista> findByCandidato(Usuario usuario);

    Optional<Entrevista> findByEntrevistador(Usuario usuario);
}
