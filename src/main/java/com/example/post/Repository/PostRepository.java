package com.example.post.Repository;

import com.example.post.entity.Member;
import com.example.post.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository  extends JpaRepository<Post, Long> {

}
