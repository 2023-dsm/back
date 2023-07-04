package com.example.back.domain.user.service;

import com.example.back.domain.user.entity.User;
import com.example.back.domain.user.entity.UserRepository;
import com.example.back.domain.user.exception.UserAlreadyException;
import com.example.back.domain.user.persentation.dto.reqeust.UserSignUpRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@RequiredArgsConstructor
@Service
public class UserService {

    private final UserRepository userRepository;

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
}
