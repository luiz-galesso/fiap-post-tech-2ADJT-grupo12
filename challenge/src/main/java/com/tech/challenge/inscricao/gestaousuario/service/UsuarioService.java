package com.tech.challenge.inscricao.gestaousuario.service;


import com.tech.challenge.inscricao.gestaocandidatura.controller.exception.EntityFoundException;
import com.tech.challenge.inscricao.gestaocandidatura.entity.Candidatura;
import com.tech.challenge.inscricao.gestaoperfil.service.PerfilService;
import com.tech.challenge.inscricao.gestaousuario.entity.Perfil;
import com.tech.challenge.inscricao.gestaousuario.controller.exception.ControllerNotFoundException;
import com.tech.challenge.inscricao.gestaousuario.dto.*;
import com.tech.challenge.inscricao.gestaousuario.entity.DadosPessoais;
import com.tech.challenge.inscricao.gestaousuario.entity.Usuario;
import com.tech.challenge.inscricao.gestaousuario.repository.UsuarioRepository;
import com.tech.challenge.util.StringUtils;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private PerfilService perfilService;

    public UsuarioDTO save(UsuarioDTO usuarioDTO) {
        Usuario usuario = toEntity(usuarioDTO);
        perfilService.verificaEntidade(usuario);
        validaSeJaCadastrado(usuario);
        usuario = usuarioRepository.save(usuario);
        return toUsuarioDTO(usuario);
    }

    public UsuarioDTO update(String id, AtualizarUsuarioDTO usuarioDTO) {
        try {
            id = StringUtils.removeMascara(id);
            Usuario usuario = usuarioRepository.getReferenceById(id);
            usuario.setDadosPessoais(new DadosPessoais(usuarioDTO.dadosPessoais()));
            usuario.setPerfil(new Perfil(usuarioDTO.perfil()));
            usuario.setNomeUsuario(usuarioDTO.nomeUsuario());
            usuario.setNome(usuarioDTO.nomeCompleto());
            usuario = usuarioRepository.save(usuario);

            return toUsuarioDTO(usuario);
        } catch (EntityNotFoundException e) {
            throw new ControllerNotFoundException("Usuário não localizado");
        }
    }


    public void delete(String id) {
        try {
            usuarioRepository.deleteById(StringUtils.removeMascara(id));
        } catch (EntityNotFoundException e) {
            throw new ControllerNotFoundException("Usuário não localizado");
        }
    }

    public Usuario findById(String id) {
        try {
            return usuarioRepository.findById(StringUtils.removeMascara(id)).orElseThrow(() -> new ControllerNotFoundException(
                   /* this.getClass().toString()
                    .replace(getClass().getPackageName(),"")
                    .replace(".","")
                    .replace("class","")
                    .replace("Service","")
                    + " não localizado")*/
                    "Usuário não localizado"
            ));


        } catch (EntityNotFoundException e) {
            throw new ControllerNotFoundException("Usuário não localizado");
        }
    }

    public void validaSeJaCadastrado(Usuario usuario){
        Usuario usuarioLocal = findById(usuario.getCpf());
        if(usuarioLocal != null){
            throw new EntityFoundException("Já cadastrado!");
        }
    }


    private Usuario toEntity(UsuarioDTO usuarioDTO) {
        return new Usuario(
                StringUtils.removeMascara(usuarioDTO.cpf()),
                usuarioDTO.nomeUsuario(),
                usuarioDTO.nomeCompleto(),
                new DadosPessoais(usuarioDTO.dadosPessoais()),
                new Perfil(usuarioDTO.perfil())

        );
    }

    private UsuarioDTO toUsuarioDTO(Usuario usuario) {

        return new UsuarioDTO(
                usuario.getNomeUsuario(),
                usuario.getCpf().toString(),
                usuario.getNome(),
                new DadosPessoaisDTO(usuario.getDadosPessoais().getEstadoCivil(),
                        usuario.getDadosPessoais().getGenero(),
                        usuario.getDadosPessoais().getDataNascimento(),
                        usuario.getDadosPessoais().getCelular(),
                        usuario.getDadosPessoais().getNaturalidade(),
                        new FiliacaoDTO(usuario.getDadosPessoais().getFiliacao().getNomeMae(),
                                usuario.getDadosPessoais().getFiliacao().getNomePai()),
                        new EnderecoDTO(usuario.getDadosPessoais().getEndereco().getCep(),
                                usuario.getDadosPessoais().getEndereco().getNumero(),
                                usuario.getDadosPessoais().getEndereco().getComplemento()))
                , new PerfilDTO(usuario.getPerfil().getId())
        );
    }
}
