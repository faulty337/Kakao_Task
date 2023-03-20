package com.example.kakao_tesk;

import com.example.kakao_tesk.dto.request.PaymentRequest;
import com.example.kakao_tesk.dto.response.MenuResponse;
import com.example.kakao_tesk.dto.response.RankResponse;
import com.example.kakao_tesk.entity.Menu;
import com.example.kakao_tesk.entity.Order;
import com.example.kakao_tesk.entity.User;
import com.example.kakao_tesk.repository.MenuRepository;
import com.example.kakao_tesk.repository.OrderRepository;
import com.example.kakao_tesk.repository.UserRepository;
import com.example.kakao_tesk.service.MenuService;
import com.example.kakao_tesk.service.OrderService;
import com.example.kakao_tesk.type.Category;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.platform.commons.logging.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;

import java.awt.print.Pageable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.logging.Logger;

@SpringBootTest
@Slf4j
public class SchedulerTest {

    @Autowired
    private MenuService menuService;

    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private MenuRepository menuRepository;

    @Autowired
    private UserRepository userRepository;


    @BeforeEach
    public void testTest(){
        List<Menu> menuList = new ArrayList<>();
        menuList.add(menuRepository.save(new Menu("아이스아메리카노", Category.COFFEE, 2000)));
        menuList.add(menuRepository.save(new Menu("카페라떼", Category.LATTE, 2500)));
        menuList.add(menuRepository.save(new Menu("레몬에이드", Category.ADE, 2000)));
        menuList.add(menuRepository.save(new Menu("콜드 브루", Category.COFFEE, 3000)));
        User user = new User();
        user.addPoint(10000L);
        user =  userRepository.save(user);
        Menu menu;
        Random random = new Random();
        for(int i = 0; i < 100; i++){
            menu = menuList.get(random.nextInt(menuList.size()));
            orderRepository.save(new Order(menu.getPrice(), user, menu));
        }
    }
    @Test
    public void schedulerTest() {
        PageRequest pageRequest = PageRequest.of(0, 3);
        List<RankResponse> response = orderRepository.findTop3ByCreatedAtAfter(LocalDateTime.now().minusDays(7), pageRequest);
        System.out.println("start");
        for(RankResponse rankResponse : response){
            System.out.println(rankResponse.getName() + " " +  rankResponse.getCategory() + " " + rankResponse.getTotalOrder());
        }
    }


}
