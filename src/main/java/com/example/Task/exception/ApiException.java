package com.example.Task.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

import java.time.ZonedDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ApiException {
    private String message;
    private HttpStatus httpStatus;
    private ZonedDateTime timestamp;
}
