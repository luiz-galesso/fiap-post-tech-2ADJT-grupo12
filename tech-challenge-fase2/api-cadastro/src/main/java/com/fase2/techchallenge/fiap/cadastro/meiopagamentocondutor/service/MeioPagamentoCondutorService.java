package com.fase2.techchallenge.fiap.cadastro.meiopagamentocondutor.service;

import com.fase2.techchallenge.fiap.cadastro.exception.ControllerNotFoundException;
import com.fase2.techchallenge.fiap.cadastro.exception.EntityFoundException;

import com.fase2.techchallenge.fiap.cadastro.condutor.service.CondutorService;
import com.fase2.techchallenge.fiap.cadastro.meiopagamentocondutor.dto.MeioPagamentoCondutorDTO;
import com.fase2.techchallenge.fiap.cadastro.meiopagamentocondutor.entity.MeioPagamentoCondutor;
import com.fase2.techchallenge.fiap.cadastro.meiopagamentocondutor.repository.MeioPagamentoCondutorRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MeioPagamentoCondutorService {

    @Autowired
    private MeioPagamentoCondutorRepository meioPagamentoCondutorRepository;

    @Autowired
    private CondutorService condutorService;

    public MeioPagamentoCondutorDTO save(MeioPagamentoCondutorDTO meioPagamentoCondutorDTO) {
        MeioPagamentoCondutor meioPagamentoCondutor = toEntity(meioPagamentoCondutorDTO);
        condutorNaoCadastrado(meioPagamentoCondutorDTO);
        metodoJaCadastrado(meioPagamentoCondutor.getEmailCondutor());
        meioPagamentoCondutor = meioPagamentoCondutorRepository.save(meioPagamentoCondutor);
        return toMeioPagamentoDTO(meioPagamentoCondutor);
    }


    public MeioPagamentoCondutorDTO update(String id, MeioPagamentoCondutorDTO meioPagamentoCondutorDTO) {
        try {
            MeioPagamentoCondutor meioPagamentoCondutor = meioPagamentoCondutorRepository.findByEmailCondutor(id);
            condutorNaoExistente(meioPagamentoCondutor);
            meioPagamentoCondutor.setMeioPagamentoFavorito(meioPagamentoCondutorDTO.meioPagamento());
            meioPagamentoCondutor = meioPagamentoCondutorRepository.save(meioPagamentoCondutor);
            return toMeioPagamentoDTO(meioPagamentoCondutor);
        } catch (EntityNotFoundException e) {
            throw new ControllerNotFoundException("Condutor não localizado");
        }
    }

    public MeioPagamentoCondutorDTO findByEmailCondutor(String id) {
        try {

            MeioPagamentoCondutor meioPagamentoCondutor =
                    meioPagamentoCondutorRepository.findByEmailCondutor(id);
            condutorNaoExistente(meioPagamentoCondutor);
            return toMeioPagamentoDTO(
                    meioPagamentoCondutor);
        } catch (EntityNotFoundException e) {
            throw new ControllerNotFoundException("Condutor não localizado");
        }
    }

    private void metodoJaCadastrado(String emailCondutor) {
        if ( meioPagamentoCondutorRepository.findByEmailCondutor(emailCondutor) != null) {
            throw new EntityFoundException("Meio de Pagamento já cadastrado!");
        }
    }

    private void condutorNaoCadastrado(MeioPagamentoCondutorDTO meioPagamentoCondutorDTO){
        if(condutorService.findById(meioPagamentoCondutorDTO.email()) == null){
            throw new EntityFoundException("Condutor não cadastrado no sistema!");
        }
    }


    private void condutorNaoExistente(MeioPagamentoCondutor meioPagamentoCondutor) {
        if (meioPagamentoCondutor == null) {
            throw new EntityNotFoundException("Condutor não localizado");
        }
    }


    public MeioPagamentoCondutor toEntity(MeioPagamentoCondutorDTO meioPagamentoCondutorDTO) {
        return new MeioPagamentoCondutor(
                meioPagamentoCondutorDTO.email(),
                meioPagamentoCondutorDTO.meioPagamento()
        );
    }

    public MeioPagamentoCondutorDTO toMeioPagamentoDTO(MeioPagamentoCondutor meioPagamentoCondutor) {
        return new MeioPagamentoCondutorDTO(
                meioPagamentoCondutor.getMeioPagamentoFavorito(),
                meioPagamentoCondutor.getEmailCondutor()
        );
    }
}