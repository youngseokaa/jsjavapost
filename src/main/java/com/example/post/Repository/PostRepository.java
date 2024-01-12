package com.example.post.Repository;

import com.example.post.dto.PostDto;
import com.example.post.entity.Member;
import com.example.post.entity.Post;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PostRepository  extends JpaRepository<Post, Long> {
    @Query("SELECT p FROM Post p WHERE p.softDelete = false AND LOWER(p.title) LIKE LOWER(CONCAT('%', :title, '%')) ORDER BY p.id ASC")
    Page<Post> findBySoftDeleteFalseAndTitleLikeOrderByIdAsc(@Param("title") String title, Pageable pageable);


}
