package com.example.post.Controller;

import com.example.post.Service.PostService;
import com.example.post.common.MemberDetails;
import com.example.post.common.ResponseDto;
import com.example.post.dto.PostRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController

@RequiredArgsConstructor
public class PostController {
    private final PostService postService;

    @PostMapping(value = "/Post")
    public ResponseEntity<ResponseDto> createPost(@RequestBody PostRequestDto requestDto, @AuthenticationPrincipal MemberDetails memberDetails){
        return postService.createPost(requestDto, memberDetails.getMember());
    }


    @GetMapping("/List")
    public ResponseEntity<ResponseDto> getPostList(@RequestParam int page,
                                                   @RequestParam int size, @RequestParam String textSearch) {
        return postService.getPostList(page-1, size,textSearch);
    }

//    @GetMapping("/List/Search")
//    public ResponseEntity<ResponseDto> getPostList(@RequestParam String Search
//                                                  ) {
//        return postService.getPostSearchList(Search);
//    }
}
