package com.fase2.techchallenge.fiap.pagamento.gestaopagamento.service;

import com.fase2.techchallenge.fiap.pagamento.gestaopagamento.controller.exception.ControllerNotFoundException;
import com.fase2.techchallenge.fiap.pagamento.gestaopagamento.controller.exception.EntityFoundException;
import com.fase2.techchallenge.fiap.pagamento.gestaopagamento.dto.SolicitacaoReciboDTO;
import com.fase2.techchallenge.fiap.pagamento.gestaopagamento.entity.MeioPagamento;
import com.fase2.techchallenge.fiap.pagamento.gestaopagamento.repository.MeioPagamentoRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MeioPagamentoService {

    @Autowired
    private MeioPagamentoRepository meioPagamentoRepository;

    public SolicitacaoReciboDTO save(SolicitacaoReciboDTO solicitacaoReciboDTO) {
        MeioPagamento meioPagamento = toEntity(solicitacaoReciboDTO);
        meioPagamentoExistente(meioPagamento);
        meioPagamento = meioPagamentoRepository.save(meioPagamento);
        return toMeioPagamentoDTO(meioPagamento);
    }

    public SolicitacaoReciboDTO update(String id, SolicitacaoReciboDTO solicitacaoReciboDTO) {
        try {
            MeioPagamento meioPagamento = meioPagamentoRepository.getReferenceById(id);
            meioPagamento.setDescricao(solicitacaoReciboDTO.descricao());
            meioPagamento.setSituacao(solicitacaoReciboDTO.situacao());
            meioPagamento = meioPagamentoRepository.save(meioPagamento);

            return toMeioPagamentoDTO(meioPagamento);
        } catch (EntityNotFoundException e) {
            throw new ControllerNotFoundException("Meio de pagamento não localizado");
        }
    }

    public void delete(String id) {
        try {
            meioPagamentoRepository.deleteById(id);
        } catch (EntityNotFoundException e) {
            /*TODO VERIFICAR SE ESTOURA ESSA EXCEPTION */
            throw new ControllerNotFoundException("Meio de pagamento não localizado");
        }
    }

    public SolicitacaoReciboDTO findById(String id) {
        try {
            return toMeioPagamentoDTO(meioPagamentoRepository.findById(id).orElseThrow(() -> new ControllerNotFoundException("Meio de pagamento não localizado")));
        } catch (EntityNotFoundException e) {
            /*TODO VERIFICAR SE ESTOURA ESSA EXCEPTION */
            throw new ControllerNotFoundException("Meio de pagamento não localizado");
        }
    }

    public List<MeioPagamento> findAll() {
        try {
            return meioPagamentoRepository.findAll();
        } catch (EntityNotFoundException e) {
            throw new ControllerNotFoundException("Não existem usuários");
        }
    }

    public void meioPagamentoExistente(MeioPagamento meioPagamento){
        Optional<MeioPagamento> meioPagamentoLocal = meioPagamentoRepository.findById(meioPagamento.getId());
        if(meioPagamentoLocal.isPresent()){
            throw new EntityFoundException("Meio de pagamento já cadastrado!");
        }
    }

    private MeioPagamento toEntity(SolicitacaoReciboDTO solicitacaoReciboDTO) {
        return new MeioPagamento(
                solicitacaoReciboDTO.id(),
                solicitacaoReciboDTO.descricao(),
                solicitacaoReciboDTO.situacao()
        );
    }

    private SolicitacaoReciboDTO toMeioPagamentoDTO(MeioPagamento meioPagamento) {
        return new SolicitacaoReciboDTO(
                meioPagamento.getId(),
                meioPagamento.getDescricao(),
                meioPagamento.getSituacao()
        );
    }
}
