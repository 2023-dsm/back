package com.example.back.global.auth;

import com.example.back.domain.user.entity.UserRepository;
import com.example.back.domain.user.exception.UserNotFoundException;
import lombok.RequiredArgsConstructor;

import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


@RequiredArgsConstructor
@Service
public class AuthDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public AuthDetails loadUserByUsername(String id) throws UsernameNotFoundException {
        return userRepository.findById(Long.valueOf(id))
                .map(AuthDetails::new)
                .orElseThrow(()-> UserNotFoundException.EXCEPTION);
    }
}
