package com.tech.challenge.inscricao.gestaovaga.service;

import com.tech.challenge.inscricao.gestaovaga.entity.Vaga;
import com.tech.challenge.inscricao.gestaovaga.repository.VagaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class VagaService
{
    @Autowired
    private VagaRepository vagaRepository;

    public Vaga criarVaga(Vaga vaga)
    {
        return vagaRepository.save(vaga);
    }
}
