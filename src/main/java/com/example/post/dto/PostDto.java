package com.example.post.dto;

import com.example.post.entity.Member;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class PostDto {
    private Long id;
    private LocalDateTime createdAt;
    private LocalDateTime modifiedAt;
    private String content;
    private String title;
    private Long memberid;

    public PostDto(Long id, LocalDateTime createdAt, LocalDateTime modifiedAt, String content, String title, Member member) {
        this.id = id;
        this.createdAt = createdAt;
        this.modifiedAt = modifiedAt;
        this.content = content;
        this.title = title;
        this.memberid = member.getId();
    }
}
