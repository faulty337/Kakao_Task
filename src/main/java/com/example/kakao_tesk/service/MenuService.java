package com.example.kakao_tesk.service;

import com.example.kakao_tesk.dto.response.MenuResponse;
import com.example.kakao_tesk.entity.Menu;
import com.example.kakao_tesk.entity.Order;
import com.example.kakao_tesk.entity.Rank;
import com.example.kakao_tesk.exception.CustomException;
import com.example.kakao_tesk.exception.ErrorCode;
import com.example.kakao_tesk.repository.MenuRepository;
import com.example.kakao_tesk.repository.OrderRepository;
import com.example.kakao_tesk.repository.RankRepository;
import com.example.kakao_tesk.type.Category;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
@RequiredArgsConstructor
public class MenuService {
    final private MenuRepository menuRepository;
    final private OrderRepository orderRepository;
    final private RankRepository rankRepository;


    @Transactional
    public List<MenuResponse> getMenu(String RequestCategory) {
        List<Menu> menuList;
        try{
            Category category = Category.valueOf(RequestCategory);
            menuList = menuRepository.findAllByCategory(category);
        }catch (IllegalArgumentException e){
            if(RequestCategory.isEmpty()){
                menuList = menuRepository.findAll();
            }else{
                throw new CustomException(ErrorCode.BAD_CATEGORY);
            }
        }

        return menuList.stream().map(menu -> new MenuResponse(menu.getId(), menu.getName(), menu.getPrice(), menu.getCategory())).toList();
    }

    @Transactional
    public List<MenuResponse> getRank() {

        List<Rank> menuList = rankRepository.findAll();

        return menuList.stream().map(rank -> new MenuResponse( rank.getName(), rank.getPrice(), rank.getCategory())).toList();
    }

    @Transactional
    @Scheduled(cron = "${scheduler.cron.reset.rank}")
    public void setRanking(){
        List<Order> orderList = orderRepository.findTop3AllByCreatedAtBefore(LocalDateTime.now().minusDays(7));

        List<Rank> rankList = orderList.stream().map(order -> {
            Menu menu = order.getMenu();
            return new Rank(menu.getPrice(), menu.getName(), menu.getCategory());
        }).toList();
        rankRepository.deleteAll();
        rankRepository.saveAll(rankList);
        //시간되면 레디스로
    }



}
