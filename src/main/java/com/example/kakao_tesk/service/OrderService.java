package com.example.kakao_tesk.service;

import com.example.kakao_tesk.dto.request.PaymentRequest;
import com.example.kakao_tesk.dto.request.PointRequest;
import com.example.kakao_tesk.entity.Menu;
import com.example.kakao_tesk.entity.Order;
import com.example.kakao_tesk.entity.User;
import com.example.kakao_tesk.exception.CustomException;
import com.example.kakao_tesk.exception.ErrorCode;
import com.example.kakao_tesk.repository.MenuRepository;
import com.example.kakao_tesk.repository.OrderRepository;
import com.example.kakao_tesk.repository.UserRepository;
import com.example.kakao_tesk.type.PointType;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;

    private final UserRepository userRepository;

    private final MenuRepository menuRepository;

    private final PointService pointService;


    public void payment(PaymentRequest paymentRequest) {
        User user = userRepository.findById(paymentRequest.getUserId()).orElseThrow(
                () -> new CustomException(ErrorCode.NOT_FOUND_USER)
        );

        Menu menu = menuRepository.findById(paymentRequest.getManuId()).orElseThrow(
                () -> new CustomException(ErrorCode.NOT_FOUND_MENU)
        );

        Order order = orderRepository.save(new Order(menu.getPrice(), user, menu));

        PointRequest pointRequest = new PointRequest(user.getId(), (long)menu.getPrice());

        pointService.addPointHistory(pointRequest, PointType.USE);

    }
}
