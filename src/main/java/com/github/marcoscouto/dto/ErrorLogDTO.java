package com.github.marcoscouto.dto;

import com.github.marcoscouto.domain.ErrorLog;
import com.github.marcoscouto.domain.enums.LevelEnum;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
public class ErrorLogDTO implements Serializable {

    private String description;
    private String origin;
    private String level;
    private LocalDateTime timestamp;
    private Integer quantity;

    public ErrorLogDTO(ErrorLog errorLog){
        this.description = errorLog.getDescription();
        this.origin = errorLog.getOrigin();
        this.level = LevelEnum.toEnum(errorLog.getLevel()).getDescription();
        this.quantity = errorLog.getQuantity();
        this.timestamp = errorLog.getTimestamp();
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(LevelEnum level) {
        this.level = level.getDescription();
    }

}
