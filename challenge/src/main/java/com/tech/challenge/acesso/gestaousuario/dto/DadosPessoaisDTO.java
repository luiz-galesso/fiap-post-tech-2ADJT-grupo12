package com.tech.challenge.acesso.gestaousuario.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;

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
