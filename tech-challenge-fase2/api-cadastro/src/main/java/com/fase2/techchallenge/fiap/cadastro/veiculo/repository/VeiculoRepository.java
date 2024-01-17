package com.fase2.techchallenge.fiap.cadastro.veiculo.repository;

import com.fase2.techchallenge.fiap.cadastro.condutor.controller.exception.ControllerNotFoundException;
import com.fase2.techchallenge.fiap.cadastro.condutor.entity.Condutor;
import com.fase2.techchallenge.fiap.cadastro.veiculo.entity.Veiculo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface VeiculoRepository extends JpaRepository<Veiculo, Integer>
{
    List<Veiculo> findAllByCondutor(Condutor condutor);

    List<Veiculo> findAllByCondutor_Email(String email);

    Optional<Veiculo> findByPlaca(String placa);

    Optional<Veiculo> findById(Integer id) throws ControllerNotFoundException;

}
