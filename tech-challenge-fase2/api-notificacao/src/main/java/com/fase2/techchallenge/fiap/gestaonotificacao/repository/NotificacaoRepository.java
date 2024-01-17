package com.fase2.techchallenge.fiap.gestaonotificacao.repository;

import com.fase2.techchallenge.fiap.gestaonotificacao.entity.Notificacao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NotificacaoRepository extends JpaRepository<Notificacao, Long> {

}
