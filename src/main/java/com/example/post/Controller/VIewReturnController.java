package com.example.post.Controller;

import com.example.post.common.JwtTokenUtil;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class VIewReturnController {


    @GetMapping("/")
    public String showExample() {
        return "PostV"; // HTML 파일명 (확장자 없이)
    }

    @GetMapping("/SignUp")
    public String SignUpView(HttpServletRequest request) {
        String checkCookie = request.getHeader("cookie");
        if(checkCookie.contains("Bearer")){
            return "SignUp";
        }
        return "Login"; // HTML 파일명 (확장자 없이)
    }

    @GetMapping("/Login")
    public String LoginView(HttpServletRequest request) {
        return "Login"; // HTML 파일명 (확장자 없이)
    }

    @GetMapping("/PostCommunity")
    public String PostCommunityView() {
        return "PostCommunity"; // HTML 파일명 (확장자 없이)
    }


}
