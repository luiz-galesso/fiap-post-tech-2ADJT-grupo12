package com.fase2.techchallenge.fiap.estacionamento.gestaoestacionamento.controller.exception;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.Instant;

@ControllerAdvice
public class ControllerExceptionHandler {
    private LogError logError = new LogError();
    private StandardError standardError = new StandardError();

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

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<StandardError> validation(MethodArgumentNotValidException e, HttpServletRequest httpServletRequest) {
        HttpStatus httpStatus = HttpStatus.BAD_REQUEST;
        ValidateError validateError = new ValidateError();
        validateError.setTimestamp(Instant.now());
        validateError.setStatus(httpStatus.value());
        validateError.setError("Erro de Validação ");
        validateError.setMessage(e.getMessage());
        validateError.setPath(httpServletRequest.getRequestURI());
        for (FieldError f : e.getFieldErrors()) {
            validateError.addMensagens(f.getField(), f.getDefaultMessage());
        }
        return ResponseEntity.status(httpStatus).body(validateError);
    }

}
