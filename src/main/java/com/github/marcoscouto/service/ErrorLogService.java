package com.github.marcoscouto.service;

import com.github.marcoscouto.domain.ErrorLog;
import com.github.marcoscouto.dto.ErrorLogDTO;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;

public interface ErrorLogService {

    Page<ErrorLogDTO> findAll(Example example, String sort, Integer page);

    ErrorLog findById(Long id);

    ErrorLog save(ErrorLog errorLog);

    ErrorLog update(Long id, ErrorLog errorLog);

    void delete(Long id);

}
