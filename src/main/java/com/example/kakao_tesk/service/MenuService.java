package com.example.kakao_tesk.service;

import com.example.kakao_tesk.dto.response.MenuResponse;
import com.example.kakao_tesk.entity.Menu;
import com.example.kakao_tesk.exception.CustomException;
import com.example.kakao_tesk.exception.ErrorCode;
import com.example.kakao_tesk.repository.MenuRepository;
import com.example.kakao_tesk.type.Category;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
@RequiredArgsConstructor
public class MenuService {
    final private MenuRepository menuRepository;


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
        List<MenuResponse> menuResponseList = menuList.stream().map(menu -> new MenuResponse(menu.getId(), menu.getName(), menu.getPrice())).toList();

        return menuResponseList;
    }
}
