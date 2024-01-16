package com.fase2.techchallenge.fiap.cadastro.condutor.entity;

import com.fase2.techchallenge.fiap.cadastro.condutor.dto.DadosPessoaisDTO;
import jakarta.persistence.Embeddable;
import jakarta.validation.constraints.NotNull;
import org.hibernate.validator.constraints.br.CPF;

@Embeddable
public class DadosPessoais {


    @NotNull(message = "O nome do Condutor é obrigatório")
    private String nome;

    @CPF
    // @Id
    @NotNull(message = "O cpf do Condutor é obrigatório")
    private String cpf;


    @NotNull(message = "O número de celular do Condutor é obrigatório")
    //Precisamos ter como mandar notificação
    private String nrCelular;

    public DadosPessoais() {
    }

    public DadosPessoais(String nome, String cpf, String nrCelular) {
        this.nome = nome;
        this.cpf = cpf;
        this.nrCelular = nrCelular;
    }

    public DadosPessoais(DadosPessoaisDTO dadosPessoaisDTO) {
        this.nome = dadosPessoaisDTO.nome();
        this.cpf = dadosPessoaisDTO.cpf();
        this.nrCelular = dadosPessoaisDTO.nrCelular();
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getNrCelular() {
        return this.nrCelular;
    }

    public void setNrCelular(String nrCelular) {
        this.nrCelular = nrCelular;
    }

    private DadosPessoais toEntity(DadosPessoaisDTO dadosPessoaisDTO) {
        return new DadosPessoais(
                dadosPessoaisDTO.nome(),
                dadosPessoaisDTO.cpf(),
                dadosPessoaisDTO.nrCelular()
        );
    }

    private DadosPessoaisDTO toDadosPessoaisDTO(DadosPessoais dadosPessoais) {
        return new DadosPessoaisDTO(
                dadosPessoais.getNome(),
                dadosPessoais.getCpf(),
                dadosPessoais.getNrCelular()
        );
    }
}
