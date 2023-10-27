package com.tech.challenge.inscricao.gestaoperfil.repository;

import com.tech.challenge.inscricao.gestaoperfil.entity.Perfil;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PerfilRepository extends JpaRepository<Perfil, Long> {
}
