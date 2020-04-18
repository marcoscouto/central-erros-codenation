package com.github.marcoscouto.repository;

import com.github.marcoscouto.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
