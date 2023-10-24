package com.tech.challenge.processoseletivo.gestaoentrevista.dto;

import com.tech.challenge.inscricao.gestaousuario.entity.Candidato;
import com.tech.challenge.inscricao.gestaousuario.entity.Usuario;

import java.util.Date;

public record EntrevistaDTO(
        Long id,

        Date dataHora,

        String local,

        Candidato candidato,

        Usuario entrevistador
) {
}
