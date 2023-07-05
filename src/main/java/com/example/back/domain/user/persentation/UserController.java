package com.example.back.domain.user.persentation;

import com.example.back.domain.user.persentation.dto.reqeust.UserLoginRequest;
import com.example.back.domain.user.persentation.dto.reqeust.UserSignUpRequest;
import com.example.back.domain.user.persentation.dto.reqeust.UserWriteResumeRequest;
import com.example.back.domain.user.persentation.dto.response.UserInfoResponse;
import com.example.back.domain.user.service.UserService;
import com.example.back.global.error.exception.dto.response.TokenResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
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

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/login")
    public TokenResponse login(@RequestBody UserLoginRequest request) {
        return userService.login(request);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PatchMapping("/resume")
    public void writeResume(@RequestBody UserWriteResumeRequest request) {
        userService.writeResume(request);
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/info")
    public UserInfoResponse queryUserInfo() {
        return userService.queryUserInfo();
    }

    /*@GetMapping("/send/message")
    @ResponseBody
    public String sendSMS(@RequestBody UserSendMassageReqeust reqeust) {

        Random rand  = new Random();
        String numStr = "";
        for(int i=0; i<4; i++) {
            String ran = Integer.toString(rand.nextInt(10));
            numStr+=ran;
        }

        System.out.println("수신자 번호 : " + reqeust.getPhoneNumber());
        System.out.println("인증번호 : " + numStr);
        userService.certifiedPhoneNumber(reqeust.getPhoneNumber(), numStr);
        return numStr;
    }*/
}
