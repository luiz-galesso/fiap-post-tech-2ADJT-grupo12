package com.fase2.techchallenge.fiap.pagamento.gestaopagamento.entity;

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
@Table(name = "tb_meio_pagamento")
@AllArgsConstructor
@NoArgsConstructor
public class MeioPagamento {

    @Id
    @NotNull(message = "O id do meio de pagamento é obrigatório")
    private String id;
    @NotNull(message = "A descrição do meio de pagamento é obrigatória")
    private String descricao;

    @NotNull(message = "A situação do meio de pagamento é obrigatória")
    private String situacao;
    /* TODO fazer um enum para esse cara */

}
