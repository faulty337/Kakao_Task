package com.example.kakao_tesk.exception;

import lombok.Getter;

@Getter
public enum ErrorCode {
    BAD_CATEGORY("올바르지 않은 카테고리입니다.", 400),
    NOT_FOUND_USER("존재하지 않는 유저 입니다.", 403),
    SHORTAGE_POINT("포인트가 부족합니다", 400),
    BAD_MENU_ID("잘못된 주문 번호입니다.", 400),
    NOT_FOUND_MENU("존재하지 않는 메뉴입니다.", 400),
    NOT_FOUND_LOCK("Lock을 획득하지 못했습니다.", 500),
    SERVER_ERROR("서버에러입니다. 서버관리자에게 문의주세요", 500);


    private final String message;
    private final Integer StatusCode;

    ErrorCode(String message, Integer statusCode) {
        this.message = message;
        StatusCode = statusCode;
    }
}
