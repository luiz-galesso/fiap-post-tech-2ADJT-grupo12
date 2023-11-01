package com.tech.challenge.processoseletivo.gestaoetapa.repository.specification;

import com.tech.challenge.inscricao.gestaovaga.entity.SolicitacaoVaga;
import com.tech.challenge.processoseletivo.gestaoetapa.entity.VagaEtapa;
import com.tech.challenge.processoseletivo.gestaoetapa.service.filtros.VagaEtapaFiltro;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class VagaEtapaSpecification implements Specification<VagaEtapa> {


    private final VagaEtapaFiltro filtro;

    public VagaEtapaSpecification(VagaEtapaFiltro filtro) {
        this.filtro = filtro;
    }

    @Override
    public Predicate toPredicate(Root<VagaEtapa> root, CriteriaQuery<?> query, CriteriaBuilder builder) {
        List<Predicate> predicates = new ArrayList<>();

        if (Objects.nonNull(filtro.getIdVaga())) {
            Predicate predicate = builder.equal(root.get("vagaEtapaID").get("vaga").get("id"), filtro.getIdVaga());
            predicates.add(predicate);
        }

        if (Objects.nonNull(filtro.getIdEtapa())) {
            Predicate predicate = builder.equal(root.get("vagaEtapaID").get("etapa").get("idEtapa"), filtro.getIdEtapa());
            predicates.add(predicate);
        }

        return builder.and(predicates.toArray(new Predicate[0]));
    }
}
