package com.github.marcoscouto.resource.exception;

import com.github.marcoscouto.exception.StandardError;
import com.github.marcoscouto.service.exception.NotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.validation.ConstraintViolationException;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Arrays;
import java.util.stream.Collectors;

@RestControllerAdvice
public class ExceptionResourceHandler {

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<StandardError> handleNotFoundException(NotFoundException e){
        StandardError se = new StandardError(e.getTitle(), Arrays.asList(e.getMessage()), LocalDateTime.now(ZoneId.systemDefault()));
        return ResponseEntity.status(404).body(se);
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<StandardError> handleNotFoundException(ConstraintViolationException e){
        StandardError se = new StandardError("Erro de Validação", e.getConstraintViolations().stream().map(x -> x.getMessageTemplate()).collect(Collectors.toList()), LocalDateTime.now(ZoneId.systemDefault()));
        return ResponseEntity.status(400).body(se);
    }


}
