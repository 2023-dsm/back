package com.example.back.domain.user.persentation;

import com.example.back.domain.user.persentation.dto.reqeust.UserSignUpRequest;
import com.example.back.domain.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RequestMapping("/user")
@RestController
public class UserController {

    private final UserService userService;

    //회원가입
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/signup")
    public void userSignUp(@RequestBody UserSignUpRequest request) {
        userService.userSingUp(request);
    }

}
