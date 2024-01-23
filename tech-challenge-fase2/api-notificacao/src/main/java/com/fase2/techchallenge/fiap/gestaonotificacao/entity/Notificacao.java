package com.fase2.techchallenge.fiap.gestaonotificacao.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "tb_notificacao")
public class Notificacao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "O id do veiculo da notificação é obrigatório")
    private Integer idVeiculo;

    @NotNull(message = "A data e hora da notificação é obrigatório")
    private LocalDateTime dataHora;

    @NotNull(message = "O conteudo da notificação é obrigatório")
    private String conteudo;

    @NotNull(message = "A situação da notificação é obrigatório")
    private String situacao;

    public Notificacao() {
    }

    public Notificacao(Long id, Integer idVeiculo, LocalDateTime dataHora, String conteudo, String situacao) {
        this.id = id;
        this.idVeiculo = idVeiculo;
        this.dataHora = dataHora;
        this.conteudo = conteudo;
        this.situacao = situacao;
    }
}
