package com.github.marcoscouto.dto;

import com.github.marcoscouto.domain.ErrorLog;
import lombok.Data;

@Data
public class LogDTO {

    private String log;

    public LogDTO(ErrorLog errorLog){
        this.log = errorLog.getLog();
    }
}
