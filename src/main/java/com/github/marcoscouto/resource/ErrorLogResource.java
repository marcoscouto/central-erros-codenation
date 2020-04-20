package com.github.marcoscouto.resource;

import com.github.marcoscouto.domain.ErrorLog;
import com.github.marcoscouto.dto.ErrorLogDTO;
import com.github.marcoscouto.dto.LogDTO;
import com.github.marcoscouto.service.ErrorLogService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequiredArgsConstructor
@RequestMapping("/errors")
public class ErrorLogResource {

    private final ErrorLogService service;

    @GetMapping
    public ResponseEntity<Page<ErrorLogDTO>> findAll(ErrorLog data, @RequestParam(value = "orderby") String orderBy, @RequestParam(defaultValue = "0") Integer page){
        ExampleMatcher exampleMatcher = ExampleMatcher
                .matching()
                .withIgnoreCase()
                .withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING);
        Example example = Example.of(data, exampleMatcher);
        Page<ErrorLogDTO> dto = service.findAll(example, orderBy, page);
        return ResponseEntity.ok(dto);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ErrorLog> findById(@PathVariable Long id){
        ErrorLog response = service.findById(id);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/log/{id}")
    public ResponseEntity<LogDTO> findByIdShowLog(@PathVariable Long id){
        ErrorLog response = service.findById(id);
        LogDTO log = new LogDTO(response);
        return ResponseEntity.ok(log);
    }

    @PostMapping
    public ResponseEntity<ErrorLog> save(@RequestBody ErrorLog errorLog){
        ErrorLog response = service.save(errorLog);
        URI uri = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(response.getId())
                .toUri();
        return ResponseEntity.created(uri).body(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ErrorLog> update(@PathVariable Long id, @RequestBody ErrorLog errorLog){
        ErrorLog response = service.update(id, errorLog);
        return ResponseEntity.ok(response);
    }

}
