package com.example.post.common;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;
@Getter
@AllArgsConstructor
public enum SuccessMessage {

    LOGIN_SUCCESS(HttpStatus.OK,"로그인이 완료 되었습니다."),
    SIGN_UP_SUCCESS(HttpStatus.CREATED, "회원가입이 완료 되었습니다."),

    POST_WRITING_SUCCESS(HttpStatus.CREATED, "게시물 작성이 완료 되었습니다.");

    private final HttpStatus httpStatus;
    private final String detail;
}
