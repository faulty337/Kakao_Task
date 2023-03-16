package com.example.kakao_tesk.dto.response;

import com.example.kakao_tesk.type.Category;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class MenuResponse {
    private Long menuId;
    private String name;
    private Integer price;
    private Category category;

    public MenuResponse(String name, Integer price, Category category) {
        this.name = name;
        this.price = price;
        this.category = category;
    }
}
