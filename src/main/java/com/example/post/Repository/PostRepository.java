package com.example.post.Repository;

import com.example.post.dto.PostDto;
import com.example.post.entity.Member;
import com.example.post.entity.Post;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PostRepository  extends JpaRepository<Post, Long> {
    Page<PostDto> findBySoftDeleteFalseOrderByIdAsc(Pageable pageable);

}
