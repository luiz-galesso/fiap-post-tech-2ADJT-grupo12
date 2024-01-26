package com.fase2.techchallenge.fiap.cadastro.meiopagamentocondutor.service;

import com.fase2.techchallenge.fiap.cadastro.condutor.service.CondutorService;
import com.fase2.techchallenge.fiap.cadastro.exception.ControllerNotFoundException;
import com.fase2.techchallenge.fiap.cadastro.meiopagamentocondutor.dto.MeioPagamentoCondutorRequestDTO;
import com.fase2.techchallenge.fiap.cadastro.meiopagamentocondutor.dto.MeioPagamentoCondutorResponseDTO;
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

    public MeioPagamentoCondutorResponseDTO save(MeioPagamentoCondutorRequestDTO meioPagamentoCondutorRequestDTO) {
        MeioPagamentoCondutor meioPagamentoCondutor = toEntity(meioPagamentoCondutorRequestDTO);
        condutorNaoCadastrado(meioPagamentoCondutorRequestDTO.emailCondutor());
        meioPagamentoCondutor = meioPagamentoCondutorRepository.save(meioPagamentoCondutor);
        return toMeioPagamentoDTO(meioPagamentoCondutor);
    }

    public MeioPagamentoCondutorResponseDTO update(Long id, MeioPagamentoCondutorRequestDTO meioPagamentoCondutorRequestDTO) {
        try {
            MeioPagamentoCondutor meioPagamentoCondutor = meioPagamentoCondutorRepository.getReferenceById(id);
            condutorNaoCadastrado(meioPagamentoCondutorRequestDTO.emailCondutor());
            meioPagamentoCondutor.setTipoMeioPagamento(meioPagamentoCondutorRequestDTO.tipoMeioPagamento());
            meioPagamentoCondutor.setNumeroCartao(meioPagamentoCondutorRequestDTO.numeroCartao());
            meioPagamentoCondutor.setValidadeCartao(meioPagamentoCondutorRequestDTO.validadeCartao());
            meioPagamentoCondutor = meioPagamentoCondutorRepository.save(meioPagamentoCondutor);
            return toMeioPagamentoDTO(meioPagamentoCondutor);
        } catch (EntityNotFoundException e) {
            throw new ControllerNotFoundException("Condutor não localizado");
        }
    }

    public void deleteById(Long id) {
        try {
            meioPagamentoCondutorRepository.deleteById(id);
        } catch (EntityNotFoundException e) {
            throw new ControllerNotFoundException("Meio de Pagamento não localizada");
        }
    }

    public MeioPagamentoCondutorResponseDTO findByEmailCondutor(String emailCondutor) {
        try {
            condutorNaoCadastrado(emailCondutor);
            MeioPagamentoCondutor meioPagamentoCondutor = meioPagamentoCondutorRepository.findByCondutor(condutorService.findCondutorById(emailCondutor));
            return toMeioPagamentoDTO(meioPagamentoCondutor);
        } catch (EntityNotFoundException e) {
            throw new ControllerNotFoundException("Condutor não localizado");
        }
    }

    private void condutorNaoCadastrado(String idCondutor) {
        if (condutorService.findById(idCondutor) == null) {
            throw new ControllerNotFoundException("Condutor não cadastrado no sistema!");
        }
    }


    public MeioPagamentoCondutor toEntity(MeioPagamentoCondutorRequestDTO meioPagamentoCondutorRequestDTO) {
        return new MeioPagamentoCondutor(
                null,
                meioPagamentoCondutorRequestDTO.tipoMeioPagamento(),
                meioPagamentoCondutorRequestDTO.numeroCartao(),
                meioPagamentoCondutorRequestDTO.validadeCartao(),
                condutorService.findCondutorById(meioPagamentoCondutorRequestDTO.emailCondutor())
        );
    }

    public MeioPagamentoCondutorResponseDTO toMeioPagamentoDTO(MeioPagamentoCondutor meioPagamentoCondutor) {
        return new MeioPagamentoCondutorResponseDTO(
                meioPagamentoCondutor.getId(),
                meioPagamentoCondutor.getTipoMeioPagamento(),
                meioPagamentoCondutor.getNumeroCartao(),
                meioPagamentoCondutor.getValidadeCartao(),
                meioPagamentoCondutor.getCondutor().getEmail()
        );
    }
}