package com.example.back.domain.recruitment.service;

import com.example.back.domain.recruitment.entity.ActivityType;
import com.example.back.domain.recruitment.entity.Recruitment;
import com.example.back.domain.recruitment.entity.RecruitmentRepository;
import com.example.back.domain.recruitment.presentation.dto.response.RecruitmentElement;
import com.example.back.domain.recruitment.presentation.dto.response.RecruitmentListResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Service
public class RecruitmentService {

    private final RecruitmentRepository recruitmentRepository;

    @Transactional(readOnly = true)
    public RecruitmentListResponse queryRecruitmentList(ActivityType activityType) {
        List<Recruitment> recruitments = recruitmentRepository.findByActivityTypeEquals(activityType);

        if(recruitments.isEmpty()) {
            return new RecruitmentListResponse(List.of());
        }

        List<RecruitmentElement> recruitmentElementList = recruitments.stream()
                .map(recruitment -> RecruitmentElement.builder()
                        .companyName(recruitment.getCompanyName())
                        .workArea(recruitment.getWorkArea())
                        .activityType(recruitment.getActivityType())
                        .number(recruitment.getNumber())
                        .workName(recruitment.getWorkName())
                        .build()
                ).toList();

        return new RecruitmentListResponse(recruitmentElementList);
    }
}
