package com.example.back.domain.company.presentation;

import com.example.back.domain.company.presentation.dto.CompanyLoginRequest;
import com.example.back.domain.company.presentation.dto.CompanySignUpRequest;
import com.example.back.domain.company.presentation.dto.response.CompanyInfoResponse;
import com.example.back.domain.company.service.CompanyService;
import com.example.back.global.error.exception.dto.response.TokenResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RequestMapping("/company")
@RestController
public class CompanyController {

    private final CompanyService companyService;

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PostMapping("/signup")
    public void companySignUp(@RequestBody CompanySignUpRequest request) {
        companyService.companySignup(request);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/login")
    public TokenResponse companyLogin(@RequestBody CompanyLoginRequest request) {
        return companyService.companyLogin(request);
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/info")
    public CompanyInfoResponse queryCompanyInfo() {
        return companyService.queryCompanyInfo();
    }
}
