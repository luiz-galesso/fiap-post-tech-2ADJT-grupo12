package com.tech.challenge.inscricao.gestaovaga.repository.specification;

import com.tech.challenge.inscricao.gestaovaga.enumeration.Nivel;
import com.tech.challenge.inscricao.gestaovaga.service.filtros.SolicitacaoVagaFiltro;
import com.tech.challenge.inscricao.gestaovaga.entity.SolicitacaoVaga;
import com.tech.challenge.inscricao.gestaovaga.enumeration.SolicitacaoSituacao;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class SolicitacaoVagaSpecification implements Specification<SolicitacaoVaga> {


    private final SolicitacaoVagaFiltro filtro;

    public SolicitacaoVagaSpecification(SolicitacaoVagaFiltro filtro) {
        this.filtro = filtro;
    }

    @Override
    public Predicate toPredicate(Root<SolicitacaoVaga> root, CriteriaQuery<?> query, CriteriaBuilder builder) {
        List<Predicate> predicates = new ArrayList<>();

        if (Objects.nonNull(filtro.getSituacao())) {
            Predicate predicate = builder.equal(root.get("situacao"), SolicitacaoSituacao.valueOf(filtro.getSituacao()));
            predicates.add(predicate);
        }

        if (Objects.nonNull(filtro.getNivel())) {
            Predicate predicate = builder.equal(root.get("nivel"), Nivel.valueOf(filtro.getNivel()));
            predicates.add(predicate);
        }

        if (Objects.nonNull(filtro.getIdSolicitante())) {
            Predicate predicate = builder.equal(root.get("solicitante").get("cpf"), filtro.getIdSolicitante());
            predicates.add(predicate);
        }

        if (Objects.nonNull(filtro.getIdAvaliador())) {
            Predicate predicate = builder.equal(root.get("avaliador").get("cpf"), filtro.getIdAvaliador());
            predicates.add(predicate);
        }

        return builder.and(predicates.toArray(new Predicate[0]));
    }
}
