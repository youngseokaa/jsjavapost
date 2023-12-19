package com.example.post.common;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

import static org.springframework.http.HttpStatus.*;

@Getter
@AllArgsConstructor
public enum ExceptionMessage {
    TEST(BAD_REQUEST, "TEST Exception"),
    ID_PASSWORDS_INCORRECT(BAD_REQUEST, "아이디,비밀번호를 확인해주세요"),
    DUPLICATE_EMAIL(CONFLICT,"중복된 이메일이 존재합니다."),
    UNAUTHORIZED_EXPIRE(UNAUTHORIZED,"로그인이 필요합니다.");

    private final HttpStatus httpStatus;
    private final String detail;
}
