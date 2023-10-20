package com.tech.challenge.inscricao.gestaousuario.entity;

import com.tech.challenge.inscricao.gestaousuario.dto.EnderecoDTO;
import com.tech.challenge.util.StringUtils;
import jakarta.persistence.Embeddable;
import jakarta.validation.constraints.NotNull;

@Embeddable
public class Endereco {
    @NotNull(message="O cep é obrigatório")
    private String cep;
    @NotNull(message="O numero é obrigatório")
    private String numero;
    private String complemento;

    public Endereco(){}
    public Endereco(String cep, String numero, String complemento) {
        this.cep = StringUtils.removeMascara(cep);
        this.numero = numero;
        this.complemento = complemento;
    }

    public Endereco(EnderecoDTO endereco) {
        setCep(StringUtils.removeMascara(cep));
        setNumero(endereco.numero());
        setComplemento(endereco.complemento());
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getComplemento() {
        return complemento;
    }

    public void setComplemento(String complemento) {
        this.complemento = complemento;
    }
}
