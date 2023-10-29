package com.tech.challenge.inscricao.gestaovaga.dto;

import com.tech.challenge.inscricao.gestaoetapa.entity.Etapa;

import java.util.ArrayList;
import java.util.List;

public record AdicionaEtapaDTO(
        Long idVaga,
        ArrayList<Etapa> etapas

) {
}
