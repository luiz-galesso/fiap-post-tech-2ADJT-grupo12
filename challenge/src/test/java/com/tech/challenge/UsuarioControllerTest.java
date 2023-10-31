package com.tech.challenge;

import com.tech.challenge.acesso.gestaousuario.entity.Perfil;
import com.tech.challenge.acesso.gestaousuario.controller.UsuarioController;

import com.tech.challenge.acesso.gestaousuario.entity.Usuario;
import com.tech.challenge.acesso.gestaousuario.service.UsuarioService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

//define o scopo da classe como teste unitario
@ExtendWith(MockitoExtension.class)
public class UsuarioControllerTest {

    //injeta um bean mockado para o teste
    @InjectMocks
    private UsuarioController usuarioController;

    //injeta os mocks necessarios para o bean
    @Mock
    private UsuarioService usuarioService;

    //cria um cenario de teste
    @Test
    public void shouldGetCandidatoSuccessfully() {
        //cria os dados usados no teste
        String nome = "Lari";
        String nomeUsuario = "teste@gmail.com";
        Perfil perfil = new Perfil();
        perfil.setId(1l);
        var usuario = new Usuario("123", nomeUsuario,  nome, null,perfil);

        //atribui um comportamento a um mock
        when(usuarioService.findById(anyString())).thenReturn(usuario);

        //realiza o teste, invocando a unidade testada
        ResponseEntity<Usuario> usuarioResponse = usuarioController.findById("123");

        //valida se as condicoes do teste foram atendidas
        assertEquals(HttpStatus.OK, usuarioResponse.getStatusCode());
        assertEquals(nome, usuarioResponse.getBody().getNome());
    }

    //esse eh um padrao de teste bem comum chamado AAA - Arrange, Act and Assert
    //https://medium.com/@pablodarde/o-padrão-triple-a-arrange-act-assert-741e2a94cf88
}
