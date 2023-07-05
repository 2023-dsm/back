package com.example.back.domain.employment.presentation;

import com.example.back.domain.employment.presentation.dto.response.MyEmploymentResponse;
import com.example.back.domain.employment.service.EmploymentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
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

    
}
