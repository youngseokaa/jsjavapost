package com.example.post.common;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import org.springframework.http.ResponseEntity;

@Getter
@Builder
@AllArgsConstructor
public class ResponseDto<T> {
    private boolean status;
    private String message;
    private T data;



    public static ResponseEntity<ResponseDto> toExceptionResponseEntity(ExceptionMessage exceptionMessage) {
        return ResponseEntity
                .status(exceptionMessage.getHttpStatus())
                .body(ResponseDto.builder()
                        .status(!exceptionMessage.getHttpStatus().isError())
                        .message(exceptionMessage.getDetail())
                        .data(exceptionMessage)
                        .build()
                );
    }


    public static ResponseEntity<ResponseDto> toResponseEntity(SuccessMessage message) {
        return ResponseEntity
                .status(message.getHttpStatus())
                .body(ResponseDto.builder()
                        .status(!message.getHttpStatus().isError())
                        .message(message.getDetail())
                        .data(message)
                        .build()
                );
    }

    public static <T> ResponseEntity<ResponseDto> toResponseEntity(SuccessMessage message, T data) {
        return ResponseEntity
                .status(message.getHttpStatus())
                .body(ResponseDto.builder()
                        .status(!message.getHttpStatus().isError())
                        .message(message.getDetail())
                        .data(data)
                        .build()
                );
    }
}
