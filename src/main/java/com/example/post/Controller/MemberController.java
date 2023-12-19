package com.example.post.Controller;

import com.example.post.Service.MemberService;
import com.example.post.common.ResponseDto;
import com.example.post.dto.LoginRequestDto;
import com.example.post.dto.SignupRequestDto;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;


    @PostMapping("/SignUp")
    public ResponseEntity<ResponseDto> signUp(@RequestBody SignupRequestDto signupRequestDto){
        return memberService.signup(signupRequestDto);
    }

    @PostMapping("/Login")
    public ResponseEntity<ResponseDto> Login(@RequestBody LoginRequestDto loginRequestDto, HttpServletResponse response) {
        return memberService.Login(loginRequestDto, response);
    }
}
