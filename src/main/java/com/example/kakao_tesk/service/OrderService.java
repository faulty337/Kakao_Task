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
import lombok.extern.slf4j.Slf4j;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.concurrent.TimeUnit;

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

    public void orderLock(PaymentRequest paymentRequest){
        Order order;
        RLock rlock = redissonClient.getLock(paymentRequest.getUserId().toString());
        try{
            if(!(rlock.tryLock(WAIT_TIME, LEASE_TIME, TimeUnit.SECONDS))){
                throw new CustomException(ErrorCode.NOT_FOUND_LOCK);
            }
            order = createOrder(paymentRequest);

        } catch (InterruptedException e) {
            throw new CustomException(ErrorCode.SERVER_ERROR);
        } finally {
            rlock.unlock();
        }

        requestPlatform(order);

    }

    @Transactional
    public Order createOrder(PaymentRequest paymentRequest) {
        User user = userRepository.findById(paymentRequest.getUserId()).orElseThrow(
                () -> new CustomException(ErrorCode.NOT_FOUND_USER)
        );

        Menu menu = menuRepository.findById(paymentRequest.getManuId()).orElseThrow(
                () -> new CustomException(ErrorCode.NOT_FOUND_MENU)
        );

        if(user.getTotalPoint() < menu.getPrice()){
            throw new CustomException(ErrorCode.SHORTAGE_POINT);
        }

        PointRequest pointRequest = new PointRequest(user.getId(), (long)menu.getPrice());
        pointService.payment(pointRequest, PointType.USE);
        log.info("createOrder");
        return orderRepository.save(new Order(menu.getPrice(), user, menu));
    }

    public void requestPlatform(Order order){
        log.info(order.getMenu().getName());
    }




}
