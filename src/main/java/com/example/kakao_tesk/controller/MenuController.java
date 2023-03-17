package com.example.kakao_tesk.controller;

import com.example.kakao_tesk.dto.response.MenuResponse;
import com.example.kakao_tesk.dto.response.ResponseMessage;
import com.example.kakao_tesk.service.MenuService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/menu")
@RequiredArgsConstructor
public class MenuController {

    private final MenuService menuService;

    @GetMapping()
    public ResponseEntity getMenu(@RequestParam(defaultValue = "") String category){
        List<MenuResponse> menuResponseList = menuService.getMenu(category);
        ResponseMessage responseMessage = new ResponseMessage(200, "커피 메뉴 반환", menuResponseList);
        return new ResponseEntity(responseMessage, HttpStatus.valueOf(responseMessage.getStatusCode()));
    }

    @GetMapping("/rank")
    public ResponseEntity getRank(){
        List<MenuResponse> menuResponseList = menuService.getRank();
        ResponseMessage responseMessage = new ResponseMessage(200, "인기메뉴 메뉴 반환", menuResponseList);
        return new ResponseEntity(responseMessage, HttpStatus.valueOf(responseMessage.getStatusCode()));
    }


}

