package com.tech.challenge.inscricao.gestaovaga.dto;

import com.tech.challenge.processoseletivo.gestaoetapa.entity.Etapa;

import java.util.ArrayList;

public record AdicionaEtapaDTO(
        Long idVaga,
        ArrayList<Etapa> etapas

) {
}
