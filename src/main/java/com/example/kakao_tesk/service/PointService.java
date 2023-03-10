package com.example.kakao_tesk.service;

import com.example.kakao_tesk.dto.request.PointRequest;
import com.example.kakao_tesk.entity.Point;
import com.example.kakao_tesk.entity.User;
import com.example.kakao_tesk.exception.CustomException;
import com.example.kakao_tesk.exception.ErrorCode;
import com.example.kakao_tesk.repository.PointRepository;
import com.example.kakao_tesk.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

@Service
@RequiredArgsConstructor
public class PointService {
    private final PointRepository pointRepository;
    private final UserRepository userRepository;
    public void chargePoint(PointRequest pointRequest) {
        User user = userRepository.findById(pointRequest.getUserId()).orElseThrow(
                () -> new CustomException(ErrorCode.NOT_FOUNT_USER)
        );

        Point point = pointRepository.save(new Point(pointRequest.getAmount(), user));
        user.addPoint(point.getAmount()); //일관성 유지 로직 필요
    }




}
