package com.tech.challenge.inscricao.gestaocarreira.controller.exception;

import com.tech.challenge.inscricao.gestaousuario.controller.exception.LogError;

import jakarta.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.Instant;


@RestControllerAdvice
public class UniqueExceptionHandler {
    private LogError logError = new LogError();

    @ExceptionHandler(UniqueException.class)
    public ResponseEntity<LogError> entityFound(UniqueException e, HttpServletRequest request) {
        HttpStatus status = HttpStatus.BAD_REQUEST;
        logError.setTimestamp(Instant.now());
        logError.setStatus(status.value());
        logError.setError("Já cadastrada");
        logError.setMessage(e.getMessage());
        logError.setPath(request.getRequestURI());
        return ResponseEntity.status(status).body(this.logError);
    }
}
