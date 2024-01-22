package com.fase2.techchallenge.fiap.pagamento.gestaorecibo.service;

import com.fase2.techchallenge.fiap.pagamento.gestaopagamento.service.PagamentoService;
import com.fase2.techchallenge.fiap.pagamento.gestaorecibo.dto.SolicitacaoReciboDTO;
import com.fase2.techchallenge.fiap.pagamento.gestaorecibo.entity.DadosPagamento;
import com.fase2.techchallenge.fiap.pagamento.gestaorecibo.entity.Recibo;
import com.fase2.techchallenge.fiap.pagamento.gestaorecibo.enumeration.ReciboSituacao;
import com.fase2.techchallenge.fiap.pagamento.gestaorecibo.feign.CadastroClient;
import com.fase2.techchallenge.fiap.pagamento.gestaorecibo.repository.ReciboRepository;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.stereotype.Service;

@Service
@EnableScheduling
public class ReciboService {

    final
    PagamentoService pagamentoService;
    final
    CadastroClient cadastroClient;
    private final ReciboRepository reciboRepository;

    public ReciboService(ReciboRepository reciboRepository, PagamentoService pagamentoService, CadastroClient cadastroClient) {
        this.reciboRepository = reciboRepository;
        this.pagamentoService = pagamentoService;
        this.cadastroClient = cadastroClient;
    }

    private Recibo toEntity(SolicitacaoReciboDTO solicitacaoReciboDTO) {
        return new Recibo(new DadosPagamento(solicitacaoReciboDTO.idPagamento()));
    }

    private SolicitacaoReciboDTO toReciboDTO(Recibo recibo) {
        return new SolicitacaoReciboDTO(
                recibo.getId(),
                recibo.getPagamento().getIdPagamento(),
                recibo.getReciboSituacao().toString()
        );
    }

    public SolicitacaoReciboDTO gerarRecibo(SolicitacaoReciboDTO solicitacaoReciboDTO) {

        Recibo recibo = toEntity(solicitacaoReciboDTO);
        System.out.println(recibo.getPagamento());
        recibo.setReciboSituacao(ReciboSituacao.SOLICITADO);

        //  recibo.setDataImpressaoRecibo(Date.from(Instant.now()));
        recibo = reciboRepository.save(recibo);
        return toReciboDTO(recibo);
    }

    /*@Scheduled(fixedDelay = 1000)
    public void preencherDetalhes() {
        List<Recibo> recibos = reciboRepository.findByReciboSituacao(ReciboSituacao.SOLICITADO);
        for (Recibo recibo : recibos) {
            Pagamento pagamento = pagamentoService.findById(recibo.getPagamento().getIdPagamento());
            recibo.getPagamento().setValor(pagamento.getValor());
            recibo.getPagamento().setDataHoraPagamento(pagamento.getDataPagamento());

           // if (recibo.getDadosCondutor().getNomeCondutor() == null) {
                //recibo.setDadosCondutor(new DadosCondutor(cadastroClient.getCondutor(pagamento.getIdCondutor())));
            //}

            recibo.setReciboSituacao(ReciboSituacao.GERADO);
            reciboRepository.save(recibo);

        }

    }*/

}
