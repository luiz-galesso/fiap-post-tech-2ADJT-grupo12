package com.fase2.techchallenge.fiap.cadastro.veiculo.entity;

import com.fase2.techchallenge.fiap.cadastro.condutor.entity.Condutor;
import com.fase2.techchallenge.fiap.cadastro.veiculo.dto.VeiculoResponseDTO;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@Entity
@Table(name = "tb_veiculo", uniqueConstraints = @UniqueConstraint(columnNames={"placa","condutor_email"}))
@AllArgsConstructor
@NoArgsConstructor
public class Veiculo implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "veiculo_generator")
    @SequenceGenerator(name = "veiculo_generator", sequenceName = "veiculo_sequence", allocationSize = 1)
    private Integer id;

    private String placa;

    private String nome;

    @ManyToOne
    private Condutor condutor;

    public VeiculoResponseDTO toVeiculoResponseDTO(){
        return new VeiculoResponseDTO(
                this.id,
                this.placa,
                this.nome,
                this.condutor.getEmail()
        );
    }

}
