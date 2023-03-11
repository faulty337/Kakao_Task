package com.example.kakao_tesk.dto.request;


import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class PaymentRequest {

    private Long userId;
    private Long manuId;

}
