package com.example.kakao_tesk.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class PointRequest {
    private Long userId;
    private Long amount;
}
