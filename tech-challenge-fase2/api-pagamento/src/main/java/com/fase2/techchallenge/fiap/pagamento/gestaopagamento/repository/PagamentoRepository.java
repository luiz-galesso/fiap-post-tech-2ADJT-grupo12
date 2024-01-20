package com.fase2.techchallenge.fiap.pagamento.gestaopagamento.repository;

import com.fase2.techchallenge.fiap.pagamento.gestaopagamento.entity.Pagamento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PagamentoRepository extends JpaRepository<Pagamento, Long> {
    @Override
    Optional<Pagamento> findById(Long id);

    @Override
    List<Pagamento> findAll();
}
