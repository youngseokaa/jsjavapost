package com.example.post.common;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class CustomException  extends RuntimeException {
    private ExceptionMessage exceptionMessage;
}