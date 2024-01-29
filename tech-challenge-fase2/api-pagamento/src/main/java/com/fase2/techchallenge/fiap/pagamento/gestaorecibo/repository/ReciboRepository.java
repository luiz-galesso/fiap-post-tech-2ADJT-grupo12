package com.fase2.techchallenge.fiap.pagamento.gestaorecibo.repository;

import com.fase2.techchallenge.fiap.pagamento.gestaorecibo.entity.Recibo;
import com.fase2.techchallenge.fiap.pagamento.gestaorecibo.enumeration.ReciboSituacao;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ReciboRepository extends JpaRepository<Recibo, Long> {

    @Override
    Optional<Recibo> findById(Long id);


    @Override
    List<Recibo> findAll();

    Recibo findByDadosPagamento_IdPagamento(Long idPagamento);

    List<Recibo> findByReciboSituacao(ReciboSituacao reciboSituacao);

    List<Recibo> findByDadosCondutor_email(String idCondutor);

}
