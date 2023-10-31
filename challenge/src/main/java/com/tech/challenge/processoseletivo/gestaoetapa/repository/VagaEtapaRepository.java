package com.tech.challenge.processoseletivo.gestaoetapa.repository;

import com.tech.challenge.processoseletivo.gestaoetapa.entity.VagaEtapa;
import com.tech.challenge.processoseletivo.gestaoetapa.entity.VagaEtapaID;
import com.tech.challenge.processoseletivo.gestaoetapa.repository.specification.VagaEtapaSpecification;
import com.tech.challenge.processoseletivo.gestaoetapa.service.filtros.VagaEtapaFiltro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VagaEtapaRepository extends JpaRepository<VagaEtapa, VagaEtapaID>, JpaSpecificationExecutor<VagaEtapa> {

    default List<VagaEtapa> findAll(VagaEtapaFiltro filtro) {
        return findAll(new VagaEtapaSpecification(filtro));
    }

}
