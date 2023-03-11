package com.example.kakao_tesk.controller;


import com.example.kakao_tesk.dto.request.PointRequest;
import com.example.kakao_tesk.dto.response.ResponseMessage;
import com.example.kakao_tesk.service.PointService;
import com.example.kakao_tesk.type.PointType;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/point")
@RequiredArgsConstructor
public class PointController {
    private final PointService pointService;

    @PostMapping()
    public ResponseEntity<ResponseMessage> chargePoint(@RequestBody PointRequest pointRequest){
        pointService.addPointHistory(pointRequest, PointType.CHARGE);
        ResponseMessage responseMessage = new ResponseMessage(200, "충전이 완료되었습니다.");
        return new ResponseEntity<>(responseMessage, HttpStatusCode.valueOf(responseMessage.getStatusCode()));
    }

}
