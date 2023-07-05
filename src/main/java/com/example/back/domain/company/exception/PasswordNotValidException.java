package com.example.back.domain.company.exception;

import com.example.back.global.error.exception.ErrorCode;
import com.example.back.global.error.exception.GlobalException;

public class PasswordNotValidException extends GlobalException {

    public static final GlobalException EXCEPTION = new PasswordNotValidException();

    private PasswordNotValidException() {
        super(ErrorCode.PASSWORD_NOT_VALID);
    }
}
