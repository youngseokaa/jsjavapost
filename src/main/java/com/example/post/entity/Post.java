package com.example.post.entity;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity(name = "Post")
@Getter
@NoArgsConstructor
public class Post extends TimeStamped{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String content;
    private String title;
    private boolean softDelete;

    @ManyToOne
    @JoinColumn(name = "memberId", nullable = false)
    private Member member;

    @Builder
    public Post(String content, String title, Member member) {
        this.content = content;
        this.title = title;
        this.member = member;
    }
}
