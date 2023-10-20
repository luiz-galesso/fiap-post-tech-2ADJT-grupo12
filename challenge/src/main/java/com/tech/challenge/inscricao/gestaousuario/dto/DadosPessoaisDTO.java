package com.tech.challenge.inscricao.gestaousuario.dto;

import com.tech.challenge.inscricao.gestaousuario.entity.Endereco;
import com.tech.challenge.inscricao.gestaousuario.entity.Filiacao;
import jakarta.persistence.Embedded;

import java.time.LocalDate;

public record DadosPessoaisDTO(

        String estadoCivil,
        String genero,
        LocalDate dataNascimento,
        String celular,
        String naturalidade,
        FiliacaoDTO filiacao,
        EnderecoDTO endereco
) {
};
