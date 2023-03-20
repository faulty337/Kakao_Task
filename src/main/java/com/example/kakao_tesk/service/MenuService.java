package com.example.kakao_tesk.service;

import com.example.kakao_tesk.dto.response.MenuResponse;
import com.example.kakao_tesk.dto.response.RankResponse;
import com.example.kakao_tesk.entity.Menu;
import com.example.kakao_tesk.entity.Order;
import com.example.kakao_tesk.exception.CustomException;
import com.example.kakao_tesk.exception.ErrorCode;
import com.example.kakao_tesk.repository.MenuRepository;
import com.example.kakao_tesk.repository.OrderRepository;
import com.example.kakao_tesk.type.Category;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.PageRequest;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class MenuService {
    final private MenuRepository menuRepository;
    final private OrderRepository orderRepository;

    @Transactional
    public Menu createMenu(String name, Category category, int price){
        return menuRepository.save(new Menu(name, category, price));
    }

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
    public List<RankResponse> getRank() {
        PageRequest pageRequest = PageRequest.of(0, 3);
        List<RankResponse> rankResponses = orderRepository.findTop3ByCreatedAtAfter(LocalDateTime.now().minusDays(7), pageRequest);
        return rankResponses;
    }





}
