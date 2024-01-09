package com.fase2.techchallenge.fiap.pagamento.gestaopagamento.controller.exception;

import com.fase2.techchallenge.fiap.pagamento.gestaopagamento.controller.exception.LogError;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.Instant;

@ControllerAdvice
public class EntityFoundExceptionHandler {
    private LogError logError= new LogError();

    @ExceptionHandler(EntityFoundException.class)
    public ResponseEntity<LogError> entityFound(EntityFoundException e, HttpServletRequest request) {
        HttpStatus status = HttpStatus.BAD_REQUEST;
        logError.setTimestamp(Instant.now());
        logError.setStatus(status.value());
        logError.setError("Entidade já cadastrada encontrada");
        //logError.setMessage(e.getMessage());
        logError.setPath(request.getRequestURI());
        return ResponseEntity.status(status).body(this.logError);
    }
}
