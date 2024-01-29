package com.fase2.techchallenge.fiap.pagamento.gestaopagamento.repository;

import com.fase2.techchallenge.fiap.pagamento.gestaopagamento.entity.MeioPagamento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface MeioPagamentoRepository extends JpaRepository<MeioPagamento, String> {
    @Override
    Optional<MeioPagamento> findById(String id);

    @Override
    List<MeioPagamento> findAll();
}
