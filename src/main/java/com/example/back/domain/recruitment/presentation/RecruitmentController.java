package com.example.back.domain.recruitment.presentation;

import com.example.back.domain.recruitment.entity.ActivityType;
import com.example.back.domain.recruitment.presentation.dto.response.RecruitmentListResponse;
import com.example.back.domain.recruitment.service.RecruitmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RequestMapping("/recruitment")
@RestController
public class RecruitmentController {

    private final RecruitmentService recruitmentService;

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/list")
    public RecruitmentListResponse queryRecruitmentList(@RequestParam(value = "type") ActivityType activityType) {
        return recruitmentService.queryRecruitmentList(activityType);
    }


}
