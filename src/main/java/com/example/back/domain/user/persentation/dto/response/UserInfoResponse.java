package com.example.back.domain.user.persentation.dto.response;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class UserInfoResponse {
    private final String name;
    private final int age;
    private final String sex;
    private final String phoneNumber;
}
