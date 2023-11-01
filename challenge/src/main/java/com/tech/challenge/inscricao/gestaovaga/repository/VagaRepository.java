package com.tech.challenge.inscricao.gestaovaga.repository;

import com.tech.challenge.inscricao.gestaovaga.entity.Vaga;
import com.tech.challenge.inscricao.gestaovaga.repository.specification.VagaSpecification;
import com.tech.challenge.inscricao.gestaovaga.service.filtros.VagaFiltro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface VagaRepository extends JpaRepository<Vaga, Long>, JpaSpecificationExecutor<Vaga>
{

    @Query(value = "SELECT v FROM Vaga v WHERE v.dataExpiracao > current_date")
    Optional<List<Vaga>> listaVagasDisponiveis();

    default List<Vaga> findAll(VagaFiltro filtro) {
        return findAll(new VagaSpecification(filtro));
    }

}
