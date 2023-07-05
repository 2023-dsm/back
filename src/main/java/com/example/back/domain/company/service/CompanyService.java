package com.example.back.domain.company.service;

import com.example.back.domain.company.entity.Company;
import com.example.back.domain.company.entity.CompanyRepository;
import com.example.back.domain.company.exception.PasswordNotValidException;
import com.example.back.domain.company.presentation.dto.CompanyLoginRequest;
import com.example.back.domain.company.presentation.dto.CompanySignUpRequest;
import com.example.back.domain.company.presentation.dto.response.CompanyInfoResponse;
import com.example.back.domain.user.exception.UserAlreadyException;
import com.example.back.domain.user.exception.UserNotFoundException;
import com.example.back.domain.user.facade.UserFacade;
import com.example.back.global.error.exception.dto.response.TokenResponse;
import com.example.back.global.jwt.JwtTokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class CompanyService {
    private final CompanyRepository companyRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtTokenProvider jwtTokenProvider;
    private final UserFacade userFacade;

    public void companySignup(CompanySignUpRequest request) {
        String password = passwordEncoder.encode(request.getPassword());

        if(companyRepository.existsByUserId(request.getUserId())) {
            throw UserAlreadyException.EXCEPTION;
        }

        companyRepository.save(Company.builder()
                .companyName(request.getCompanyName())
                .userId(request.getUserId())
                .password(password)
                .build()
        );
    }

    public TokenResponse companyLogin(CompanyLoginRequest request) {
        Company company = companyRepository.findByUserId(request.getUserId())
                .orElseThrow(()-> UserNotFoundException.EXCEPTION);

        if (!passwordEncoder.matches(request.getPassword(), company.getPassword())) {
            throw PasswordNotValidException.EXCEPTION;
        }

        return new TokenResponse(jwtTokenProvider.getCompanyAccessToken(company.getId()));
    }

    public CompanyInfoResponse queryCompanyInfo() {
        Company company = userFacade.getCompanyCurrentUser();

        return new CompanyInfoResponse(company.getCompanyName());
    }
}
