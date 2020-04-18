package com.github.marcoscouto.service.exception;

import lombok.Getter;

public class NotFoundException extends RuntimeException {

    @Getter
    private String title;

    public NotFoundException(String title, String message) {
        super(message);
        this.title = title;
    }
}
