package com.tech.challenge.processoseletivo.gestaoetapa.repository;

import com.tech.challenge.processoseletivo.gestaoetapa.entity.VagaEtapaOrdem;
import com.tech.challenge.processoseletivo.gestaoetapa.entity.VagaEtapaOrdemId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VagaEtapaOrdemRepository extends JpaRepository<VagaEtapaOrdem, VagaEtapaOrdemId> {
}
