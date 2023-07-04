package com.example.back.domain.recruitment.presentation.dto.response;

import com.example.back.domain.recruitment.entity.ActivityType;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class RecruitmentElement {
    private final String companyName;
    private final String workArea;
    private final ActivityType activityType;
    private final int number;
    private final String workName;
}
