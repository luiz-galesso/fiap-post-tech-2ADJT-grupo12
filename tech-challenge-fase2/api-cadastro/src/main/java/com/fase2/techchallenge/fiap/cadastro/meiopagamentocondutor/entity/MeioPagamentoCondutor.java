package com.fase2.techchallenge.fiap.cadastro.meiopagamentocondutor.entity;

import com.fase2.techchallenge.fiap.cadastro.condutor.entity.Condutor;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
@Entity
@Table(name = "tb_meio_pagamento_condutor")
public class MeioPagamentoCondutor {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "veiculo_generator")
    @SequenceGenerator(name = "veiculo_generator", sequenceName = "veiculo_sequence", allocationSize = 1)
    private Long id;

    @NotNull(message = "O Tipo de meio de pagamento é obrigatório")
    private String tipoMeioPagamento;

    @ManyToOne
    private Condutor condutor;

    public MeioPagamentoCondutor() {
    }

    public MeioPagamentoCondutor(Long id, String tipoMeioPagamento, Condutor condutor) {
        this.id = id;
        this.tipoMeioPagamento = tipoMeioPagamento;
        this.condutor = condutor;
    }


}
