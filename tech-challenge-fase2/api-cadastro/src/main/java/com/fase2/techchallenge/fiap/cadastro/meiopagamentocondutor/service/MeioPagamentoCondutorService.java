package com.fase2.techchallenge.fiap.cadastro.meiopagamentocondutor.service;

import com.fase2.techchallenge.fiap.cadastro.condutor.service.CondutorService;
import com.fase2.techchallenge.fiap.cadastro.exception.ControllerNotFoundException;
import com.fase2.techchallenge.fiap.cadastro.meiopagamentocondutor.dto.MeioPagamentoCondutorRequestDTO;
import com.fase2.techchallenge.fiap.cadastro.meiopagamentocondutor.dto.MeioPagamentoCondutorResponseDTO;
import com.fase2.techchallenge.fiap.cadastro.meiopagamentocondutor.dto.MeioPagamentoCondutorUpdateDTO;
import com.fase2.techchallenge.fiap.cadastro.meiopagamentocondutor.entity.MeioPagamentoCondutor;
import com.fase2.techchallenge.fiap.cadastro.meiopagamentocondutor.repository.MeioPagamentoCondutorRepository;
import io.micrometer.observation.ObservationFilter;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MeioPagamentoCondutorService {

    @Autowired
    private MeioPagamentoCondutorRepository meioPagamentoCondutorRepository;

    @Autowired
    private CondutorService condutorService;

    @Autowired
    private MeioPagamentoService meioPagamentoService;

    public MeioPagamentoCondutor save(MeioPagamentoCondutorRequestDTO meioPagamentoCondutorRequestDTO) {
        MeioPagamentoCondutor meioPagamentoCondutor = toEntity(meioPagamentoCondutorRequestDTO);
        meioPagamentoService.findByIdMeioPagamento(meioPagamentoCondutorRequestDTO.tipoMeioPagamento());
        condutorNaoCadastrado(meioPagamentoCondutorRequestDTO.emailCondutor());
        meioPagamentoCondutor = meioPagamentoCondutorRepository.save(meioPagamentoCondutor);
        return meioPagamentoCondutor;
    }

    public MeioPagamentoCondutor update(Long id, MeioPagamentoCondutorUpdateDTO meioPagamentoCondutorUpdateDTO) {
        try {
            MeioPagamentoCondutor meioPagamentoCondutor = meioPagamentoCondutorRepository.getReferenceById(id);
            meioPagamentoService.findByIdMeioPagamento(meioPagamentoCondutorUpdateDTO.tipoMeioPagamento());
            meioPagamentoCondutor.setTipoMeioPagamento(meioPagamentoCondutorUpdateDTO.tipoMeioPagamento());
            meioPagamentoCondutor.setNumeroCartao(meioPagamentoCondutorUpdateDTO.numeroCartao());
            meioPagamentoCondutor.setValidadeCartao(meioPagamentoCondutorUpdateDTO.validadeCartao());
            return meioPagamentoCondutorRepository.save(meioPagamentoCondutor);
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

    public List<MeioPagamentoCondutor> findByEmailCondutor(String emailCondutor) {
        try {
            condutorNaoCadastrado(emailCondutor);
            List<MeioPagamentoCondutor> meioPagamentoCondutorList = meioPagamentoCondutorRepository.findByCondutor(condutorService.findById(emailCondutor));
            return meioPagamentoCondutorList;
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
                condutorService.findById(meioPagamentoCondutorRequestDTO.emailCondutor())
        );
    }

    public MeioPagamentoCondutorResponseDTO findById(Long id) {
        try {

            return meioPagamentoCondutorRepository.findMeioPagamentoCondutorById(id).toMeioPagamentoCondutorResponseDTO();
        } catch (RuntimeException e) {
            throw new ControllerNotFoundException("Meio de Pagamento do Condutor não Encontrado");
        }
    }


}