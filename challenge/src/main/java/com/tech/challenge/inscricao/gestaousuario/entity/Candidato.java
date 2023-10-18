package com.tech.challenge.inscricao.gestaousuario.entity;

import jakarta.persistence.*;


@Entity
@Table(name="tb_candidato")
public class Candidato extends Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Embedded
    private DadosPessoais dadosPessoais;

}
