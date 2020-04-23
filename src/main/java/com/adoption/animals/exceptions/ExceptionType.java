package com.adoption.animals.exceptions;

import lombok.Getter;

@Getter
public enum ExceptionType {

    GENERAL_ERROR("AGE1000", "error.animals.general.error", "Please try after some time!"),
    METHOD_ARG_NOT_VALID_ERROR("AMAE1000", "error.animals.method.argument.not.valid.error", "Please pass valid method argument"),
    BIND_ERROR("ABE1000", "error.animals.bind.error", "Please pass valid method argument");

    private String errorCode;
    private String messageCode;
    private String description;

    ExceptionType(String errorCode, String messageCode, String description) {
        this.errorCode = errorCode;
        this.messageCode = messageCode;
        this.description = description;
    }
}
