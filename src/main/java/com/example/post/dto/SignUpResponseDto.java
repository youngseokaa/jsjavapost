package com.example.post.dto;

import com.example.post.entity.Member;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class SignUpResponseDto {
    private Long id;
    private String Loginid;
    private String pwsd1;
    private String name;
    private int birthdayInput;
    private String gender;
    private String email;
    private int mobile;


    public SignUpResponseDto(Member member) {
        this.id =member.getId();
        this.Loginid = member.getLoginid();
        this.pwsd1 = member.getPwsd1();
        this.name = member.getName();
        this.birthdayInput = member.getBirthdayInput();
        this.gender = member.getGender();
        this.email = member.getEmail();
        this.mobile = member.getMobile();
    }
}
