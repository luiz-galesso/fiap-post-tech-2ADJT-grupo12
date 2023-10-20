package com.tech.challenge.inscricao.gestaousuario.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.hibernate.validator.constraints.br.CPF;


@Entity
@Table(name="tb_candidato")
public class Candidato {

    @NotBlank
    @NotNull(message="O nome é obrigatório")
    private String nome;

    @Id
    @CPF
    @NotNull(message="O cpf é obrigatório")
    private String cpf;

    @Embedded
    private DadosPessoais dadosPessoais;

    @OneToOne(cascade = CascadeType.ALL)
    @NotNull(message="O usuario é obrigatório")
    private Usuario usuario;

    public Candidato (){}
    public Candidato(String nome, String cpf, DadosPessoais dadosPessoais, Usuario usuario) {
        this.setNome(nome);
        this.setCpf(cpf);
        this.setDadosPessoais(dadosPessoais);
        this.setUsuario(usuario);
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public DadosPessoais getDadosPessoais() {
        return dadosPessoais;
    }

    public void setDadosPessoais(DadosPessoais dadosPessoais) {
        this.dadosPessoais = dadosPessoais;
    }
}
