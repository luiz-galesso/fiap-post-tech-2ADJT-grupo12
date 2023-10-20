package com.tech.challenge.inscricao.gestaousuario.entity;

import jakarta.persistence.*;

@Entity
@Table(name="tb_gestor") //Retirar essa classe deste pacote, não está sendo utilizada
public class Gestor{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Embedded
    private Endereco endereco;

    @OneToOne(cascade = CascadeType.ALL)
    private Usuario usuario;

    public Endereco getEndereco() {
        return endereco;
    }

    public void setEndereco(Endereco endereco) {
        this.endereco = endereco;
    }
}
