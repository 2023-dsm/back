package com.example.back.domain.user.persentation.dto.response;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class UserResumeResponse {
    private final String name;
    private final int age;
    private final String address;
    private final String phoneNumber;
    private final String introduce;
    private final String career;

}
