package com.example.back.domain.recruitment.presentation;

import com.example.back.domain.recruitment.entity.ActivityType;
import com.example.back.domain.recruitment.presentation.dto.request.WriteRecruitmentRequest;
import com.example.back.domain.recruitment.presentation.dto.response.RecruitmentDetailResponse;
import com.example.back.domain.recruitment.presentation.dto.response.RecruitmentListResponse;
import com.example.back.domain.recruitment.service.RecruitmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
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

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/detail/{recruitment_id}")
    public RecruitmentDetailResponse queryRecruitmentDetail(@PathVariable("recruitment_id") Long recruitmentId) {
        return recruitmentService.queryRecruitmentDetail(recruitmentId);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/write/employment")
    public void writeRecruitment(@RequestBody WriteRecruitmentRequest request) {
        System.out.println("jb bhj");
        recruitmentService.writeRecruitment(request);
    }
}
