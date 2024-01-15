package com.fase2.techchallenge.fiap.tarifa.gestaotarifa.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;

import java.util.Objects;

@Entity
@Table(name="tb_tarifa")
public class Tarifa {

    @Id
    @NotNull(message = "O id da tarifa é obrigatório")
    private String id;
    @NotNull(message = "A descrição da tarifa é obrigatória")
    private String descricao;

    @NotNull(message = "O valor da tarifa é obrigatório")
    private Double valor;
    /* TODO fazer um enum para esse cara */

    public Tarifa() {
    }

    public Tarifa(String id, String descricao, Double valor) {
        this.id = id;
        this.descricao = descricao;
        this.valor = valor;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Double getValor() {
        return valor;
    }

    public void setValor(Double valor) {
        this.valor = valor;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Tarifa that = (Tarifa) o;
        return Objects.equals(id, that.id) && Objects.equals(descricao, that.descricao) && Objects.equals(valor, that.valor);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, descricao, valor);
    }

    @Override
    public String toString() {
        return "Tarifa{" +
                "id='" + id + '\'' +
                ", descricao='" + descricao + '\'' +
                ", valor='" + valor + '\'' +
                '}';
    }
}
