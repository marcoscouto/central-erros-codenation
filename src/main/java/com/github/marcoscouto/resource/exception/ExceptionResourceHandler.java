package com.github.marcoscouto.resource.exception;

import com.github.marcoscouto.exception.StandardError;
import com.github.marcoscouto.service.exception.NotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.validation.ConstraintViolationException;
import java.nio.file.AccessDeniedException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Arrays;
import java.util.stream.Collectors;

@RestControllerAdvice
public class ExceptionResourceHandler {

    LocalDateTime instant = LocalDateTime.now(ZoneId.of("UTC"));

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<StandardError> handleNotFoundException(NotFoundException e){
        StandardError se = new StandardError(e.getTitle(), Arrays.asList(e.getMessage()), instant);
        return ResponseEntity.status(404).body(se);
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<StandardError> handleNotFoundException(ConstraintViolationException e){
        StandardError se = new StandardError("Erro de Validação", e.getConstraintViolations().stream().map(x -> x.getMessageTemplate()).collect(Collectors.toList()), instant);
        return ResponseEntity.status(400).body(se);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<StandardError> handleIllegalArgumentException(IllegalArgumentException e){
        StandardError se = new StandardError("Argumento inválido", Arrays.asList(e.getMessage()), instant);
        return ResponseEntity.status(400).body(se);
    }

    @ExceptionHandler(AuthenticationException.class)
    public ResponseEntity<StandardError> handleAuthenticationException(AuthenticationException e){
        StandardError se = new StandardError("Erro de autenticação", Arrays.asList(e.getMessage()), instant);
        return ResponseEntity.status(401).body(se);
    }

    @ExceptionHandler(AccessDeniedException.class)
    public ResponseEntity<StandardError> handleAccessDeniedException(AccessDeniedException e){
        StandardError se = new StandardError("Erro de autorização", Arrays.asList(e.getMessage()), instant);
        return ResponseEntity.status(403).body(se);
    }

}
