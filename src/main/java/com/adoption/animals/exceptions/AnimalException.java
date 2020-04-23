package com.adoption.animals.exceptions;

import lombok.Getter;

@Getter
public class AnimalException extends RuntimeException {

    private String code;
    private String description;

    public AnimalException(String code, String message, String description) {
        super(message);
        this.code = code;
        this.description = description;
    }
}
