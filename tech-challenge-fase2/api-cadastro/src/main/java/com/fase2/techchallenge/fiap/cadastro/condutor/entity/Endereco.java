package com.fase2.techchallenge.fiap.cadastro.condutor.entity;

import com.fase2.techchallenge.fiap.cadastro.condutor.dto.DadosPessoaisDTO;
import com.fase2.techchallenge.fiap.cadastro.condutor.dto.EnderecoDTO;
import jakarta.persistence.Embeddable;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
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

    public EnderecoDTO toEnderecoDTO() {
        return new EnderecoDTO(
                this.descricao,
                this.numero,
                this.cidade,
                this.estado,
                this.CEP,
                this.complemento
        );
    }
}
