package com.github.marcoscouto.domain;

import com.github.marcoscouto.domain.enums.LevelEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
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
    private String description;
    private String log;
    private String origin;
    private LevelEnum level;
    private LocalDateTime timestamp;
    private Integer quantity;

}
