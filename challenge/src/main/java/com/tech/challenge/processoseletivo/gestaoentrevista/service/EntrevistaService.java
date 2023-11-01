package com.tech.challenge.processoseletivo.gestaoentrevista.service;

import com.tech.challenge.processoseletivo.gestaoetapa.service.EtapaService;
import com.tech.challenge.exception.ControllerNotFoundException;
import com.tech.challenge.acesso.gestaoperfil.entity.Perfil;
import com.tech.challenge.acesso.gestaoperfil.service.PerfilService;
import com.tech.challenge.acesso.gestaousuario.entity.Usuario;
import com.tech.challenge.acesso.gestaousuario.service.UsuarioService;
import com.tech.challenge.inscricao.gestaovaga.service.VagaService;
import com.tech.challenge.processoseletivo.gestaoentrevista.dto.EntrevistaRequestDTO;
import com.tech.challenge.processoseletivo.gestaoentrevista.dto.EntrevistaResponseDTO;
import com.tech.challenge.processoseletivo.gestaoentrevista.entity.Entrevista;
import com.tech.challenge.processoseletivo.gestaoentrevista.repository.EntrevistaRepository;
import com.tech.challenge.util.StringUtils;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.stream.Collectors;

@Service
public class EntrevistaService {

    private final EntrevistaRepository entrevistaRepository;
    private final UsuarioService usuarioService;
    private final PerfilService perfilService;
    private final VagaService vagaService;
    private final EtapaService etapaService;

    @Autowired
    public EntrevistaService(EntrevistaRepository entrevistaRepository, UsuarioService usuarioService, PerfilService perfilService, VagaService vagaService, EtapaService etapaService) {
        this.entrevistaRepository = entrevistaRepository;
        this.usuarioService = usuarioService;
        this.perfilService = perfilService;
        this.vagaService = vagaService;
        this.etapaService = etapaService;
    }

    public Collection<EntrevistaResponseDTO> findAll() {
        var entrevistas = entrevistaRepository.findAll();
        return entrevistas
                .stream()
                .map(this::toEntrevistaResponseDTO)
                .collect(Collectors.toList());
    }

    public EntrevistaResponseDTO findById(Long id) {
        var entrevista = entrevistaRepository.findById(id).orElseThrow(() -> new ControllerNotFoundException("Entrevista não encontrada"));
        return toEntrevistaResponseDTO(entrevista);
    }

    public EntrevistaResponseDTO findByUsuario(String idUsuario, String tipoPerfil) {
        try {
            var entrevista = new Entrevista();
            Usuario usuario = usuarioService.findById(StringUtils.removeMascara(idUsuario));
            Perfil perfil = perfilService.findById(usuario.getPerfil().getId());

            if (perfil.getDescricao().toUpperCase().equals("CANDIDATO") && tipoPerfil.toUpperCase().equals("CANDIDATO")) {
                entrevista = entrevistaRepository.findByCandidato(usuario).orElseThrow(() -> new ControllerNotFoundException("Candidato sem Entrevista"));
            } else if (!perfil.getDescricao().toUpperCase().equals("CANDIDATO") && tipoPerfil.toUpperCase().equals("ENTREVISTADOR")) {
                entrevista = entrevistaRepository.findByEntrevistador(usuario).orElseThrow(() -> new ControllerNotFoundException("Entrevistador sem Entrevista"));
            }

            return toEntrevistaResponseDTO(entrevista);
        } catch (EntityNotFoundException e) {
            throw new ControllerNotFoundException("Usuario não encontrado");
        } catch (NullPointerException e) {
            throw new ControllerNotFoundException("Usuario com perfil Invalido");
        }
    }

    public EntrevistaResponseDTO save(EntrevistaRequestDTO entrevistaDTO) {
        Entrevista entrevista = toEntrevista(entrevistaDTO);
        entrevista = entrevistaRepository.save(entrevista);
        return toEntrevistaResponseDTO(entrevista);
    }

    public EntrevistaResponseDTO update(Long id, EntrevistaRequestDTO entrevistaDTO) {
        try {
            Entrevista entrevista = entrevistaRepository.getReferenceById(id);
            entrevista.setEntrevistador(usuarioService.findById(StringUtils.removeMascara(entrevistaDTO.entrevistador())));
            entrevista.setDataEntrevista(entrevistaDTO.dataEntrevista());
            entrevista.setLocal(entrevistaDTO.local());
            entrevista.setCandidato(usuarioService.findById(StringUtils.removeMascara(entrevistaDTO.candidato())));
            entrevista = entrevistaRepository.save(entrevista);

            return toEntrevistaResponseDTO(entrevista);
        } catch (EntityNotFoundException e) {
            throw new ControllerNotFoundException("Entrevista não encontrada");
        }
    }

    public void delete(Long id) {
        entrevistaRepository.deleteById(id);
    }

    private EntrevistaResponseDTO toEntrevistaResponseDTO(Entrevista entrevista) {
        return new EntrevistaResponseDTO(entrevista.getId(),
                entrevista.getEntrevistador().getNome(),
                entrevista.getDataEntrevista(),
                entrevista.getLocal(),
                entrevista.getCandidato().getNome(),
                entrevista.getVaga().getTitulo(),
                entrevista.getEtapa().getDescricao());
    }

    private Entrevista toEntrevista(EntrevistaRequestDTO entrevistaDTO) {
        return new Entrevista(entrevistaDTO.id(),
                entrevistaDTO.local(),
                entrevistaDTO.dataEntrevista(),
                usuarioService.findById(StringUtils.removeMascara(entrevistaDTO.candidato())),
                usuarioService.findById(StringUtils.removeMascara(entrevistaDTO.entrevistador())),
                vagaService.findById(entrevistaDTO.vaga()),
                etapaService.findById(entrevistaDTO.etapa()));
    }
}
