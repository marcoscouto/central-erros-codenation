package com.github.marcoscouto.service.impl;

import com.github.marcoscouto.domain.ErrorLog;
import com.github.marcoscouto.dto.ErrorLogDTO;
import com.github.marcoscouto.repository.ErrorLogRepository;
import com.github.marcoscouto.service.ErrorLogService;
import com.github.marcoscouto.service.exception.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ErrorLogServiceImpl implements ErrorLogService {

    private final ErrorLogRepository repository;

    @Override
    public Page<ErrorLogDTO> findAll(Example example, String orderBy, Integer page) {
        Sort sort = orderBy != null ? Sort.by(Sort.Direction.ASC, orderBy) : Sort.by(Sort.Direction.ASC, "id");
        Page<ErrorLog> errors = repository.findAll(example, PageRequest.of(page, 5, sort));
        List<ErrorLogDTO> errorsDTO = errors.getContent().stream().map(x -> new ErrorLogDTO(x)).collect(Collectors.toList());
        Page<ErrorLogDTO> pageable = new PageImpl<ErrorLogDTO>(errorsDTO, PageRequest.of(page, 5, sort), errors.getTotalElements());
        if (errors.isEmpty()) throw new NotFoundException("Error Log Exception", "Error logs not found");
        return pageable;
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

    private ErrorLog updateDataError(ErrorLog errorLog, ErrorLog newErrorLog) {
        if (newErrorLog.getDescription() != null && !newErrorLog.getDescription().isEmpty() && !newErrorLog.getDescription().isBlank())
            errorLog.setDescription(newErrorLog.getDescription());
        if (newErrorLog.getLog() != null && !newErrorLog.getLog().isEmpty() && !newErrorLog.getLog().isBlank())
            errorLog.setLog(newErrorLog.getLog());
        if (newErrorLog.getOrigin() != null && !newErrorLog.getOrigin().isEmpty() && !newErrorLog.getOrigin().isBlank())
            errorLog.setOrigin(newErrorLog.getOrigin());
        if (newErrorLog.getLevel() != null && !newErrorLog.getLevel().toString().isEmpty() && !newErrorLog.getLevel().toString().isBlank())
            errorLog.setLevel(newErrorLog.getLevel());
        return errorLog;
    }
}
