package com.fase2.techchallenge.fiap.cadastro.veiculo.service;

import com.fase2.techchallenge.fiap.cadastro.condutor.entity.Condutor;
import com.fase2.techchallenge.fiap.cadastro.veiculo.dto.VeiculoRequestDTO;
import com.fase2.techchallenge.fiap.cadastro.veiculo.dto.VeiculoResponseDTO;
import com.fase2.techchallenge.fiap.cadastro.veiculo.dto.VeiculoUpdateDTO;
import com.fase2.techchallenge.fiap.cadastro.veiculo.entity.Veiculo;
import com.fase2.techchallenge.fiap.cadastro.veiculo.repository.VeiculoRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class VeiculoService
{
    private final VeiculoRepository repository;

    public VeiculoService(VeiculoRepository repository) {
        this.repository = repository;
    }

    public Veiculo save(Veiculo veiculo)
    {
        return this.repository.save(veiculo);
    }

    public Optional<Veiculo> findById(Integer id)
    {
        return Optional.of(this.repository.findById(id).orElseThrow());
    }

    public Veiculo update(Veiculo veiculo, VeiculoUpdateDTO veiculoUpdateDTO) {
        if (!veiculoUpdateDTO.nome().isBlank())
            veiculo.setNome(veiculoUpdateDTO.nome());

        if (!veiculoUpdateDTO.placa().isBlank())
            veiculo.setPlaca(veiculoUpdateDTO.placa());
        return this.repository.save(veiculo);
    }

    public Optional<Veiculo> findByPlaca(String placa)
    {
        return this.repository.findByPlaca(placa);
    }

    public List<Veiculo> listAll()
    {
        return this.repository.findAll();
    }

    public List<Veiculo> listAllByCondutor(Condutor condutor)
    {
        return this.repository.findAllByCondutor(condutor);
    }

    public List<Veiculo> listAllByCondutor(String email)
    {
        return this.repository.findAllByCondutor_Email(email);
    }

    public void delete(Integer id)
    {
        this.repository.deleteById(id);
    }

}
