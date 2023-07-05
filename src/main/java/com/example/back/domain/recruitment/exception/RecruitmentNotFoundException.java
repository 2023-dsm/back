package com.example.back.domain.recruitment.exception;

import com.example.back.global.error.exception.ErrorCode;
import com.example.back.global.error.exception.GlobalException;

public class RecruitmentNotFoundException extends GlobalException {
    public static final GlobalException EXCEPTION = new RecruitmentNotFoundException();

    private RecruitmentNotFoundException() {
        super(ErrorCode.RECRUITMENT_NOT_FOUND);
    }
}
