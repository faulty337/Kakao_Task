package com.example.kakao_tesk;

import com.example.kakao_tesk.dto.request.PaymentRequest;
import com.example.kakao_tesk.entity.Menu;
import com.example.kakao_tesk.entity.Order;
import com.example.kakao_tesk.entity.User;
import com.example.kakao_tesk.exception.CustomException;
import com.example.kakao_tesk.exception.ErrorCode;
import com.example.kakao_tesk.repository.MenuRepository;
import com.example.kakao_tesk.repository.UserRepository;
import com.example.kakao_tesk.repository.OrderRepository;
import com.example.kakao_tesk.service.OrderService;
import com.example.kakao_tesk.type.Category;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.concurrent.CompletableFuture;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class ServiceTest {

    @Autowired
    private OrderService orderService;

    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private MenuRepository menuRepository;

    @Autowired
    private UserRepository userRepository;

    private User user;


    @BeforeEach
    public void testTest(){
        menuRepository.save(new Menu("아이스아메리카노", Category.COFFEE, 2000));
        menuRepository.save(new Menu("카페라떼", Category.LATTE, 2500));
        this.user = new User();
        user.addPoint(10000L);
        user = userRepository.save(user);
    }

    @Test
    @DisplayName("주문 확인")
    public void order(){

        orderService.createOrder(1L, 1L);

        Order order = orderRepository.findById(1L).get();
        User user = userRepository.findById(1L).get();
        assertEquals(user.getTotalPoint(), 10000-2000);
    }

    @Test
    @DisplayName("동시성 확인")
    public void orderCoincidence(){
        System.out.println(userRepository.findById(user.getId()).get().getId());
        PaymentRequest request1 = new PaymentRequest(user.getId(), 1L);
        PaymentRequest request2 = new PaymentRequest(user.getId(), 2L);


        CompletableFuture.allOf(
                CompletableFuture.runAsync(()->orderService.createOrder(request1.getUserId(), request1.getManuId())),
                CompletableFuture.runAsync(()->orderService.createOrder(request2.getUserId(), request2.getManuId()))
        ).join();

        Order order = orderRepository.findById(1L).get();

        User user = userRepository.findById(this.user.getId()).orElseThrow(
                () -> new CustomException(ErrorCode.NOT_FOUND_USER)
        );
        assertEquals(10000-2000-2500, user.getTotalPoint());
    }


}
