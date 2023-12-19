package com.example.post.common;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class ErrorResponse {
    private ErrorCode errorCode;
    private String errorCodeMessage;
}
