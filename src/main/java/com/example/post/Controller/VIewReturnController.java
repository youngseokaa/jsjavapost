package com.example.post.Controller;

import com.example.post.Repository.PostRepository;
import com.example.post.entity.Post;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Optional;


@Controller
@RequiredArgsConstructor
@Slf4j
public class VIewReturnController {

    private final PostRepository postRepository;
    @GetMapping("/")
    public String showExample(HttpServletRequest request, Model model) {
        HeaderView(request,model);
        return "PostV"; // HTML 파일명 (확장자 없이)
    }

    @GetMapping("/SignUp")
    public String SignUpView(HttpServletRequest request,Model model) {
        HeaderView(request,model);
        String checkCookie = request.getHeader("cookie");
        if(checkCookie.contains("Bearer")){
            return "SignUp";
        }
        return "Login"; // HTML 파일명 (확장자 없이)
    }

    @GetMapping("/Login")
    public String LoginView(HttpServletRequest request,Model model) {
        HeaderView(request,model);
        return "Login"; // HTML 파일명 (확장자 없이)
    }

    @GetMapping("/PostCommunityV")
    public String PostCommunityView(HttpServletRequest request, Model model) {
        HeaderView(request,model);
        return "PostCommunityV"; // HTML 파일명 (확장자 없이)
    }

    @GetMapping("/PostCommunityW")
    public String PostCommunityViewR(HttpServletRequest request,Model model) {
        HeaderView(request,model);
        String checkCookie = request.getHeader("cookie");
        if(checkCookie.contains("Bearer")){
            return "PostCommunityW";
        }
        return "Login"; // HTML 파일명 (확장자 없이)
    }


    @GetMapping("/PostCommunityR")
    public String PostCommunityRead(HttpServletRequest request, Model model,@RequestParam Long page) {
        Post post= postRepository.findById(page).get();
        model.addAttribute("title",post.getTitle());
        model.addAttribute("content",post.getContent());
        HeaderView(request,model);
        return "PostCommunityR"; // HTML 파일명 (확장자 없이)
    }




    private static void HeaderView(HttpServletRequest request ,Model model){
        String checkCookie = request.getHeader("cookie");
        if(checkCookie.contains("Bearer")){
            model.addAttribute("info","SuccessLogin");
        }

        if(!checkCookie.contains("Bearer")){
            model.addAttribute("info","FailLogin");
        }
    }
}
