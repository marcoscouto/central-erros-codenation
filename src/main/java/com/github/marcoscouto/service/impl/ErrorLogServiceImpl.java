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
    public List<ErrorLogDTO> findAll(Example example, String sort) {
        List<ErrorLog> errors = repository.findAll(example, Sort.by(sort));
        if (errors.isEmpty())
            throw new NotFoundException("Error Log Exception", "Error logs not found");
        List<ErrorLogDTO> dto = errors.stream().map(x -> new ErrorLogDTO(x)).collect(Collectors.toList());
        return dto;
    }

    @Override
    public Page<ErrorLogDTO> findAll(Example example, String orderBy, Integer page) {
        if(page == null)
            page = 0;
        PageRequest pageRequest = getPageRequest(orderBy, page);
        Page<ErrorLog> errors = repository.findAll(example, pageRequest);
        if (errors.isEmpty())
            throw new NotFoundException("Error Log Exception", "Error logs not found");
        Page<ErrorLogDTO> pageable = new PageImpl<ErrorLogDTO>(getErrorsDTO(errors),pageRequest, errors.getTotalElements());
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

    private PageRequest getPageRequest(String orderBy, Integer page){
        return PageRequest.of(page, 5,  Sort.by(Sort.Direction.ASC, orderBy));
    }

    private List<ErrorLogDTO> getErrorsDTO(Page<ErrorLog> errors){
        return errors.getContent().stream().map(x -> new ErrorLogDTO(x)).collect(Collectors.toList());
    }

    private ErrorLog updateDataError(ErrorLog errorLog, ErrorLog newErrorLog) {
        if (newErrorLog.getDescription() != null && !newErrorLog.getDescription().isEmpty())
            errorLog.setDescription(newErrorLog.getDescription());
        if (newErrorLog.getLog() != null && !newErrorLog.getLog().isEmpty())
            errorLog.setLog(newErrorLog.getLog());
        if (newErrorLog.getOrigin() != null && !newErrorLog.getOrigin().isEmpty())
            errorLog.setOrigin(newErrorLog.getOrigin());
        if (newErrorLog.getLevel() != null && !newErrorLog.getLevel().toString().isEmpty())
            errorLog.setLevel(newErrorLog.getLevel());
        return errorLog;
    }
}
