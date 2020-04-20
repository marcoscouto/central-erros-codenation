package com.github.marcoscouto.service.impl;

import com.github.marcoscouto.domain.User;
import com.github.marcoscouto.repository.UserRepository;
import com.github.marcoscouto.service.UserService;
import com.github.marcoscouto.service.exception.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository repository;

    @Override
    public List<User> findAll() {
        List<User> users = repository.findAll();
        if(users.isEmpty()) throw new NotFoundException("User Exception", "Users not found");
        return users;
    }

    @Override
    public User findById(Long id) {
        User user = repository.findById(id)
                .orElseThrow(() -> new NotFoundException("User Exception", "User not found. Id: " + id));
        return user;
    }

    @Override
    public User save(User user) {
        user.setId(null);
        return repository.save(user);
    }

    @Override
    public User update(Long id, User newUser) {
        User user = findById(id);
        user = updateUserData(user, newUser);
        return repository.save(user);
    }

    @Override
    public void delete(Long id) {
        findById(id);
        repository.deleteById(id);
    }

    private User updateUserData(User user, User newUser){
        if(newUser.getName() != null && !newUser.getName().isEmpty() && !newUser.getName().isBlank())
            user.setName(newUser.getName());
        if(newUser.getCpf() != null && !newUser.getCpf().isEmpty() && !newUser.getCpf().isBlank())
            user.setCpf(newUser.getCpf());
        if(newUser.getDateBirth() != null)
            user.setDateBirth(newUser.getDateBirth());
        return user;
    }

}
