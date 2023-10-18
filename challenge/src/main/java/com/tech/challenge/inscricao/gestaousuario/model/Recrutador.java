package com.tech.challenge.inscricao.gestaousuario.model;

import jakarta.persistence.*;

@Entity
@Table(name="tb_recrutador")
public class Recrutador extends Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Embedded
    private Endereco endereco;

    public Endereco getEndereco() {
        return endereco;
    }

    public void setEndereco(Endereco endereco) {
        this.endereco = endereco;
    }
}
