package com.github.marcoscouto.repository;

import com.github.marcoscouto.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    @Transactional(readOnly = true)
    Optional<User> findUserByEmail(String email);

    @Transactional(readOnly = true)
    Optional<User> findUserByCpf(String cpf);

}
