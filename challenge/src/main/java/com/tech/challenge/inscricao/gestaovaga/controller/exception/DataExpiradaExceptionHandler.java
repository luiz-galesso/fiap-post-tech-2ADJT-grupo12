package com.tech.challenge.inscricao.gestaovaga.controller.exception;

import com.tech.challenge.acesso.gestaousuario.controller.exception.LogError;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.Instant;

@RestControllerAdvice
public class DataExpiradaExceptionHandler {
    private LogError logError= new LogError();

    @ExceptionHandler(DataExpiradaException.class)
    public ResponseEntity<LogError> entityFound(DataExpiradaException e, HttpServletRequest request) {
        HttpStatus status = HttpStatus.BAD_REQUEST;
        logError.setTimestamp(Instant.now());
        logError.setStatus(status.value());
        logError.setError("Data expirada");
        //logError.setMessage(e.getMessage());
        logError.setPath(request.getRequestURI());
        return ResponseEntity.status(status).body(this.logError);
    }
}
