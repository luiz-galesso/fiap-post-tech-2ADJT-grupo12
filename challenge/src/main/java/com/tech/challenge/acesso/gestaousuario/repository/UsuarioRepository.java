package com.tech.challenge.acesso.gestaousuario.repository;

import com.tech.challenge.acesso.gestaousuario.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, String> {
    @Override
    Optional<Usuario> findById(String id);
}
