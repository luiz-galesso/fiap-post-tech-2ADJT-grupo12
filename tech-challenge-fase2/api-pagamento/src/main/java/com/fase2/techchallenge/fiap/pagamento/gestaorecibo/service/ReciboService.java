package com.fase2.techchallenge.fiap.pagamento.gestaorecibo.service;

import com.fase2.techchallenge.fiap.pagamento.gestaorecibo.dto.SolicitacaoReciboDTO;
import com.fase2.techchallenge.fiap.pagamento.gestaorecibo.entity.Recibo;
import com.fase2.techchallenge.fiap.pagamento.gestaorecibo.enumeration.ReciboSituacao;
import com.fase2.techchallenge.fiap.pagamento.gestaorecibo.repository.ReciboRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ReciboService {

    @Autowired
    private ReciboRepository reciboRepository;

    private Recibo toEntity(SolicitacaoReciboDTO solicitacaoReciboDTO) {
        return new Recibo(solicitacaoReciboDTO.idPagamento()
        );

    }

    private SolicitacaoReciboDTO toReciboDTO(Recibo recibo) {
        return new SolicitacaoReciboDTO(
                recibo.getId(),
                recibo.getIdPagamento(),
                recibo.getReciboSituacao().toString()
        );
    }

    public SolicitacaoReciboDTO gerarRecibo(SolicitacaoReciboDTO solicitacaoReciboDTO) {

        Recibo recibo = toEntity(solicitacaoReciboDTO);
        recibo.setReciboSituacao(ReciboSituacao.valueOf("SOLICITADO"));
      //  recibo.setDataImpressaoRecibo(Date.from(Instant.now()));
        recibo = reciboRepository.save(recibo);
        return toReciboDTO(recibo);
    }

}
