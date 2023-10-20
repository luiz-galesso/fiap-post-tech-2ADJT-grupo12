package com.tech.challenge.inscricao.gestaousuario.dto;

import com.tech.challenge.inscricao.gestaousuario.entity.Endereco;
import com.tech.challenge.inscricao.gestaousuario.entity.Filiacao;
import jakarta.validation.constraints.NotBlank;
import org.hibernate.validator.constraints.br.CPF;


import java.time.LocalDate;

public record CandidatoDTO(


        String nomeCompleto,
        String cpf,
        DadosPessoaisDTO dadosPessoais,
        UsuarioDTO usuario

) {
};
