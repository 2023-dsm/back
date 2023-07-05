package com.example.back.domain.employment.service;

import com.example.back.domain.employment.entity.Employment;
import com.example.back.domain.employment.entity.EmploymentRepository;
import com.example.back.domain.employment.presentation.dto.response.MyEmploymentElement;
import com.example.back.domain.employment.presentation.dto.response.MyEmploymentResponse;
import com.example.back.domain.recruitment.entity.Recruitment;
import com.example.back.domain.recruitment.entity.RecruitmentRepository;
import com.example.back.domain.recruitment.exception.RecruitmentNotFoundException;
import com.example.back.domain.user.entity.User;
import com.example.back.domain.user.facade.UserFacade;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class EmploymentService {
    private final EmploymentRepository employmentRepository;
    private final RecruitmentRepository recruitmentRepository;
    private final UserFacade userFacade;

    public MyEmploymentResponse queryMyEmploymentList() {
        User user = userFacade.getCurrentUser();
        List<Employment> employmentList = employmentRepository.findAllByUserId(user.getId());

        if (employmentList.isEmpty()) {
            new MyEmploymentResponse(List.of());
        }

        List<MyEmploymentElement> elementList = employmentList.stream().map(employment -> {
            Recruitment recruitment = recruitmentRepository.findById(employment.getRecruitment().getId())
                    .orElseThrow(()-> RecruitmentNotFoundException.EXCEPTION);

            return MyEmploymentElement.builder()
                    .companyName(recruitment.getCompanyName())
                    .status(employment.isStatus())
                    .workArea(recruitment.getWorkArea())
                    .workName(recruitment.getWorkName())
                    .build();

        }).toList();

        return new MyEmploymentResponse(elementList);
    }
}
