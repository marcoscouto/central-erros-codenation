package com.github.marcoscouto.service.impl;

import com.github.marcoscouto.domain.ErrorLog;
import com.github.marcoscouto.repository.ErrorLogRepository;
import com.github.marcoscouto.service.ErrorLogService;
import com.github.marcoscouto.service.exception.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ErrorLogServiceImpl implements ErrorLogService {

    private final ErrorLogRepository repository;

    @Override
    public List<ErrorLog> findAll(Example example, String orderBy) {
        Sort sort = orderBy != null ? Sort.by(Sort.Direction.ASC, orderBy) : Sort.by(Sort.Direction.ASC, "id");
        List<ErrorLog> errors = repository.findAll(example, sort);
        if(errors.isEmpty()) throw new NotFoundException("Error Log Exception","Error logs not found");
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
        errorLog.setTimestamp(LocalDateTime.now(ZoneId.of("UTC")));
        return repository.save(errorLog);
    }

    @Override
    public ErrorLog update(Long id, ErrorLog newErrorLog) {
        ErrorLog errorLog = findById(id);
        errorLog = updateDataError(errorLog, newErrorLog);
        return repository.save(errorLog);
    }

    @Override
    public void delete(Long id) {
        findById(id);
        repository.deleteById(id);
    }

    private ErrorLog updateDataError(ErrorLog errorLog, ErrorLog newErrorLog){
        if(!newErrorLog.getDescription().isEmpty() && !newErrorLog.getDescription().isBlank() && newErrorLog.getDescription() != null)
            errorLog.setDescription(newErrorLog.getDescription());
        if(!newErrorLog.getLog().isEmpty() && !newErrorLog.getLog().isBlank() && newErrorLog.getLog() != null)
            errorLog.setLog(newErrorLog.getLog());
        if(!newErrorLog.getOrigin().isEmpty() && !newErrorLog.getOrigin().isBlank() && newErrorLog.getOrigin() != null)
            errorLog.setOrigin(newErrorLog.getOrigin());
        if(!newErrorLog.getLevel().toString().isEmpty() && !newErrorLog.getLevel().toString().isBlank() && newErrorLog.getLevel() != null)
            errorLog.setLevel(newErrorLog.getLevel());
        return errorLog;
    }
}
