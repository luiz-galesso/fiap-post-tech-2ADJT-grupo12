package com.tech.challenge.exception;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.TransactionSystemException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

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
        validateError.setMessage("Erro de validação dos campos");
        validateError.setPath(httpServletRequest.getRequestURI());
        for (FieldError f : e.getFieldErrors()) {
            validateError.addMensagens(f.getField(), f.getDefaultMessage());
        }
        return ResponseEntity.status(httpStatus).body(validateError);
    }

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResponseEntity<StandardError> validation(MethodArgumentTypeMismatchException e, HttpServletRequest httpServletRequest) {
        HttpStatus httpStatus = HttpStatus.BAD_REQUEST;
        ValidateError validateError = new ValidateError();
        validateError.setTimestamp(Instant.now());
        validateError.setStatus(httpStatus.value());
        validateError.setError("Erro de Validação");
        validateError.setMessage("Valor inválido: " + e.getCause());
        validateError.setPath(httpServletRequest.getRequestURI());

        return ResponseEntity.status(httpStatus).body(validateError);
    }
    @ExceptionHandler(TransactionSystemException.class)
    public ResponseEntity<StandardError> validation(TransactionSystemException e, HttpServletRequest httpServletRequest) {
        HttpStatus httpStatus = HttpStatus.BAD_REQUEST;
        ValidateError validateError = new ValidateError();
        validateError.setTimestamp(Instant.now());
        validateError.setStatus(httpStatus.value());
        validateError.setError("Erro de Validação");
        validateError.setMessage("Valor inválido: " + e.getCause());
        validateError.setPath(httpServletRequest.getRequestURI());

        return ResponseEntity.status(httpStatus).body(validateError);
    }

}
