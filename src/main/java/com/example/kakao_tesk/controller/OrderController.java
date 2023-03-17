package com.example.kakao_tesk.controller;

import com.example.kakao_tesk.dto.request.PaymentRequest;
import com.example.kakao_tesk.dto.response.ResponseMessage;
import com.example.kakao_tesk.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/order")
public class OrderController {

    private final OrderService orderService;


    @PostMapping
    public ResponseEntity ResponseEntity(@RequestBody PaymentRequest paymentRequest){
        orderService.createOrder(paymentRequest);

        ResponseMessage responseMessage = new ResponseMessage(200, "주문이 완료되었습니다.");

        return new ResponseEntity(responseMessage, HttpStatus.valueOf(responseMessage.getStatusCode()));
    }
}
