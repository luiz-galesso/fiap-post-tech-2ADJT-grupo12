package com.fase2.techchallenge.fiap.cadastro.veiculo.entity;

import com.fase2.techchallenge.fiap.cadastro.condutor.entity.Condutor;
import com.fase2.techchallenge.fiap.cadastro.veiculo.dto.VeiculoDTO;
import jakarta.persistence.*;

import java.io.Serializable;

@Entity
@Table(name = "tb_veiculo")
public class Veiculo implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="veiculo_generator")
    @SequenceGenerator(name="veiculo_generator", sequenceName="veiculo_sequence", allocationSize = 1)
    private Integer id;

    private String placa;

    private String nome;

    @ManyToOne
    private Condutor condutor;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Condutor getCondutor() {
        return condutor;
    }

    public String getPlaca() {
        return placa;
    }

    public String getNome() {
        return nome;
    }

    public void setPlaca(String placa) {
        this.placa = placa;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setCondutor(Condutor condutor) {
        this.condutor = condutor;
    }

    public Veiculo() {
    }

    public Veiculo(VeiculoDTO veiculoDTO)
    {
        this.placa = veiculoDTO.placa();
        this.nome = veiculoDTO.nome();
        this.condutor = new Condutor(veiculoDTO.emailCondutor());
    }
}
