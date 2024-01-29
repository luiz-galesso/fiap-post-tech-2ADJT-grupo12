package com.fase2.techchallenge.fiap.tarifa.gestaotarifa.entity;

import com.fase2.techchallenge.fiap.tarifa.gestaotarifa.dto.TarifaDTO;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Objects;

@Data
@Entity
@Table(name="tb_tarifa")
@AllArgsConstructor
@NoArgsConstructor
public class Tarifa {

    @Id
    @NotNull(message = "O id da tarifa é obrigatório")
    private String id;
    @NotNull(message = "A descrição da tarifa é obrigatória")
    private String descricao;

    @NotNull(message = "O valor da tarifa é obrigatório")
    private Double valor;

    public TarifaDTO toTarifaDTO(){
        return new TarifaDTO(
                this.id,
                this.descricao,
                this.valor
        );
    }
}
