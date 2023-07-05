package com.example.back.domain.employment.presentation;

import com.example.back.domain.employment.presentation.dto.request.ChangeStatusRequest;
import com.example.back.domain.employment.presentation.dto.response.ApplicationListResponse;
import com.example.back.domain.employment.presentation.dto.response.MyEmploymentResponse;
import com.example.back.domain.employment.service.EmploymentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RequestMapping("/employment")
@RestController
public class EmploymentController {
    private final EmploymentService employmentService;

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/my")
    public MyEmploymentResponse queryMyEmploymentList() {
        return employmentService.queryMyEmploymentList();
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/company")
    public ApplicationListResponse queryApplicationList() {
        System.out.println("aregsthrdfgj");
        return employmentService.queryApplicationList();
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PatchMapping("/deadline/{user_id}")
    public void changeStatus(@RequestBody ChangeStatusRequest request, @PathVariable("user_id") Long userId){
        employmentService.changeStatus(request.isStatus(), userId);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/apply/{recruitment_id}")
    public void postRecruitment(@PathVariable("recruitment_id") Long recruitmentId) {
        employmentService.postEmployment(recruitmentId);
    }
}
