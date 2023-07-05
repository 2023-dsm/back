package com.example.back.domain.user.facade;

import com.example.back.domain.company.entity.Company;
import com.example.back.domain.company.entity.CompanyRepository;
import com.example.back.domain.user.entity.User;
import com.example.back.domain.user.entity.UserRepository;
import com.example.back.domain.user.exception.UserNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class UserFacade {

    private final UserRepository userRepository;
    private final CompanyRepository companyRepository;

    public User getUserCurrentUser() {
        String userId = SecurityContextHolder.getContext().getAuthentication().getName();
        return userRepository.findById(Long.valueOf(userId)).orElseThrow(() -> UserNotFoundException.EXCEPTION);
    }

    public Company getCompanyCurrentUser() {
        String userId = SecurityContextHolder.getContext().getAuthentication().getName();
        return companyRepository.findById(Long.valueOf(userId)).orElseThrow(() -> UserNotFoundException.EXCEPTION);
    }
}
