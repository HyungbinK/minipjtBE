package com.sprata.minipjtbe.controller;

import com.sprata.minipjtbe.dto.IdCheckRequestDto;
import com.sprata.minipjtbe.dto.SignupRequestDto;
import com.sprata.minipjtbe.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class UserRestController {
    private final UserService userService;

    @Autowired
    public UserRestController(UserService userService){
        this.userService = userService;
    }

    // 회원가입
    @PostMapping("/user/signup")
    public String signup(@RequestBody SignupRequestDto signupRequestDto){
        return userService.signup(signupRequestDto);
    }

    // ID 중복 체크
    @PostMapping("/api/user/idCheck")
    public String idCheck(@RequestBody IdCheckRequestDto idCheckRequestDto){
        return userService.idCheck(idCheckRequestDto);
    }

}
