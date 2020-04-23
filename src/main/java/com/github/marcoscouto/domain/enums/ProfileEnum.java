package com.github.marcoscouto.domain.enums;

import com.github.marcoscouto.service.exception.NotFoundException;

import java.util.Arrays;

public enum ProfileEnum {

    ADMIN(1, "Administrador"),
    USER(2, "Usuário");

    private Integer code;
    private String description;

    ProfileEnum(Integer code, String description){
        this.code = code;
        this.description = description;
    }

    public int getCode() {
        return code;
    }

    public String getDescription() {
        return description;
    }

    public static ProfileEnum toEnum(Integer code){
        if(code == null)
            return null;

        return Arrays
                .stream(ProfileEnum.values())
                .filter(x -> x.code == code)
                .findFirst()
                .orElseThrow(() -> new NotFoundException("Erro de Perfil", "Perfil não encontrado"));
    }
}
