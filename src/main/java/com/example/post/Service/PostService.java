package com.example.post.Service;

import com.example.post.Repository.PostRepository;
import com.example.post.common.ResponseDto;
import com.example.post.dto.PostDto;
import com.example.post.dto.PostRequestDto;
import com.example.post.entity.Member;
import com.example.post.entity.Post;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.ArrayList;
import java.util.List;

import static com.example.post.common.SuccessMessage.POST_WRITING_SUCCESS;


@Service
@RequiredArgsConstructor
public class PostService {
    private final PostRepository postRepository;
    @Transactional
    public ResponseEntity<ResponseDto> createPost(@RequestBody PostRequestDto postRequestDto, Member member) {

        Post post = new Post(postRequestDto.getContent(),postRequestDto.getTitle(), member,false);
        postRepository.save(post);
        return ResponseDto.toResponseEntity(POST_WRITING_SUCCESS, postRequestDto);
    }



    public ResponseEntity<ResponseDto> getPostList(int page, int size, String textSearch) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Post> postPage = postRepository.findBySoftDeleteFalseAndTitleLikeOrderByIdAsc(textSearch,pageable);
        return ResponseDto.toResponseEntity(POST_WRITING_SUCCESS, postPage);
    }

}
