package com.tech.challenge.inscricao.gestaovaga.repository;

import com.tech.challenge.inscricao.gestaovaga.service.filtros.SolicitacaoVagaFiltro;
import com.tech.challenge.inscricao.gestaovaga.entity.SolicitacaoVaga;
import com.tech.challenge.inscricao.gestaovaga.repository.specification.SolicitacaoVagaSpecification;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SolicitacaoVagaRepository extends JpaRepository<SolicitacaoVaga, Integer>, JpaSpecificationExecutor<SolicitacaoVaga>
{
    default List<SolicitacaoVaga> findAll(SolicitacaoVagaFiltro filtro) {
        return findAll(new SolicitacaoVagaSpecification(filtro));
    }


}
