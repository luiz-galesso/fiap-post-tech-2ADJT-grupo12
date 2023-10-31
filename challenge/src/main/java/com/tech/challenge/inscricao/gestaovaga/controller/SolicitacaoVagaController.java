package com.tech.challenge.inscricao.gestaovaga.controller;

import com.tech.challenge.inscricao.gestaovaga.dto.AprovaSolicitacaoDTO;
import com.tech.challenge.inscricao.gestaovaga.dto.ReprovaSolicitacaoDTO;
import com.tech.challenge.inscricao.gestaovaga.dto.SolicitacaoVagaDTO;
import com.tech.challenge.inscricao.gestaovaga.dto.SolicitacaoVagaRespostaDTO;
import com.tech.challenge.inscricao.gestaovaga.entity.SolicitacaoVaga;
import com.tech.challenge.inscricao.gestaovaga.entity.Vaga;
import com.tech.challenge.inscricao.gestaovaga.enumeration.Nivel;
import com.tech.challenge.inscricao.gestaovaga.enumeration.SolicitacaoSituacao;
import com.tech.challenge.inscricao.gestaovaga.service.SolicitacaoVagaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;

/**
 * @author thiago-ribeiro
 */
@RestController
@RequestMapping("/solicitacaovaga")
public class SolicitacaoVagaController
{
    @Autowired
    private SolicitacaoVagaService solicitacaoVagaService;

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
        @RequestParam(required = false) String nivel,
        @RequestParam(required = false) String idAvaliador,
        @RequestParam(required = false) String situacao)

    {
        return ResponseEntity.ok(solicitacaoVagaService.findByFiltro(idSolicitante, nivel, idAvaliador, situacao));
    }

    /**
     * cria uma solicitaão para a vaga
     * essa solicitação deverá ser exibida nos perfis
     * responsáveis por aprovar a vaga
     * @param solicitacaoVagaDTO
     * @return
     */
    @PostMapping
    public ResponseEntity<?> solicitacaoVaga(@RequestBody SolicitacaoVagaDTO solicitacaoVagaDTO)
    {
        try
        {
            SolicitacaoVaga solicitacaoVaga = solicitacaoVagaService.criarSolicitacaoVaga(solicitacaoVagaDTO);
            return ResponseEntity.ok(
                new SolicitacaoVagaRespostaDTO(
                        solicitacaoVaga.getId()
                ));
        }
        catch (Exception e)
        {
            return ResponseEntity.internalServerError().body("Não foi possível inserir a solicitação de vaga");
        }
    }

    /*** ============================================================================================================ ***/

    /**
     *
     * @param aprovaSolicitacaoDTO
     * @return
     */
    @PutMapping("{idSolicitacao}/aprovar")
    public ResponseEntity<?> aprovaSolicitacao(@PathVariable Integer idSolicitacao, @RequestBody AprovaSolicitacaoDTO aprovaSolicitacaoDTO)
    {
        try
        {
            Vaga vaga = solicitacaoVagaService.aprovaSolicitacao(idSolicitacao, aprovaSolicitacaoDTO.idAprovador());

            return ResponseEntity.ok(vaga);
        }
        catch (Exception e)
        {
            System.out.println("Erro ao aprovar solicitação: " + e.getMessage());
            return ResponseEntity.internalServerError().body("'message': 'A vaga não foi aprovada devido a um erro de sistema.'");
        }
    }

    @PutMapping("{idSolicitacao}/reprovar")
    public ResponseEntity<?> reprovaSolicitacao(@PathVariable Integer idSolicitacao, @RequestBody ReprovaSolicitacaoDTO reprovaSolicitacaoDTO)
    {
        try
        {
            solicitacaoVagaService.reprovaSolicitacao(idSolicitacao,
                                                   reprovaSolicitacaoDTO.idAprovador(),
                                                   reprovaSolicitacaoDTO.mensagem());

            HashMap<String, String> resposta = new HashMap<>();
            resposta.put("message", "Vaga reprovada com sucesso.");
            return ResponseEntity.ok(resposta);
        }
        catch (Exception e)
        {
            return ResponseEntity.internalServerError().body("'message': 'A vaga não foi alterada devido a um erro de sistema.'");
        }
    }
}
