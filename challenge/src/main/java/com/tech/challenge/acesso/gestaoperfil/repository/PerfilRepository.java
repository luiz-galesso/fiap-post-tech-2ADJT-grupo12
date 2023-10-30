package com.tech.challenge.acesso.gestaoperfil.repository;

import com.tech.challenge.acesso.gestaoperfil.entity.Perfil;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PerfilRepository extends JpaRepository<Perfil, Long> {
}
