package com.example.back.domain.user.exception;

import com.example.back.global.error.exception.ErrorCode;
import com.example.back.global.error.exception.GlobalException;

public class UserAlreadyException extends GlobalException {
    public static final GlobalException EXCEPTION = new UserAlreadyException();

    private UserAlreadyException() {
        super(ErrorCode.USER_ALREADY);
    }
}
