package com.github.marcoscouto.resource;

import com.github.marcoscouto.domain.ErrorLog;
import com.github.marcoscouto.service.ErrorLogService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/errors")
public class ErrorLogResource {

    private final ErrorLogService service;

    @GetMapping
    public ResponseEntity<List<ErrorLog>> findAll(){
        List<ErrorLog> errorLogs = service.findAll();
        return ResponseEntity.ok(errorLogs);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ErrorLog> findById(@PathVariable Long id){
        ErrorLog errorLog = service.findById(id);
        return ResponseEntity.ok(errorLog);
    }

    @PostMapping
    public ResponseEntity<ErrorLog> save(@RequestBody ErrorLog errorLog){
        ErrorLog response = service.save(errorLog);
        URI uri = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(response.getId())
                .toUri();
        return ResponseEntity.created(uri).body(errorLog);
    }

    @PutMapping
    public ResponseEntity<ErrorLog> update(@PathVariable Long id, @RequestBody ErrorLog errorLog){
        service.update(id, errorLog);
        return ResponseEntity.ok(errorLog);
    }



}
