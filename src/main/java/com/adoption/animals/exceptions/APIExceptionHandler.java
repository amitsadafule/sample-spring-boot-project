package com.adoption.animals.exceptions;

import com.adoption.animals.presenters.APIExceptionResponse;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Order(Ordered.HIGHEST_PRECEDENCE)
@ControllerAdvice
public class APIExceptionHandler extends ResponseEntityExceptionHandler {

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        List<String> errors = ex.getBindingResult().getFieldErrors().stream()
            .map(error -> error.getDefaultMessage())
            .collect(Collectors.toList());

        APIExceptionResponse response = APIExceptionResponse.builder()
            .code(ExceptionType.METHOD_ARG_NOT_VALID_ERROR.getErrorCode())
            .errors(errors)
            .message(ex.getMessage())
            .timeStamp(new Date())
            .build();
        return ResponseEntity.badRequest().body(response);
    }

    @Override
    protected ResponseEntity<Object> handleBindException(BindException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        List<String> errors = ex.getBindingResult().getFieldErrors().stream()
            .map(error -> error.getDefaultMessage())
            .collect(Collectors.toList());

        APIExceptionResponse response = APIExceptionResponse.builder()
            .code(ExceptionType.BIND_ERROR.getErrorCode())
            .errors(errors)
            .message(ExceptionType.BIND_ERROR.getDescription())
            .timeStamp(new Date())
            .build();
        return ResponseEntity.badRequest().body(response);
    }

    @ExceptionHandler(AnimalException.class)
    protected ResponseEntity<APIExceptionResponse> handleValidationError(AnimalException animalException) {
        APIExceptionResponse response = APIExceptionResponse.builder()
            .code(animalException.getCode())
            .errors(Arrays.asList(animalException.getDescription()))
            .message(animalException.getMessage())
            .timeStamp(new Date())
            .build();
        return ResponseEntity.badRequest().body(response);
    }
}
