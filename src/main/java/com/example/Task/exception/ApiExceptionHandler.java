package com.example.Task.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.ZonedDateTime;

@ControllerAdvice
public class ApiExceptionHandler {

    @ExceptionHandler(value = EntityException.class)
    public ResponseEntity<Object> handlerEntityException (EntityException message) {
        HttpStatus badRequest = HttpStatus.BAD_REQUEST;
        ApiException apiException = new ApiException(message.getMessage(), badRequest, ZonedDateTime.now());
        return new ResponseEntity<>(apiException, badRequest);
    }
}
