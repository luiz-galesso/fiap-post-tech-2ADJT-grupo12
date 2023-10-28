package com.tech.challenge.inscricao.gestaoperfil.controller.exception;

import com.tech.challenge.inscricao.gestaousuario.controller.exception.LogError;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.Instant;

@RestControllerAdvice
public class AuthenticationExceptionHandler {
    private LogError logError = new LogError();

    @ExceptionHandler(AuthenticationException.class)
    public ResponseEntity<LogError> entityNotAuthorized(AuthenticationException e, HttpServletRequest request) {
        HttpStatus status = HttpStatus.BAD_REQUEST;
        logError.setTimestamp(Instant.now());
        logError.setStatus(status.value());
        logError.setError("Perfil não autorizado");
        //logError.setMessage(e.getMessage());
        logError.setPath(request.getRequestURI());
        return ResponseEntity.status(status).body(this.logError);
    }
}
