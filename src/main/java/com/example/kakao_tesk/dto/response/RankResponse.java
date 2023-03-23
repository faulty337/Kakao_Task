package com.example.kakao_tesk.dto.response;

import com.example.kakao_tesk.type.Category;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class RankResponse {
    private String name;
    private int price;
    private Category category;
    private long totalOrder;
}
