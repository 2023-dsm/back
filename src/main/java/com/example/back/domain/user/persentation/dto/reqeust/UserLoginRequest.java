package com.example.back.domain.user.persentation.dto.reqeust;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Getter
@NoArgsConstructor
public class UserLoginRequest {

    @NotNull(message = "이름을 입력하세요")
    private String name;

    @NotNull(message = "전화번호를 입력하세요")
    private String phoneNumber;
}
