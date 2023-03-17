package com.example.kakao_tesk.exception;


import com.example.kakao_tesk.dto.response.ResponseMessage;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler({CustomException.class})
    public ResponseEntity<ResponseMessage> CustomExceptionHandler(CustomException e){
        ResponseMessage responseMessage = new ResponseMessage(e.getErrorCode().getStatusCode(), e.getErrorCode().getMessage());
        return new ResponseEntity<>(responseMessage, HttpStatus.valueOf(responseMessage.getStatusCode()));
    }

}
