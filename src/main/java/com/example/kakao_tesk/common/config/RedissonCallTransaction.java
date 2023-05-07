package com.example.kakao_tesk.common.config;

import org.aspectj.lang.ProceedingJoinPoint;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Component
public class RedissonCallTransaction {
    /**
     * 별도의 트랜잭션 생성을 위한 작업
     * @param joinPoint 트랜잭션 동작 포인트
     * @return
     * @throws Throwable
     */
    @Transactional(propagation = Propagation.REQUIRES_NEW) //별도의 트랜잭션 동작 설정
    public Object proceed(ProceedingJoinPoint joinPoint) throws Throwable {
        return joinPoint.proceed();
    }
}
