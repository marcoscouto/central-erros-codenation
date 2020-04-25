package com.github.marcoscouto.resource;

import com.github.marcoscouto.domain.User;
import com.github.marcoscouto.dto.UserDTO;
import com.github.marcoscouto.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
public class UserResource {

    private final UserService service;

    @GetMapping
    public ResponseEntity<List<UserDTO>> findAll(){
        List<User> response = service.findAll();
        List<UserDTO> dto = response.stream().map(x -> new UserDTO(x)).collect(Collectors.toList());
        return ResponseEntity.ok(dto);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserDTO> findById(@PathVariable Long id){
        User response = service.findById(id);
        UserDTO dto = new UserDTO(response);
        return ResponseEntity.ok(dto);
    }

    @PostMapping
    public ResponseEntity<User> save(@RequestBody @Valid User user){
        User response = service.save(user);
        URI uri = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(response.getId())
                .toUri();
        return ResponseEntity.created(uri).body(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<User> update(@PathVariable Long id, @RequestBody User user){
        User response = service.update(id, user);
        return ResponseEntity.ok(response);
    }

}
