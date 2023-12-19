package com.example.post.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class VIewReturnController {
    @GetMapping("/")
    public String showExample() {
        return "PostV"; // HTML 파일명 (확장자 없이)
    }

    @GetMapping("/SignUp")
    public String SignUpView() {
        return "SignUp"; // HTML 파일명 (확장자 없이)
    }

    @GetMapping("/Login")
    public String LoginView() {
        return "Login"; // HTML 파일명 (확장자 없이)
    }

    @GetMapping("/PostCommunity")
    public String PostCommunityView() {
        return "PostCommunity"; // HTML 파일명 (확장자 없이)
    }

}
