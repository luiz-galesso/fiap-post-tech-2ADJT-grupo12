package com.tech.challenge.inscricao.gestaovaga.service;

import com.tech.challenge.acesso.gestaoperfil.entity.Perfil;
import com.tech.challenge.acesso.gestaoperfil.service.PerfilService;
import com.tech.challenge.acesso.gestaousuario.entity.Usuario;
import com.tech.challenge.acesso.gestaousuario.service.UsuarioService;
import com.tech.challenge.inscricao.gestaovaga.service.filtros.SolicitacaoVagaFiltro;
import com.tech.challenge.inscricao.gestaovaga.dto.SolicitacaoVagaDTO;
import com.tech.challenge.inscricao.gestaovaga.entity.SolicitacaoVaga;
import com.tech.challenge.inscricao.gestaovaga.entity.Vaga;
import com.tech.challenge.inscricao.gestaovaga.enumeration.Nivel;
import com.tech.challenge.inscricao.gestaovaga.enumeration.SolicitacaoSituacao;
import com.tech.challenge.inscricao.gestaovaga.repository.SolicitacaoVagaRepository;
import com.tech.challenge.util.StringUtils;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class SolicitacaoVagaService
{
    @Autowired
    private SolicitacaoVagaRepository solicitacaoVagaRepository;

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private PerfilService perfilService;
    
    @Autowired
    private VagaService vagaService;

    /**
     *
     * @param solicitacaoDTO
     * @return
     */
    public SolicitacaoVaga criarSolicitacaoVaga(SolicitacaoVagaDTO solicitacaoDTO)
    {
        SolicitacaoVaga solicitacao = toEntity(solicitacaoDTO);
        //verificar perfil
        Usuario usuario = usuarioService.findById(
                StringUtils.removeMascara(solicitacao.getSolicitante().getCpf()));
        Perfil perfil = perfilService.findById(usuario.getPerfil().getId());
        if(perfil.getDescricao().equals("CANDIDATO")){
            throw new EntityNotFoundException("Perfil não autorizado");
        }
        return solicitacaoVagaRepository.save(solicitacao);

    }

    /**
     * aprova solicitacao
     * @param idSolicitacao cpf
     * @param idAprovador cpf
     */
    public Vaga aprovaSolicitacao(Integer idSolicitacao, String idAprovador)
    {
        SolicitacaoVaga solicitacao = findById(idSolicitacao);

        if(solicitacao == null)  throw new EntityNotFoundException();

        //aprova e cria vaga
        solicitacao.setAvaliador(new Usuario(idAprovador));
        solicitacao.setDataAvaliacao(new Date());
        solicitacao.setSituacao(SolicitacaoSituacao.valueOf("APROVADA"));
        return vagaService.criarVaga(new Vaga(solicitacao));
    }

    /**
     * reprova solicitação
     * -- validar data de expiracao
     * @param idSolicitacao
     * @param idAprovador
     */
    public void reprovaSolicitacao(Integer idSolicitacao, String idAprovador, String mensagem)
    {
        SolicitacaoVaga solicitacao = findById(idSolicitacao);

        if(solicitacao == null)  throw new EntityNotFoundException();

        //reprova
        solicitacao.setAvaliador(new Usuario(idAprovador));
        solicitacao.setDataAvaliacao(new Date());
        solicitacao.setSituacao(SolicitacaoSituacao.valueOf("REPROVADA"));
        solicitacao.setMensagem(mensagem);
        solicitacaoVagaRepository.save(solicitacao);
    }

    /**
     * todos
     * @return
     */
    public List<SolicitacaoVaga> findAll()
    {
        return solicitacaoVagaRepository.findAll();
    }

    /**
     *
     * @param idSolicitante
     * @param nivel
     * @param idAvaliador
     * @return
     */
    public List<SolicitacaoVaga> findByFiltro(String idSolicitante, String nivel, String idAvaliador, String situacao)
    {
       SolicitacaoVagaFiltro filtro = new SolicitacaoVagaFiltro(idSolicitante, idAvaliador, nivel, situacao);
       return solicitacaoVagaRepository.findAll(filtro);
    }

    /**
     *
     * @param id
     * @return
     */
    public SolicitacaoVaga findById(Integer id)
    {
        return solicitacaoVagaRepository.findById(id).orElse(null);
    }

    /**
     *
     * @param solicitacaoVagaDTO
     * @return SolicitaVaga
     */
    public SolicitacaoVaga toEntity(SolicitacaoVagaDTO solicitacaoVagaDTO)
    {
        return new SolicitacaoVaga(
                solicitacaoVagaDTO.titulo(),
                solicitacaoVagaDTO.descricao(),
                solicitacaoVagaDTO.quantidade(),
                StringUtils.removeMascara(solicitacaoVagaDTO.idSolicitante()),
                Nivel.valueOf(solicitacaoVagaDTO.nivel()),
                solicitacaoVagaDTO.dataExpiracao(),
                solicitacaoVagaDTO.carreira(),
                SolicitacaoSituacao.valueOf("ABERTA")
        );
    }

    public SolicitacaoVagaDTO toSolicitaVagaDTO(SolicitacaoVaga solicitacaoVaga){
        return new SolicitacaoVagaDTO(solicitacaoVaga.getTitulo(),
                solicitacaoVaga.getDescricao(),
                solicitacaoVaga.getQuantidadeDeVagas(),
                solicitacaoVaga.getSolicitante().getCpf(),
                solicitacaoVaga.getNivel().toString(),
                solicitacaoVaga.getDataExpiracao(),
                solicitacaoVaga.getCarreira().getId()
        );
    }
}
