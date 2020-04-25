package com.github.marcoscouto.service;

import com.github.marcoscouto.domain.ErrorLog;
import com.github.marcoscouto.dto.ErrorLogDTO;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;

import java.util.List;

public interface ErrorLogService {

    List<ErrorLogDTO> findAll(Example example, String sort);

    Page<ErrorLogDTO> findAll(Example example, String sort, Integer page);

    ErrorLog findById(Long id);

    ErrorLog save(ErrorLog errorLog);

    ErrorLog update(Long id, ErrorLog errorLog);

    void delete(Long id);

}
