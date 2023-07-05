package com.example.back.domain.recruitment.presentation.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class RecruitmentDetailResponse {
    private final String detailWorkArea;
    private final String jobDescription;
    private final String weekWorkTime;
    private final String monthWorkTime;
    private final int money;
    private final String manager;
    private final String closeDate;
    private final String companyName;
}
