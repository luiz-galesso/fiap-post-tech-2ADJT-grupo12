package com.tech.challenge.acesso.gestaoperfil.repository;

import com.tech.challenge.acesso.gestaoperfil.entity.Perfil;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PerfilRepository extends JpaRepository<Perfil, Long> {
    Optional<Perfil> findByDescricao(String descricao);
}
