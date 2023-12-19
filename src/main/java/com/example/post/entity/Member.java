package com.example.post.entity;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@NoArgsConstructor
public class Member {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column
    private String loginid;
    @Column
    private String pwsd1;
    @Column
    private String name;
    @Column
    private int birthdayInput;
    @Column
    private String gender;
    @Column
    private String email;
    @Column
    private int mobile;

    @Builder
    public Member( String loginid, String pwsd1, String name, int birthdayInput, String gender, String email, int mobile) {
        this.loginid = loginid;
        this.pwsd1 = pwsd1;
        this.name = name;
        this.birthdayInput = birthdayInput;
        this.gender = gender;
        this.email = email;
        this.mobile = mobile;
    }
}
