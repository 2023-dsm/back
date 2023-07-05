package com.example.back.global.error.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ErrorCode {
    INTERNAL_SERVER_ERROR(500, "Internal Server Error"),
    INVALID_TOKEN(401, "Invalid Token"),
    EXPIRED_TOKEN(401, "Expired Token"),
    USER_NOT_FOUND(404, "User Not Found"),
    USER_ALREADY(409, "User Already"),
    RECRUITMENT_NOT_FOUND(404, "Recruitment Not Found"),
    PASSWORD_NOT_VALID(401, "Password Not Valid"),
    EMPLOYMENT_NOT_FOUND(400, "Employment Not Found"),
    COMPANY_NOT_FOUND(400, "Company Not Found"),
    EMPLOYMENT_NOT_APPLY(409, "Employment Not Apply");

    private final int status;
    private final String message;
}
