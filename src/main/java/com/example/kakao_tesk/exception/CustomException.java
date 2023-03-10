package com.example.kakao_tesk.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class CustomException extends RuntimeException{
    final private ErrorCode errorCode;
}
