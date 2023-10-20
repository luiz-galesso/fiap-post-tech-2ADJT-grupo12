package com.tech.challenge.inscricao.gestaousuario.entity;

import com.tech.challenge.inscricao.gestaousuario.dto.DadosPessoaisDTO;
import com.tech.challenge.inscricao.gestaousuario.dto.FiliacaoDTO;
import jakarta.persistence.Embeddable;
import jakarta.persistence.Embedded;

import java.time.LocalDate;

@Embeddable
public class DadosPessoais {
    private String estadoCivil;
    private String genero;
    private LocalDate dataNascimento;
    private Integer celular;
    private String naturalidade;
    @Embedded
    private Filiacao filiacao;
    @Embedded
    private Endereco endereco;

    public DadosPessoais(DadosPessoaisDTO dadosPessoaisDTO){
        this.estadoCivil = dadosPessoaisDTO.estadoCivil();
        this.genero = dadosPessoaisDTO.genero();
        this.dataNascimento = dadosPessoaisDTO. dataNascimento();
        this.celular = dadosPessoaisDTO.celular();
        this.naturalidade = dadosPessoaisDTO.naturalidade();
        this.filiacao = new Filiacao(dadosPessoaisDTO.filiacao().nomeMae(),
                dadosPessoaisDTO.filiacao().nomePai());
        this.endereco = new Endereco(dadosPessoaisDTO.endereco().cep(),
                dadosPessoaisDTO.endereco().numero(),
                dadosPessoaisDTO.endereco().complemento()
                );
    }

    public String getEstadoCivil() {
        return estadoCivil;
    }

    public void setEstadoCivil(String estadoCivil) {
        this.estadoCivil = estadoCivil;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public LocalDate getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(LocalDate dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public Integer getCelular() {
        return celular;
    }

    public void setCelular(Integer celular) {
        this.celular = celular;
    }

    public String getNaturalidade() {
        return naturalidade;
    }

    public void setNaturalidade(String naturalidade) {
        this.naturalidade = naturalidade;
    }

    public Filiacao getFiliacao() {
        return filiacao;
    }

    public void setFiliacao(Filiacao filiacao) {
        this.filiacao = filiacao;
    }

    public Endereco getEndereco() {
        return endereco;
    }

    public void setEndereco(Endereco endereco) {
        this.endereco = endereco;
    }
}
