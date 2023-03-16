package com.example.kakao_tesk.controller;

import com.example.kakao_tesk.dto.request.PaymentRequest;
import com.example.kakao_tesk.dto.response.ResponseMessage;
import com.example.kakao_tesk.service.OrderService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/order")
public class OrderController {

    private final OrderService orderService;


    @Operation(summary = "주문 API", description = "유저에 주문에 대한 처리를 합니다.", tags = { "OrderController" })
    @PostMapping
    public ResponseEntity ResponseEntity(@RequestBody PaymentRequest paymentRequest){
        orderService.payment(paymentRequest);

        ResponseMessage responseMessage = new ResponseMessage(200, "주문이 완료되었습니다.");

        return new ResponseEntity(responseMessage, HttpStatusCode.valueOf(responseMessage.getStatusCode()));
    }
}
