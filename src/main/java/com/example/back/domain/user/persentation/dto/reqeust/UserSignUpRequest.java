package com.example.back.domain.user.persentation.dto.reqeust;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@NoArgsConstructor
@Getter
public class UserSignUpRequest {

    @NotNull(message = "이름을 입력해주세요.")
    private String name;

    @NotNull(message = "전화번호를 입력해주세요.")
    private String phoneNumber;

    @NotNull(message = "나이를 입력해주세요.")
    private int age;

    @NotNull(message = "주소를 입력해주세요.")
    private String address;

    @NotNull(message = "성별을 입력해주세요.")
    private String sex;
}
