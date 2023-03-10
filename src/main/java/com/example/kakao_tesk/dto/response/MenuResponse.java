package com.example.kakao_tesk.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class MenuResponse {
    private Long menuId;
    private String name;
    private Integer price;
}
