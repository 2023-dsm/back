package com.example.back.domain.employment.exception;

import com.example.back.global.error.exception.ErrorCode;
import com.example.back.global.error.exception.GlobalException;

public class EmploymentNotFoundException extends GlobalException {
    public static final GlobalException EXCEPTION = new EmploymentNotFoundException();

    private EmploymentNotFoundException() {
        super(ErrorCode.EMPLOYMENT_NOT_FOUND);
    }
}
