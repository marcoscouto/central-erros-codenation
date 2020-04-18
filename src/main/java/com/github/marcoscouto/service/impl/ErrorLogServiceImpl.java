package com.github.marcoscouto.service.impl;

import com.github.marcoscouto.domain.ErrorLog;
import com.github.marcoscouto.repository.ErrorLogRepository;
import com.github.marcoscouto.service.ErrorLogService;
import com.github.marcoscouto.service.exception.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ErrorLogServiceImpl implements ErrorLogService {

    private final ErrorLogRepository repository;

    @Override
    public List<ErrorLog> findAll() {
        List<ErrorLog> errors = repository.findAll();
        if(errors.isEmpty()) throw new NotFoundException("Error Log Exception","Error logs not found.");
        return errors;
    }

    @Override
    public ErrorLog findById(Long id) {
        return repository
                .findById(id)
                .orElseThrow(() -> new NotFoundException("Error Log Exception", "Error log not found. Id: " + id));
    }

    @Override
    public ErrorLog save(ErrorLog errorLog) {
        errorLog.setId(null);
        return repository.save(errorLog);
    }

    @Override
    public ErrorLog update(Long id, ErrorLog newErrorLog) {
        ErrorLog errorLog = findById(id);
        errorLog = updateDataError(errorLog, newErrorLog);
        repository.save(errorLog);
        return errorLog;
    }

    @Override
    public void delete(Long id) {
        findById(id);
        repository.deleteById(id);
    }

    private ErrorLog updateDataError(ErrorLog errorLog, ErrorLog newErrorLog){
        if(!newErrorLog.getDescription().isEmpty() && !newErrorLog.getDescription().isBlank())
            errorLog.setDescription(newErrorLog.getDescription());
        if(!newErrorLog.getDetails().isEmpty() && !newErrorLog.getDetails().isBlank())
            errorLog.setDetails(newErrorLog.getDetails());
        if(!newErrorLog.getOrigin().isEmpty() && !newErrorLog.getOrigin().isBlank())
            errorLog.setOrigin(newErrorLog.getOrigin());
        if(!newErrorLog.getLevel().toString().isEmpty() && !newErrorLog.getLevel().toString().isBlank())
            errorLog.setLevel(newErrorLog.getLevel());
        return errorLog;
    }
}
