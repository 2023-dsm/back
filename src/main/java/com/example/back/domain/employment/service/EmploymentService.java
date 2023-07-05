package com.example.back.domain.employment.service;

import com.example.back.domain.company.entity.Company;
import com.example.back.domain.employment.entity.Employment;
import com.example.back.domain.employment.entity.EmploymentRepository;
import com.example.back.domain.employment.exception.EmploymentNotApplyException;
import com.example.back.domain.employment.exception.EmploymentNotFoundException;
import com.example.back.domain.employment.presentation.dto.response.ApplicationElement;
import com.example.back.domain.employment.presentation.dto.response.ApplicationListResponse;
import com.example.back.domain.employment.presentation.dto.response.MyEmploymentElement;
import com.example.back.domain.employment.presentation.dto.response.MyEmploymentResponse;
import com.example.back.domain.recruitment.entity.Recruitment;
import com.example.back.domain.recruitment.entity.RecruitmentRepository;
import com.example.back.domain.recruitment.exception.RecruitmentNotFoundException;
import com.example.back.domain.user.entity.User;
import com.example.back.domain.user.entity.UserRepository;
import com.example.back.domain.user.exception.UserNotFoundException;
import com.example.back.domain.user.facade.UserFacade;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Service
public class EmploymentService {
    private final EmploymentRepository employmentRepository;
    private final RecruitmentRepository recruitmentRepository;
    private final UserRepository userRepository;
    private final UserFacade userFacade;

    public MyEmploymentResponse queryMyEmploymentList() {
        User user = userFacade.getUserCurrentUser();
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

    public ApplicationListResponse queryApplicationList() {
        System.out.println("sdfgd");
        Company user = userFacade.getCompanyCurrentUser();
        System.out.println("saefrgd");
        List<Employment> employmentList = employmentRepository.findAllByCompanyId(user.getId());

        if(employmentList.isEmpty()) {
            employmentList = List.of();
        }

        List<Long> userIdList = employmentList.stream().map(employment -> employment.getUser().getId()).toList();
        if(userIdList.isEmpty()) {
            userIdList = List.of();
        }
        System.out.println(userIdList);


        List<ApplicationElement> applicationList = userIdList.stream().map(userId -> {
                User applicationUser = userRepository.findById(userId).orElseThrow(()-> UserNotFoundException.EXCEPTION);

                return ApplicationElement.builder()
                        .id(applicationUser.getId())
                        .sex(applicationUser.getName())
                        .name(applicationUser.getName())
                        .age(applicationUser.getAge())
                        .build();
        }).toList();

        if(applicationList.isEmpty()) {
            new ApplicationListResponse(List.of());
        }

        return new ApplicationListResponse(applicationList);
    }

    @Transactional
    public void changeStatus(boolean status, Long userId) {
        userRepository.findById(userId)
                .orElseThrow(()->UserNotFoundException.EXCEPTION);

        Company user = userFacade.getCompanyCurrentUser();

        Employment employment = employmentRepository.findByUserIdAndCompanyId(userId, user.getId())
                .orElseThrow(()-> EmploymentNotFoundException.EXCEPTION);

        employment.changeStatus(status);
    }

    public void postEmployment(Long recruitmentId) {
        Recruitment recruitment = recruitmentRepository.findById(recruitmentId)
                .orElseThrow(()->RecruitmentNotFoundException.EXCEPTION);

        User user = userFacade.getUserCurrentUser();

        recruitmentRepository.findById(recruitmentId)
                .orElseThrow(()->RecruitmentNotFoundException.EXCEPTION);

        if(employmentRepository.findByUserIdAndRecruitmentId(user.getId(), recruitmentId)) {
            throw EmploymentNotApplyException.EXCEPTION;
        }

        employmentRepository.save(Employment.builder()
                        .company(recruitment.getCompany())
                        .status(false)
                        .recruitment(recruitment)
                        .user(user)
                .build());
    }
}
