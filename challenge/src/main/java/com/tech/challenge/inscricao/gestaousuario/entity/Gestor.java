package com.tech.challenge.inscricao.gestaousuario.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "tb_gestor")
public class Gestor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Embedded
    private Endereco endereco;

    @OneToOne(cascade = CascadeType.ALL)
    private Usuario usuario;

    public Gestor() {
    }

    public Gestor(Endereco endereco, Usuario usuario) {
        this.endereco = endereco;
        this.usuario = usuario;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Endereco getEndereco() {
        return endereco;
    }

    public void setEndereco(Endereco endereco) {
        this.endereco = endereco;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
}
