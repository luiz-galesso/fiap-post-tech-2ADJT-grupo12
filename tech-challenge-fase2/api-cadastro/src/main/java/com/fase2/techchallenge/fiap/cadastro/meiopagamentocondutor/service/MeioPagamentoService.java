package com.fase2.techchallenge.fiap.cadastro.meiopagamentocondutor.service;

import com.fase2.techchallenge.fiap.cadastro.exception.ControllerNotFoundException;
import com.fase2.techchallenge.fiap.cadastro.meiopagamentocondutor.dto.MeioPagamentoDTO;
import com.fase2.techchallenge.fiap.cadastro.meiopagamentocondutor.entity.MeioPagamentoCondutor;
import com.fase2.techchallenge.fiap.cadastro.meiopagamentocondutor.feign.MeioPagamentoClient;
import com.fase2.techchallenge.fiap.cadastro.meiopagamentocondutor.repository.MeioPagamentoCondutorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MeioPagamentoService {

    @Autowired
    private MeioPagamentoClient meioPagamentoClient;
    @Autowired
    private MeioPagamentoCondutorRepository meioPagamentoCondutorRepository;


    public MeioPagamentoDTO findByIdMeioPagamento(String id) {
        try {
            MeioPagamentoDTO meioPagamentoDTO = meioPagamentoClient.findByIdMeioPagamento(id).getBody();
            return meioPagamentoDTO;
        } catch (RuntimeException e) {
            throw new ControllerNotFoundException("Erro ao validar Meio de Pagamento.");
        }
    }

}
