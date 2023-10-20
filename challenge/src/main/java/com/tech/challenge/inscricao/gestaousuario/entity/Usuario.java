package com.tech.challenge.inscricao.gestaousuario.entity;

import com.tech.challenge.inscricao.gestaousuario.dto.UsuarioDTO;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="tb_usuario")
public class Usuario {

    /*AJUSTAR O EVENT STORMING*/
    @Id
    private String nomeUsuario;

    public Usuario(){}

    public Usuario(UsuarioDTO usuarioDTO) {
        this.nomeUsuario = usuarioDTO.nomeUsuario();
    }

    public String getNomeUsuario() {
        return nomeUsuario;
    }

    public void setNomeUsuario(String nome) {
        this.nomeUsuario = nome;
    }

    //private Perfil perfil;

}
