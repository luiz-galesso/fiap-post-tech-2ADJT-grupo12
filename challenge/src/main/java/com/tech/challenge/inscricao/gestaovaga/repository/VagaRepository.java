package com.tech.challenge.inscricao.gestaovaga.repository;

import com.tech.challenge.inscricao.gestaovaga.entity.Vaga;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface VagaRepository extends JpaRepository<Vaga, Long>
{

    @Query(value = "SELECT v FROM Vaga v WHERE v.dataExpiracao > current_date")
    Optional<List<Vaga>> listaVagasDisponiveis();

}
