package com.example.back.global.auth;

import com.example.back.domain.company.entity.CompanyRepository;
import com.example.back.domain.user.exception.UserNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


@RequiredArgsConstructor
@Service
public class CompanyDetailsService implements UserDetailsService {

    private final CompanyRepository companyRepository;

    @Override
    public CompanyDetails loadUserByUsername(String id) throws UsernameNotFoundException {
        return companyRepository.findById(Long.valueOf(id))
                .map(CompanyDetails::new)
                .orElseThrow(()-> UserNotFoundException.EXCEPTION);
    }
}
