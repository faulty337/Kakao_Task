package com.example.kakao_tesk.type;

import lombok.Getter;

@Getter
public enum PointType {
    USE(-1),
    CHARGE(1);

    private int status;

    PointType(int status) {
        this.status = status;
    }
}
