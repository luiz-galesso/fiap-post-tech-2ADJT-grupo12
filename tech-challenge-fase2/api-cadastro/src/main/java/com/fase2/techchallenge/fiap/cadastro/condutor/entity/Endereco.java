package com.fase2.techchallenge.fiap.cadastro.condutor.entity;

import com.fase2.techchallenge.fiap.cadastro.condutor.dto.EnderecoDTO;
import jakarta.persistence.Embeddable;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

@Valid
@Embeddable
public class Endereco {

    private String descricao;
    private String numero;

    private String cidade;

    private String estado;

    @NotNull(message = "O CEP do condutor é obrigatório")
    private String CEP;

    private String complemento;

    public Endereco() {
    }

    public Endereco(String descricao, String numero, String cidade, String estado, String CEP, String complemento) {
        this.descricao = descricao;
        this.numero = numero;
        this.cidade = cidade;
        this.estado = estado;
        this.CEP = CEP;
        this.complemento = complemento;
    }

    public Endereco(EnderecoDTO endereco) {
        this.descricao = endereco.descricao();
        this.numero = endereco.numero();
        this.cidade = endereco.cidade();
        this.estado = endereco.estado();
        this.CEP = endereco.CEP();
        this.complemento = endereco.complemento();
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getCEP() {
        return CEP;
    }

    public void setCEP(String CEP) {
        this.CEP = CEP;
    }

    public String getComplemento() {
        return complemento;
    }

    public void setComplemento(String complemento) {
        this.complemento = complemento;
    }

    private Endereco toEntity(EnderecoDTO enderecoDTO) {
        return new Endereco(
                enderecoDTO.descricao(),
                enderecoDTO.numero(),
                enderecoDTO.cidade(),
                enderecoDTO.estado(),
                enderecoDTO.CEP(),
                enderecoDTO.complemento()
        );
    }

    private EnderecoDTO toEnderecoDTO(Endereco endereco) {
        return new EnderecoDTO(
                endereco.getDescricao(),
                endereco.getNumero(),
                endereco.getCidade(),
                endereco.getEstado(),
                endereco.getCEP(),
                endereco.getComplemento()
        );
    }
}
