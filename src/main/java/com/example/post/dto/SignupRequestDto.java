package com.example.post.dto;

import lombok.Getter;

@Getter
public class SignupRequestDto {
    private String loginid;
    private String pswd1;
    private String name;
    private int birthdayInput;
    private String gender;
    private String email;
    private int mobile;

}
