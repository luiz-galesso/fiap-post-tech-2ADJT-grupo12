package com.fase2.techchallenge.fiap.gestaonotificacao.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "tb_notificacao")
@AllArgsConstructor
@NoArgsConstructor
public class Notificacao {

    @Id
    @NotNull
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "notificacao_generator")
    @SequenceGenerator(name = "notificacao_generator", sequenceName = "notificacao_sequence", allocationSize = 1)
    private Long id;

    @NotNull(message = "O id do veiculo da notificação é obrigatório")
    private Integer idVeiculo;

    @NotNull(message = "A data e hora da notificação é obrigatório")
    @JsonFormat(pattern="yyyy-MM-dd")
    private LocalDateTime dataHora;

    @NotNull(message = "O conteudo da notificação é obrigatório")
    private String conteudo;

    @NotNull(message = "A situação da notificação é obrigatório")
    private String situacao;

}
