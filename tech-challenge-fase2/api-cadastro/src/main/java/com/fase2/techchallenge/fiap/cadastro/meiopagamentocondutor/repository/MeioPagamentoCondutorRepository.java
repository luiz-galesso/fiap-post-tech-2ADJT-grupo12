package com.fase2.techchallenge.fiap.cadastro.meiopagamentocondutor.repository;

import com.fase2.techchallenge.fiap.cadastro.condutor.entity.Condutor;
import com.fase2.techchallenge.fiap.cadastro.meiopagamentocondutor.entity.MeioPagamentoCondutor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MeioPagamentoCondutorRepository extends JpaRepository<MeioPagamentoCondutor, Long> {
    List<MeioPagamentoCondutor> findByCondutor(Condutor condutor);
    MeioPagamentoCondutor findMeioPagamentoCondutorById(Long id);
}
