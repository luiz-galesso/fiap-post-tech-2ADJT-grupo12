package com.fase2.techchallenge.fiap.cadastro.meiopagamentocondutor.service;

import com.fase2.techchallenge.fiap.cadastro.condutor.service.CondutorService;
import com.fase2.techchallenge.fiap.cadastro.exception.ControllerNotFoundException;
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
        condutorNaoCadastrado(meioPagamentoCondutorDTO.emailCondutor());
        meioPagamentoCondutor = meioPagamentoCondutorRepository.save(meioPagamentoCondutor);
        return toMeioPagamentoDTO(meioPagamentoCondutor);
    }

    public MeioPagamentoCondutorDTO update(Long id, MeioPagamentoCondutorDTO meioPagamentoCondutorDTO) {
        try {
            MeioPagamentoCondutor meioPagamentoCondutor = meioPagamentoCondutorRepository.getReferenceById(id);
            condutorNaoCadastrado(meioPagamentoCondutorDTO.emailCondutor());
            meioPagamentoCondutor.setTipoMeioPagamento(meioPagamentoCondutorDTO.tipoMeioPagamento());
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

    public MeioPagamentoCondutorDTO findByEmailCondutor(String emailCondutor) {
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


    public MeioPagamentoCondutor toEntity(MeioPagamentoCondutorDTO meioPagamentoCondutorDTO) {
        return new MeioPagamentoCondutor(
                meioPagamentoCondutorDTO.id(),
                meioPagamentoCondutorDTO.tipoMeioPagamento(),
                condutorService.findCondutorById(meioPagamentoCondutorDTO.emailCondutor())
        );
    }

    public MeioPagamentoCondutorDTO toMeioPagamentoDTO(MeioPagamentoCondutor meioPagamentoCondutor) {
        return new MeioPagamentoCondutorDTO(
                meioPagamentoCondutor.getId(),
                meioPagamentoCondutor.getTipoMeioPagamento(),
                meioPagamentoCondutor.getCondutor().getEmail()
        );
    }
}