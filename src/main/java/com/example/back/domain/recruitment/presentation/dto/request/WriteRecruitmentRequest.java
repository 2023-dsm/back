package com.example.back.domain.recruitment.presentation.dto.request;

import com.example.back.domain.recruitment.entity.ActivityType;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Getter
@NoArgsConstructor
public class WriteRecruitmentRequest {
    @NotNull
    private String detailWorkArea;

    @NotNull
    private String jobDescription;

    @NotNull
    private String weekWorkTime;

    @NotNull
    private ActivityType activityType;

    @NotNull
    private String workName;

    @NotNull
    private String workArea;

    @NotNull
    private String monthWorkTime;

    @NotNull
    private int money;

    @NotNull
    private String manager;

    @NotNull
    private String closeDate;

    @NotNull
    private int number;
}
