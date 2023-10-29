package com.tech.challenge.inscricao.gestaocandidatura.repository;

import com.tech.challenge.inscricao.gestaovaga.entity.Vaga;
import org.springframework.data.jpa.repository.JpaRepository;
import com.tech.challenge.inscricao.gestaocandidatura.entity.Candidatura;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface CandidaturaRepository extends JpaRepository<Candidatura, Long> {

    @Query("SELECT c FROM Candidatura c WHERE c.vaga.id = :vaga_id AND c.candidato.cpf = :candidato_cpf")
    Candidatura findByVagaAndCandidato(@Param("vaga_id")Long vaga_id,
                                       @Param("candidato_cpf") String candidato_cpf);
}
