package com.example.back.domain.employment.exception;

import com.example.back.global.error.exception.ErrorCode;
import com.example.back.global.error.exception.GlobalException;

public class EmploymentNotApplyException extends GlobalException {
    public static final GlobalException EXCEPTION = new EmploymentNotApplyException();

    private EmploymentNotApplyException() {
        super(ErrorCode.EMPLOYMENT_NOT_APPLY);
    }
}
