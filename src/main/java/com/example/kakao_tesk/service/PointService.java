package com.example.kakao_tesk.service;

import com.example.kakao_tesk.dto.request.PointRequest;
import com.example.kakao_tesk.entity.Point;
import com.example.kakao_tesk.entity.User;
import com.example.kakao_tesk.exception.CustomException;
import com.example.kakao_tesk.exception.ErrorCode;
import com.example.kakao_tesk.repository.PointRepository;
import com.example.kakao_tesk.repository.UserRepository;
import com.example.kakao_tesk.type.PointType;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@RequiredArgsConstructor
public class PointService {
    private final PointRepository pointRepository;
    private final UserRepository userRepository;


    @Transactional
    public void payment(PointRequest pointRequest, PointType pointType){
        User user = userRepository.findById(pointRequest.getUserId()).orElseThrow(
                () -> new CustomException(ErrorCode.NOT_FOUND_USER)
        );

        pointRepository.save(new Point(pointRequest.getAmount()*pointType.getStatus(), pointType, user));

        user.addPoint(pointRequest.getAmount()*pointType.getStatus());

    }






}
