package com.github.marcoscouto.domain.enums;

import com.github.marcoscouto.service.exception.NotFoundException;

import java.util.Arrays;

public enum LevelEnum {

    ERROR(1, "Erro"),
    WARNING(2, "Aviso"),
    INFO(3, "Informação");

    private Integer code;
    private String description;

    LevelEnum(Integer code, String description){
        this.code = code;
        this.description = description;
    }

    public Integer getCode() {
        return code;
    }

    public String getDescription() {
        return description;
    }

    public static LevelEnum toEnum(Integer code){
        if(code == null)
            throw new IllegalArgumentException("O Perfil não pode ser nulo");

        return Arrays.asList(LevelEnum.values())
                .stream()
                .filter(x -> x.getCode() == code)
                .findFirst()
                .orElseThrow(() -> new NotFoundException("Erro de Level", "Level não encontrado"));
    }
}
