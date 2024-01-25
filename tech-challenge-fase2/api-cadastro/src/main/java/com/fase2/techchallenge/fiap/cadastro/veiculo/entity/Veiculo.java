package com.fase2.techchallenge.fiap.cadastro.veiculo.entity;

import com.fase2.techchallenge.fiap.cadastro.condutor.entity.Condutor;
import com.fase2.techchallenge.fiap.cadastro.veiculo.dto.VeiculoDTO;
import jakarta.persistence.*;
import lombok.Data;

import java.io.Serializable;

@Data
@Entity
@Table(name = "tb_veiculo")
public class Veiculo implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "veiculo_generator")
    @SequenceGenerator(name = "veiculo_generator", sequenceName = "veiculo_sequence", allocationSize = 1)
    private Integer id;

    private String placa;

    private String nome;

    @ManyToOne
    private Condutor condutor;

    public Veiculo() {
    }

    public Veiculo(VeiculoDTO veiculoDTO) {
        this.placa = veiculoDTO.placa();
        this.nome = veiculoDTO.nome();
        this.condutor = new Condutor(veiculoDTO.emailCondutor());
    }
}
