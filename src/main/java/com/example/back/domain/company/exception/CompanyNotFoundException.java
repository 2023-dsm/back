package com.example.back.domain.company.exception;

import com.example.back.global.error.exception.ErrorCode;
import com.example.back.global.error.exception.GlobalException;

public class CompanyNotFoundException extends GlobalException {
    public static final GlobalException EXCEPTION = new CompanyNotFoundException();

    private CompanyNotFoundException() {
        super(ErrorCode.COMPANY_NOT_FOUND);
    }
}
