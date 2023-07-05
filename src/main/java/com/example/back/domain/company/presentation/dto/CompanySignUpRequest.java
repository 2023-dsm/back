package com.example.back.domain.company.presentation.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@NoArgsConstructor
@Getter
public class CompanySignUpRequest {

    @NotNull
    private String userId;

    @NotNull
    private String password;

    @NotNull
    private String companyName;
}
