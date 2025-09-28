package com.mauarcanjo.todo.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;

import java.time.LocalDateTime;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    @ResponseStatus(value = HttpStatus.NOT_FOUND)
    public ResponseEntity<ErrorDetails> handleResourceNotFoundException (ResourceNotFoundException exception,
                                                                         WebRequest webRequest) {
        ErrorDetails errorDetails = new ErrorDetails(
                exception.getMessage(),
                "RESOURCE_NOT_FOUND",
                webRequest.getDescription(false),
                LocalDateTime.now()
        );

        return new ResponseEntity<>(errorDetails, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
    public ResponseEntity<ErrorDetails> handleException (ResourceNotFoundException exception,
                                                                         WebRequest webRequest) {
        ErrorDetails errorDetails = new ErrorDetails(
                exception.getMessage(),
                "INTERNAL_SERVER_ERROR",
                webRequest.getDescription(false),
                LocalDateTime.now()
        );

        return new ResponseEntity<>(errorDetails, HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
