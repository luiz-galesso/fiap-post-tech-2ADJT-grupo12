package com.tech.challenge.inscricao.gestaovaga.repository.specification;

import com.tech.challenge.inscricao.gestaovaga.entity.Vaga;
import com.tech.challenge.inscricao.gestaovaga.enumeration.Nivel;
import com.tech.challenge.inscricao.gestaovaga.enumeration.VagaSituacao;
import com.tech.challenge.inscricao.gestaovaga.service.filtros.VagaFiltro;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class VagaSpecification implements Specification<Vaga> {


    private final VagaFiltro filtro;

    public VagaSpecification(VagaFiltro filtro) {
        this.filtro = filtro;
    }

    @Override
    public Predicate toPredicate(Root<Vaga> root, CriteriaQuery<?> query, CriteriaBuilder builder) {
        List<Predicate> predicates = new ArrayList<>();

        if (Objects.nonNull(filtro.getSituacao())) {
            Predicate predicate = builder.equal(root.get("situacao"), VagaSituacao.valueOf(filtro.getSituacao()));
            predicates.add(predicate);
        }

        if (Objects.nonNull(filtro.getNivel())) {
            Predicate predicate = builder.equal(root.get("nivel"), Nivel.valueOf(filtro.getNivel()));
            predicates.add(predicate);
        }

        if (Objects.nonNull(filtro.getIdSolicitante())) {
            Predicate predicate = builder.equal(root.get("criador").get("cpf"), filtro.getIdSolicitante());
            predicates.add(predicate);
        }

        return builder.and(predicates.toArray(new Predicate[0]));
    }
}
