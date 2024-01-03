package com.example.post.Controller;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class VIewReturnController {


    @GetMapping("/")
    public String showExample(Model model) {
        model.addAttribute("info", "a");
        return "PostV"; // HTML 파일명 (확장자 없이)
    }

    @GetMapping("/SignUp")
    public String SignUpView(HttpServletRequest request,Model model) {
        String checkCookie = request.getHeader("cookie");
        if(checkCookie.contains("Bearer")){
            return "SignUp";
        }
        return "Login"; // HTML 파일명 (확장자 없이)
    }

    @GetMapping("/Login")
    public String LoginView(HttpServletRequest request,Model model) {

        return "Login"; // HTML 파일명 (확장자 없이)
    }

    @GetMapping("/PostCommunityV")
    public String PostCommunityView(Model model) {

        return "PostCommunityV"; // HTML 파일명 (확장자 없이)
    }

    @GetMapping("/PostCommunityW")
    public String PostCommunityViewR(HttpServletRequest request,Model model) {
        String checkCookie = request.getHeader("cookie");
        if(checkCookie.contains("Bearer")){
            return "PostCommunityW";
        }
        return "SignUp"; // HTML 파일명 (확장자 없이)
    }

}
