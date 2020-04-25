package com.github.marcoscouto.resource.exception;

import com.github.marcoscouto.exception.StandardError;
import com.github.marcoscouto.service.exception.NotFoundException;
import org.springframework.data.mapping.PropertyReferenceException;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.web.bind.MethodArgumentNotValidException;
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

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<StandardError> handleMethodArgumentNotValidException(MethodArgumentNotValidException e){
        StandardError se = new StandardError("Argumento inválido", e.getBindingResult().getFieldErrors().stream().map(x -> x.getDefaultMessage()).collect(Collectors.toList()), instant);
        return ResponseEntity.status(400).body(se);
    }

    @ExceptionHandler(PropertyReferenceException.class)
    public ResponseEntity<StandardError> handlePropertyReferenceException(PropertyReferenceException e){
        StandardError se = new StandardError("Propriedade inválida", Arrays.asList("A propriedade não existe. Propriedade: " +  e.getPropertyName()), instant);
        return ResponseEntity.status(400).body(se);
    }

}
