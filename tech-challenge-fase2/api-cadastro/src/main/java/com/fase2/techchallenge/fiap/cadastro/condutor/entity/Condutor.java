package com.fase2.techchallenge.fiap.cadastro.condutor.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;

import java.util.Objects;

@Entity
@Table(name = "tb_condutor")
public class Condutor {

    @Id
    @NotNull(message = "O email do Condutor é obrigatório")
    private String email;

    private DadosPessoais dadosPessoais;
    private Endereco endereco;

    // fazer um método para alterar este valor no controler
    private boolean ativacaoAutomatica;

    public Condutor() {
    }

    public Condutor(String email)
    {
        this.email = email;
    }

    public Condutor(String email, DadosPessoais dadosPessoais, Endereco endereco, boolean ativacaoAutomatica) {
        this.email = email;
        this.dadosPessoais = dadosPessoais;
        this.endereco = endereco;
        this.ativacaoAutomatica = ativacaoAutomatica;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public DadosPessoais getDadosPessoais() {
        return dadosPessoais;
    }

    public void setDadosPessoais(DadosPessoais dadosPessoais) {
        this.dadosPessoais = dadosPessoais;
    }

    public Endereco getLogradouro() {
        return endereco;
    }

    public void setLogradouro(Endereco endereco) {
        this.endereco = endereco;
    }

    public boolean isAtivacaoAutomatica() {
        return ativacaoAutomatica;
    }

    public void setAtivacaoAutomatica(boolean ativacaoAutomatica) {
        this.ativacaoAutomatica = ativacaoAutomatica;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Condutor that = (Condutor) o;
        return Objects.equals(email, that.email)
                && Objects.equals(dadosPessoais, that.dadosPessoais)
                && Objects.equals(endereco, that.endereco)
                && Objects.equals(ativacaoAutomatica, that.ativacaoAutomatica);
    }

    @Override
    public int hashCode() {
        return Objects.hash(email, dadosPessoais, endereco, ativacaoAutomatica);
    }

    @Override
    public String toString() {
        return "Cadastro{" + "EMAIL ='" + email + '\'' + ", DADOS PESSOAIS='" + dadosPessoais + '\'' + ", LOGRADOURO='" + endereco
                + '\'' + ", ATIVACAO AUTOMATICA='" + ativacaoAutomatica + '\'' + '}';
    }
}
