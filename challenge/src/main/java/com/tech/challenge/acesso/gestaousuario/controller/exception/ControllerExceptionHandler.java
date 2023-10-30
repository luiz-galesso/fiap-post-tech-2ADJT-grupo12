package com.tech.challenge.acesso.gestaousuario.controller.exception;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.Instant;

@ControllerAdvice
public class ControllerExceptionHandler {
    private LogError logError= new LogError();

    @ExceptionHandler(ControllerNotFoundException.class)
    public ResponseEntity<LogError> entityNotFound(ControllerNotFoundException e, HttpServletRequest request) {
        HttpStatus status = HttpStatus.NOT_FOUND;
        logError.setTimestamp(Instant.now());
        logError.setStatus(status.value());
        logError.setError("Entidade não encontrada");
       // logError.setMessage(e.getMessage());
        logError.setPath(request.getRequestURI());
        return ResponseEntity.status(status).body(this.logError);
    }

}
