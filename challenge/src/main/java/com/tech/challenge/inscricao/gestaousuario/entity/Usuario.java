package com.tech.challenge.inscricao.gestaousuario.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.hibernate.validator.constraints.br.CPF;


@Entity
@Table(name="tb_usuario")
public class Usuario {

    @Id
    @CPF
    @NotNull(message="O cpf é obrigatório")
    private String cpf;

    @NotBlank
    @NotNull(message="O nome do usuário é obrigatório")
    private String nomeUsuario;

    @NotBlank
    @NotNull(message="O nome é obrigatório")
    private String nome;

    @Embedded
    private DadosPessoais dadosPessoais;

    @Embedded
    private Perfil perfil; // validar como fazer o relacionamento e guardar apenas o id na tabela

    private boolean ativo;

    public Usuario (){}

    public Usuario (String id){
        this.cpf = id;
    }

    public Usuario(String cpf, String nomeUsuario, String nome, DadosPessoais dadosPessoais, Perfil perfil) {
        this.cpf = cpf;
        this.nomeUsuario = nomeUsuario;
        this.nome = nome;
        this.dadosPessoais = dadosPessoais;
        this.perfil = perfil;
        this.ativo = true;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getNomeUsuario() {
        return nomeUsuario;
    }

    public void setNomeUsuario(String nomeUsuario) {
        this.nomeUsuario = nomeUsuario;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public DadosPessoais getDadosPessoais() {
        return dadosPessoais;
    }

    public void setDadosPessoais(DadosPessoais dadosPessoais) {
        this.dadosPessoais = dadosPessoais;
    }

    public Perfil getPerfil() {
        return perfil;
    }

    public void setPerfil(Perfil perfil) {
        this.perfil = perfil;
    }

    public boolean isAtivo() {
        return ativo;
    }

    public void setAtivo(boolean ativo) {
        this.ativo = ativo;
    }
}