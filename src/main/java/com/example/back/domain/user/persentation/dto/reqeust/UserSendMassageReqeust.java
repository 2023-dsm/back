package com.example.back.domain.user.persentation.dto.reqeust;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Getter
@NoArgsConstructor
public class UserSendMassageReqeust {
    @NotNull
    private String phoneNumber;
}
