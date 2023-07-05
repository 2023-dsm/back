package com.example.back.domain.recruitment.service;

import com.example.back.domain.recruitment.entity.ActivityType;
import com.example.back.domain.recruitment.entity.Recruitment;
import com.example.back.domain.recruitment.entity.RecruitmentRepository;
import com.example.back.domain.recruitment.exception.RecruitmentNotFoundException;
import com.example.back.domain.recruitment.presentation.dto.response.RecruitmentDetailResponse;
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
                        .recruitmentId(recruitment.getId())
                        .companyName(recruitment.getCompanyName())
                        .workArea(recruitment.getWorkArea())
                        .activityType(recruitment.getActivityType())
                        .number(recruitment.getNumber())
                        .workName(recruitment.getWorkName())
                        .build()
                ).toList();

        return new RecruitmentListResponse(recruitmentElementList);
    }

    @Transactional(readOnly = true)
    public RecruitmentDetailResponse queryRecruitmentDetail(Long recruitmentId) {
        System.out.println(recruitmentId);
        Recruitment recruitment = recruitmentRepository.findById(recruitmentId)
                .orElseThrow(()-> RecruitmentNotFoundException.EXCEPTION);

        System.out.println(recruitment);

        return RecruitmentDetailResponse.builder()
                .detailWorkArea(recruitment.getDetailWorkArea())
                .jobDescription(recruitment.getJobDescription())
                .weekWorkTime(recruitment.getWeekWorkTime())
                .monthWorkTime(recruitment.getMonthWorkTime())
                .money(recruitment.getMoney())
                .manager(recruitment.getManager())
                .closeDate(recruitment.getCloseDate())
                .build();
    }
}
