package com.example.back.domain.recruitment.service;

import com.example.back.domain.company.entity.Company;
import com.example.back.domain.company.entity.CompanyRepository;
import com.example.back.domain.company.exception.CompanyNotFoundException;
import com.example.back.domain.recruitment.entity.ActivityType;
import com.example.back.domain.recruitment.entity.Recruitment;
import com.example.back.domain.recruitment.entity.RecruitmentRepository;
import com.example.back.domain.recruitment.exception.RecruitmentNotFoundException;
import com.example.back.domain.recruitment.presentation.dto.request.WriteRecruitmentRequest;
import com.example.back.domain.recruitment.presentation.dto.response.RecruitmentDetailResponse;
import com.example.back.domain.recruitment.presentation.dto.response.RecruitmentElement;
import com.example.back.domain.recruitment.presentation.dto.response.RecruitmentListResponse;
import com.example.back.domain.user.facade.UserFacade;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Service
public class RecruitmentService {

    private final RecruitmentRepository recruitmentRepository;
    private final UserFacade userFacade;
    private final CompanyRepository companyRepository;

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
        Recruitment recruitment = recruitmentRepository.findById(recruitmentId)
                .orElseThrow(()-> RecruitmentNotFoundException.EXCEPTION);

        return RecruitmentDetailResponse.builder()
                .detailWorkArea(recruitment.getDetailWorkArea())
                .jobDescription(recruitment.getJobDescription())
                .weekWorkTime(recruitment.getWeekWorkTime())
                .monthWorkTime(recruitment.getMonthWorkTime())
                .money(recruitment.getMoney())
                .manager(recruitment.getManager())
                .closeDate(recruitment.getCloseDate())
                .companyName(recruitment.getCompanyName())
                .build();
    }

    public void writeRecruitment(WriteRecruitmentRequest request) {
        System.out.println("sdfgdfh");
        Company user = userFacade.getCompanyCurrentUser();
        System.out.println(user);
        Company company = companyRepository.findById(user.getId())
                .orElseThrow(()-> CompanyNotFoundException.EXCEPTION);


        recruitmentRepository.save(Recruitment.builder()
                        .money(request.getMoney())
                        .activityType(request.getActivityType())
                        .company(company)
                        .closeDate(request.getCloseDate())
                        .workName(request.getWorkName())
                        .workArea(request.getWorkArea())
                        .companyName(company.getCompanyName())
                        .detailWorkArea(request.getDetailWorkArea())
                        .jobDescription(request.getJobDescription())
                        .manager(request.getManager())
                        .monthWorkTime(request.getMonthWorkTime())
                        .number(request.getNumber())
                        .weekWorkTime(request.getWeekWorkTime())
                .build());
    }
}
