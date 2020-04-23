package com.github.marcoscouto.dto;

import com.github.marcoscouto.domain.ErrorLog;
import lombok.Data;

import java.io.Serializable;

@Data
public class LogDTO implements Serializable {

    private String log;

    public LogDTO(ErrorLog errorLog){
        this.log = errorLog.getLog();
    }
}
