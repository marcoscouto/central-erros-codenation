package com.github.marcoscouto.repository;

import com.github.marcoscouto.domain.ErrorLog;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ErrorLogRepository extends JpaRepository<ErrorLog, Long> {
}
