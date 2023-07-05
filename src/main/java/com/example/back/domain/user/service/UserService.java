package com.example.back.domain.user.service;

import com.example.back.domain.user.entity.User;
import com.example.back.domain.user.entity.UserRepository;
import com.example.back.domain.user.exception.UserAlreadyException;
import com.example.back.domain.user.exception.UserNotFoundException;
import com.example.back.domain.user.facade.UserFacade;
import com.example.back.domain.user.persentation.dto.reqeust.UserLoginRequest;
import com.example.back.domain.user.persentation.dto.reqeust.UserSignUpRequest;
import com.example.back.domain.user.persentation.dto.reqeust.UserWriteResumeRequest;
import com.example.back.domain.user.persentation.dto.response.UserInfoResponse;
import com.example.back.global.error.exception.dto.response.TokenResponse;
import com.example.back.global.jwt.JwtTokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class UserService {

    private final UserRepository userRepository;
    private final JwtTokenProvider jwtTokenProvider;
    private final UserFacade userFacade;

    @Value("phone_number")
    String sendPhoneNumber;

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

    @Transactional
    public void writeResume(UserWriteResumeRequest request) {
        User user = userFacade.getCurrentUser();

        user.writeResume(request.getAddress(), request.getIntroduce(), request.getCareer());
    }

    @Transactional(readOnly = true)
    public UserInfoResponse queryUserInfo() {
        User user = userFacade.getCurrentUser();

        return UserInfoResponse.builder()
                .age(user.getAge())
                .name(user.getName())
                .phoneNumber(user.getPhoneNumber())
                .sex(user.getSex())
                .build();
    }

    /*@Transactional
    public void certifiedPhoneNumber(String phoneNumber, String cerNum) {

        String api_key = "";
        String api_secret = "";
        Message coolsms = new Message(api_key, api_secret);

        // 4 params(to, from, type, text) are mandatory. must be filled
        HashMap<String, String> params = new HashMap<>();
        params.put("to", phoneNumber);    // 수신전화번호
        params.put("from", sendPhoneNumber);    // 발신전화번호. 테스트시에는 발신,수신 둘다 본인 번호로 하면 됨
        params.put("type", "SMS");
        params.put("text", "테스트");
        params.put("app_version", "test app 1.2"); // application name and version

        try {
            JSONObject obj = coolsms.send(params);
        } catch (CoolsmsException e) {
            System.out.println(e.getMessage());
            System.out.println(e.getCode());
        }

    }*/
}
