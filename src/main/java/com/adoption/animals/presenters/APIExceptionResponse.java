package com.adoption.animals.presenters;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Builder
@Setter
@Getter
public class APIExceptionResponse {

    private String code;
    private String message;
    private List<String> errors;
    private Date timeStamp;
}
