package com.example.back.global.error;

import com.example.back.global.error.exception.ErrorCode;
import com.example.back.global.error.exception.GlobalException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ErrorHandler {

    @ExceptionHandler(GlobalException.class)
    public ResponseEntity<ErrorResponse> handlingRollsException(GlobalException e) {
        ErrorCode code = e.getErrorCode();
        return new ResponseEntity<>(
                new ErrorResponse(code),
                HttpStatus.valueOf(code.getStatus()));
    }
}
