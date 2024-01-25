package com.fase2.techchallenge.fiap.cadastro.condutor.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
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

    public Condutor(String email) {
        this.email = email;
    }

    public Condutor(String email, DadosPessoais dadosPessoais, Endereco endereco, boolean ativacaoAutomatica) {
        this.email = email;
        this.dadosPessoais = dadosPessoais;
        this.endereco = endereco;
        this.ativacaoAutomatica = ativacaoAutomatica;
    }

}
