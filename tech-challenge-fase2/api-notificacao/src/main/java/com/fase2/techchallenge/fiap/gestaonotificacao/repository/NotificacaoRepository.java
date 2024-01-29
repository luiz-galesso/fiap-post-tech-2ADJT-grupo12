package com.fase2.techchallenge.fiap.gestaonotificacao.repository;

import com.fase2.techchallenge.fiap.gestaonotificacao.entity.Notificacao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NotificacaoRepository extends JpaRepository<Notificacao, Long> {

    List<Notificacao> findByidVeiculo(Integer idVeiculo);

}
