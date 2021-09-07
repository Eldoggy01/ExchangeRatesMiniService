package com.example.exchangeratesminiservice.web.api;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Map;

/**
 * todo Document type ErrorHandler
 */
@RestControllerAdvice
public class ErrorHandler {


    @ExceptionHandler({Throwable.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Map<String, String> handlePasswordNotCorrectException(Throwable throwable) {
        return Map.of(throwable.getClass().getSimpleName(), throwable.getMessage());
    }
}
