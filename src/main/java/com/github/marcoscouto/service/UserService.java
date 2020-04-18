package com.github.marcoscouto.service;

import com.github.marcoscouto.domain.User;

import java.util.List;

public interface UserService {

    List<User> findAll();

    User findById(Long id);

    User save(User user);

    User update(Long id, User user);

    void delete(Long id);

}
