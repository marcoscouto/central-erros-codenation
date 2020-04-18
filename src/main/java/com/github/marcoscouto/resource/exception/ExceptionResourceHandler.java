package com.github.marcoscouto.resource.exception;

import com.github.marcoscouto.exception.StandardError;
import com.github.marcoscouto.service.exception.NotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.time.ZoneId;

@RestControllerAdvice
public class ExceptionResourceHandler {

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<StandardError> handleNotFoundException(NotFoundException e){
        StandardError se = new StandardError(e.getTitle(), e.getMessage(), LocalDateTime.now(ZoneId.systemDefault()));
        return ResponseEntity.status(404).body(se);
    }


}
