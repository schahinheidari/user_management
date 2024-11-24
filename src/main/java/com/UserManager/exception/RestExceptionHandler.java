package com.UserManager.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;

@ControllerAdvice
public class RestExceptionHandler {
    @ExceptionHandler({NotFoundException.class})
    public ResponseEntity<Object> handleNotFoundException(NotFoundException notFoundException){
        ApiException apiException = new ApiException(HttpStatus.NOT_FOUND,
                notFoundException.getMessage(), LocalDateTime.now()
        );
        return new ResponseEntity<>(apiException, apiException.getStatus());
    }

    @ExceptionHandler({ConflictException.class})
    public ResponseEntity<Object> handleNotFoundException(ConflictException conflictException){
        ApiException apiException = new ApiException(HttpStatus.CONFLICT,
                conflictException.getMessage(), LocalDateTime.now()
        );
        return new ResponseEntity<>(apiException, apiException.getStatus());
    }
}
