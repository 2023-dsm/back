package com.example.back.domain.user.service;

import com.example.back.domain.user.entity.User;
import com.example.back.domain.user.entity.UserRepository;
import com.example.back.domain.user.exception.UserAlreadyException;
import com.example.back.domain.user.exception.UserNotFoundException;
import com.example.back.domain.user.persentation.dto.reqeust.UserLoginRequest;
import com.example.back.domain.user.persentation.dto.reqeust.UserSignUpRequest;
import com.example.back.global.error.exception.dto.response.TokenResponse;
import com.example.back.global.jwt.JwtTokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@RequiredArgsConstructor
@Service
public class UserService {

    private final UserRepository userRepository;
    private final JwtTokenProvider jwtTokenProvider;

    @Transactional
    public void userSingUp(UserSignUpRequest request) {

        if(userRepository.existsByPhoneNumber(request.getPhoneNumber())) {
            throw UserAlreadyException.EXCEPTION;
        }

        userRepository.save(User.builder()
                .name(request.getName())
                .phoneNumber(request.getPhoneNumber())
                .age(request.getAge())
                .address(request.getAddress())
                .sex(request.getSex())
                .build()
        );
    }

    @Transactional
    public TokenResponse login(UserLoginRequest request) {
        User user = userRepository.findByPhoneNumberAndName(request.getPhoneNumber(), request.getName())
                .orElseThrow(()-> UserNotFoundException.EXCEPTION);

        return new TokenResponse(jwtTokenProvider.getAccessToken(user.getId()));
    }
}
