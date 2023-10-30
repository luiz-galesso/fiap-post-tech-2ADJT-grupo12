package com.tech.challenge.inscricao.gestaovaga.controller;

import com.tech.challenge.inscricao.gestaovaga.dto.AprovaSolicitacaoDTO;
import com.tech.challenge.inscricao.gestaovaga.dto.ReprovaSolicitacaoDTO;
import com.tech.challenge.inscricao.gestaovaga.dto.SolicitaVagaDTO;
import com.tech.challenge.inscricao.gestaovaga.dto.SolicitaVagaRespostaDTO;
import com.tech.challenge.inscricao.gestaovaga.entity.SolicitaVaga;
import com.tech.challenge.inscricao.gestaovaga.entity.Vaga;
import com.tech.challenge.inscricao.gestaovaga.enumeration.Nivel;
import com.tech.challenge.inscricao.gestaovaga.service.SolicitaVagaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;

/**
 * @author thiago-ribeiro
 */
@RestController
@RequestMapping("/solicitavaga")
public class SolicitaVagaController
{
    @Autowired
    private SolicitaVagaService solicitaVagaService;

    /**
     * encontra solicitações utilizando
     * um exemplo da classe SolicitaVaga
     * Os parametros não são obrigatórios.
     * Caso não seja passado, retorna todos.
     * @param idSolicitante cpf
     * @param nivel enum Nivel
     * @param idAvaliador cpf
     * @return List<SolicitaVaga>
     */
    @GetMapping
    public ResponseEntity<?> getSolicitacoes(
        @RequestParam(required = false) String idSolicitante,
        @RequestParam(required = false) Nivel nivel,
        @RequestParam(required = false) String idAvaliador,
        @RequestParam(required = false) Boolean isAprovado)

    {
        return ResponseEntity.ok(solicitaVagaService.findByExample(idSolicitante, nivel, idAvaliador, isAprovado));
    }

    /**
     * cria uma solicitaão para a vaga
     * essa solicitação deverá ser exibida nos perfis
     * responsáveis por aprovar a vaga
     * @param solicitaVagaDTO
     * @return
     */
    @PostMapping
    public ResponseEntity<?> solicitaVaga(@RequestBody SolicitaVagaDTO solicitaVagaDTO)
    {
        try
        {
            SolicitaVaga solicitaVaga = solicitaVagaService.solicitaVaga(solicitaVagaDTO);
            return ResponseEntity.ok(
                new SolicitaVagaRespostaDTO(
                        solicitaVaga.getTitulo(),
                        solicitaVaga.getDescricao(),
                        solicitaVaga.getQuantidadeDeVagas(),
                        solicitaVaga.getDataSolicitacao(),
                        solicitaVaga.getNivel()
                ));
        }
        catch (Exception e)
        {
            System.out.println("Erro ao salvar solicitação de vaga: " + e.getMessage());
            return ResponseEntity.internalServerError().body("Erro ao salvar a solicitação");
        }
    }

    /*** ============================================================================================================ ***/

    /**
     *
     * @param aprovaSolicitacaoDTO
     * @return
     */
    @PostMapping("/aprovar")
    public ResponseEntity<?> aprovaSolicitacao(@RequestBody AprovaSolicitacaoDTO aprovaSolicitacaoDTO)
    {
        try
        {
            Vaga vaga = solicitaVagaService.aprovaSolicitacao(aprovaSolicitacaoDTO.idSolicitacao(), aprovaSolicitacaoDTO.idAprovador());

            return ResponseEntity.ok(vaga);
        }
        catch (Exception e)
        {
            System.out.println("Erro ao aprovar solicitação: " + e.getMessage());
            return ResponseEntity.internalServerError().body("'message': 'A vaga não foi aprovada devido a um erro de sistema.'");
        }
    }

    @PostMapping("/reprovar")
    public ResponseEntity<?> reprovaSolicitacao(@RequestBody ReprovaSolicitacaoDTO reprovaSolicitacaoDTO)
    {
        try
        {
            solicitaVagaService.reprovaSolicitacao(reprovaSolicitacaoDTO.idSolicitacao(),
                                                   reprovaSolicitacaoDTO.idAprovador(),
                                                   reprovaSolicitacaoDTO.mensagem());

            HashMap<String, String> resposta = new HashMap<>();
            resposta.put("message", "Vaga reprovada com sucesso.");
            return ResponseEntity.ok(resposta);
        }
        catch (Exception e)
        {
            System.out.println("Erro ao reprovar solicitação: " + e.getMessage());
            return ResponseEntity.internalServerError().body("'message': 'A vaga não foi alterada devido a um erro de sistema.'");
        }
    }
}
