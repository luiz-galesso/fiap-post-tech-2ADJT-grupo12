package com.tech.challenge.inscricao.gestaousuario.repository;

import com.tech.challenge.inscricao.gestaousuario.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, String> {
}
