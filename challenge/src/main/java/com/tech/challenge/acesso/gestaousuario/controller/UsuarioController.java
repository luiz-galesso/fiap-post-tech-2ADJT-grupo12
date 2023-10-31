package com.tech.challenge.acesso.gestaousuario.controller;

import com.tech.challenge.acesso.gestaoperfil.entity.Perfil;
import com.tech.challenge.acesso.gestaousuario.dto.AtualizarUsuarioDTO;
import com.tech.challenge.acesso.gestaousuario.dto.UsuarioDTO;
import com.tech.challenge.acesso.gestaousuario.entity.Usuario;
import com.tech.challenge.acesso.gestaousuario.service.UsuarioService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;


@RestController
@RequestMapping("/usuarios")
public class UsuarioController {
    //desde o spring boot 2.3, o uso do Autowired eh opcional
    @Autowired
    private UsuarioService usuarioService;

    @PostMapping// @Valid
    public ResponseEntity<UsuarioDTO> save(@RequestBody @Valid UsuarioDTO UsuarioDTO) {
        UsuarioDTO usuarioDTO = usuarioService.save(UsuarioDTO);
        return new ResponseEntity<>(usuarioDTO, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<UsuarioDTO> update(@PathVariable String id, @RequestBody AtualizarUsuarioDTO atualizarUsuarioDTO) {
        UsuarioDTO candidatoAtualizado = usuarioService.update(id, atualizarUsuarioDTO);
        return ResponseEntity.ok(candidatoAtualizado);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable String id) {
        usuarioService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Usuario> findById(@PathVariable("id") String id) {
        var usuario = usuarioService.findById(id);
        return ResponseEntity.ok(usuario);
    }

    @GetMapping
    public ResponseEntity<Collection<Usuario>> findAll() {
        var usuarios = usuarioService.findAll();
        return ResponseEntity.ok(usuarios);
    }
}