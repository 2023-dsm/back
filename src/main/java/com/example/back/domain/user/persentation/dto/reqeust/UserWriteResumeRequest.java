package com.example.back.domain.user.persentation.dto.reqeust;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Getter
@NoArgsConstructor
public class UserWriteResumeRequest {

    @NotNull(message = "주소를 입력해주세요")
    private String address;
    private String introduce;
    private String career;
}
