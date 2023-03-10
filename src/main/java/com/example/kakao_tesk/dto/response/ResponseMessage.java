package com.example.kakao_tesk.dto.response;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ResponseMessage<T> {
    private int StatusCode;
    private String message;
    private T data;

    public ResponseMessage(int statusCode, String message) {
        StatusCode = statusCode;
        this.message = message;
    }
}
