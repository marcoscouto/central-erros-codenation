package com.github.marcoscouto.domain;

import com.github.marcoscouto.domain.enums.LevelEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "tb_error")
public class ErrorLog implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty(message = "Descrição é obrigatória")
    private String description;

    @NotEmpty(message = "Log é obrigatório")
    private String log;

    @NotEmpty(message = "Origem é obrigatório")
    private String origin;

    @NotNull(message = "Level é obrigatório")
    private Integer level;
    
    private LocalDateTime timestamp;

    @NotNull(message = "Quantidade é obrigatório")
    private Integer quantity;

    public LevelEnum getLevel() {
        return LevelEnum.toEnum(level);
    }

    public void setLevel(LevelEnum level) {
        this.level = level.getCode();
    }
}
