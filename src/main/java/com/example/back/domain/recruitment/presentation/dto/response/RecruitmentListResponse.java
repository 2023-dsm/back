package com.example.back.domain.recruitment.presentation.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
public class RecruitmentListResponse {

    private final List<RecruitmentElement> recruitmentList;
}
