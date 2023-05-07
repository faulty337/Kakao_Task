package com.example.kakao_tesk.service;

import com.example.kakao_tesk.common.aop.RedissonLock;
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
import lombok.extern.slf4j.Slf4j;
import org.redisson.api.RedissonClient;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;

    private final UserRepository userRepository;

    private final MenuRepository menuRepository;

    private final PointService pointService;

    private final RedissonClient redissonClient;

    private static final int WAIT_TIME = 1;
    private static final int LEASE_TIME = 2;

    //좀더 이쁘게
    @RedissonLock(key = "userId")
    public void createOrder(Long userId, Long menuId){
        Order order;

        User user = userRepository.findById(userId).orElseThrow(
                () -> new CustomException(ErrorCode.NOT_FOUND_USER)
        );

        Menu menu = menuRepository.findById(menuId).orElseThrow(
                () -> new CustomException(ErrorCode.NOT_FOUND_MENU)
        );

        if(user.getTotalPoint() < menu.getPrice()){
            throw new CustomException(ErrorCode.SHORTAGE_POINT);
        }

        PointRequest pointRequest = new PointRequest(user.getId(), (long)menu.getPrice());
        pointService.payment(pointRequest, PointType.USE);
        log.info("createOrder");
        order =  orderRepository.save(new Order(menu.getPrice(), user, menu));

        requestPlatform(order);

    }


    //비동기로
    public void requestPlatform(Order order){
        log.info(order.getMenu().getName());
    }




}
