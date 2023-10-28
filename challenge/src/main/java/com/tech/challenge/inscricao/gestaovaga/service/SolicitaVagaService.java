package com.tech.challenge.inscricao.gestaovaga.service;

import com.tech.challenge.inscricao.gestaoperfil.entity.Perfil;
import com.tech.challenge.inscricao.gestaoperfil.service.PerfilService;
import com.tech.challenge.inscricao.gestaousuario.entity.Usuario;
import com.tech.challenge.inscricao.gestaousuario.service.UsuarioService;
import com.tech.challenge.inscricao.gestaovaga.dto.SolicitaVagaDTO;
import com.tech.challenge.inscricao.gestaovaga.entity.SolicitaVaga;
import com.tech.challenge.inscricao.gestaovaga.entity.Vaga;
import com.tech.challenge.inscricao.gestaovaga.enumeration.Nivel;
import com.tech.challenge.inscricao.gestaovaga.repository.SolicitaVagaRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class SolicitaVagaService
{
    @Autowired
    private SolicitaVagaRepository solicitaVagaRepository;

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
    public SolicitaVaga solicitaVaga(SolicitaVagaDTO solicitacaoDTO)
    {
        SolicitaVaga solicitacao = toEntity(solicitacaoDTO);

        //verificar perfil
        Usuario usuario = usuarioService.findById(solicitacao.getSolicitante().getCpf());
        Perfil perfil = perfilService.findById(usuario.getPerfil().getId());
        if(perfil.getDescricao().equals("CANDIDATO")){
            throw new EntityNotFoundException("Perfil não autorizado");
        }

        return solicitaVagaRepository.save(solicitacao);

    }

    /**
     * aprova solicitacao
     * @param idSolicitacao
     * @param idAprovador
     */
    public Vaga aprovaSolicitacao(Integer idSolicitacao, String idAprovador)
    {
        SolicitaVaga solicitacao = findById(idSolicitacao);

        if(solicitacao == null)  throw new EntityNotFoundException();

        //aprova e cria vaga
        solicitacao.setAvaliador(new Usuario(idAprovador));
        solicitacao.setDataAvaliado(new Date());
        solicitacao.setAprovado(true);
        return vagaService.criarVaga(new Vaga(solicitacao));
    }

    /**
     * reprova solicitação
     * -- validar data de expiracao
     * @param idSolicitacao
     * @param idAprovador
     */
    public boolean reprovaSolicitacao(Integer idSolicitacao, String idAprovador)
    {
        SolicitaVaga solicitacao = findById(idSolicitacao);

        if(solicitacao == null)  throw new EntityNotFoundException();

        //reprova
        solicitacao.setAvaliador(new Usuario(idAprovador));
        solicitacao.setDataAvaliado(new Date());
        solicitacao.setAprovado(false);
        solicitaVagaRepository.save(solicitacao);
        return true;
    }

    /**
     * todos
     * @return
     */
    public List<SolicitaVaga> findAll()
    {
        return solicitaVagaRepository.findAll();
    }

    /**
     *
     * @param idSolicitante
     * @param nivel
     * @param idAvaliador
     * @return
     */
    public List<SolicitaVaga> findByExample(String idSolicitante, Nivel nivel, String idAvaliador, boolean isAprovado)
    {
        SolicitaVaga solicitaVaga = new SolicitaVaga(idSolicitante, nivel, idAvaliador, isAprovado);
        System.out.println(solicitaVaga.toString());
        return solicitaVagaRepository.findAll(Example.of(solicitaVaga));
    }

    public SolicitaVaga findById(Integer id)
    {
        return solicitaVagaRepository.findById(id).orElse(null);
    }

    /**
     *
     * @param solicitaVagaDTO
     * @return SolicitaVaga
     */
    public SolicitaVaga toEntity(SolicitaVagaDTO solicitaVagaDTO)
    {
        return new SolicitaVaga(
                solicitaVagaDTO.titulo(),
                solicitaVagaDTO.descricao(),
                solicitaVagaDTO.quantidade(),
                solicitaVagaDTO.idSolicitante(),
                Nivel.valueOf(solicitaVagaDTO.nivel())
        );
    }

    public SolicitaVagaDTO toSolicitaVagaDTO(SolicitaVaga solicitaVaga){
        return new SolicitaVagaDTO(solicitaVaga.getTitulo(),
                solicitaVaga.getDescricao(),
                solicitaVaga.getQuantidadeDeVagas(),
                solicitaVaga.getSolicitante().getCpf(),
                solicitaVaga.getNivel().toString()
        );
    }
}
