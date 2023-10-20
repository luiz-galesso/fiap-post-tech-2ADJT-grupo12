package com.tech.challenge.inscricao.gestaovaga.entity;

import com.tech.challenge.inscricao.gestaoetapa.entity.Etapa;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name="tb_vaga")
public class Vaga {
    @NotNull(message="O título é obrigatório")
    private String titulo;
    @NotNull(message="A descrição é obrigatória")
    private String descricao;
    private List<Etapa> etapas;
    @NotNull(message="A carreira é obrigatória")
    private String carreira;
    private String nivel;
    private LocalDate dataExpiracao;
}
