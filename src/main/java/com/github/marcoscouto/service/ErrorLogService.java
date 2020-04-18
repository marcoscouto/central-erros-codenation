package com.github.marcoscouto.service;

import com.github.marcoscouto.domain.ErrorLog;

import java.util.List;

public interface ErrorLogService {

    List<ErrorLog> findAll();

    ErrorLog findById(Long id);

    ErrorLog save(ErrorLog errorLog);

    ErrorLog update(Long id, ErrorLog errorLog);

    void delete(Long id);

}
