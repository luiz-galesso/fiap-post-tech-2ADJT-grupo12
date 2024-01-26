package com.fase2.techchallenge.fiap.cadastro.meiopagamentocondutor.entity;

import com.fase2.techchallenge.fiap.cadastro.condutor.entity.Condutor;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@Entity
@Table(name = "tb_meio_pagamento_condutor")
@AllArgsConstructor
@NoArgsConstructor
public class MeioPagamentoCondutor {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "meiopagamento_generator")
    @SequenceGenerator(name = "meiopagamento_generator", sequenceName = "meiopagamento_sequence", allocationSize = 1)
    private Long id;

    @NotNull(message = "O Tipo de meio de pagamento é obrigatório")
    private String tipoMeioPagamento;

    @NotNull(message = "O número do cartão é obrigatório")
    private Long numeroCartao;

    @NotNull(message = "A validade do cartão é obrigatória")
    @Temporal(TemporalType.DATE)
    private Date validadeCartao;

    @ManyToOne
    private Condutor condutor;

}
