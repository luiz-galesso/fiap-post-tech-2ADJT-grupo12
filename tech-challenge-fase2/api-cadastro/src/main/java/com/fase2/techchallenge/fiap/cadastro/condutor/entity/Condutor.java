package com.fase2.techchallenge.fiap.cadastro.condutor.entity;

import com.fase2.techchallenge.fiap.cadastro.condutor.dto.CondutorDTO;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Table(name = "tb_condutor")
@AllArgsConstructor
@NoArgsConstructor
public class Condutor {

    @Id
    @NotNull(message = "O email do Condutor é obrigatório")
    private String email;

    private DadosPessoais dadosPessoais;
    private Endereco endereco;

    // fazer um método para alterar este valor no controler
    private boolean ativacaoAutomatica;

    public Condutor(String email) {
        this.email = email;
    }

    public CondutorDTO toCondutorResponseDTO(){
        return new CondutorDTO(
                this.email,
                this.dadosPessoais.toDadosPessoaisDTO(),
                this.endereco.toEnderecoDTO(),
                this.ativacaoAutomatica
        );
    }
}
