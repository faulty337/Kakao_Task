package com.example.kakao_tesk.common.aop;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.util.concurrent.TimeUnit;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface RedissonLock {

    String key();

    TimeUnit timeUnit() default TimeUnit.SECONDS;

    //해당 시간동안 lock 획득을 시도한다. 만약 시간 초과시 lock획득에 실패하고 false 리턴
    long waitTime() default 5L;

    //lock획득 이후 해당 시간이 지나면 lock를 자동 해제한다. (무제한 소유 방지)
    long leaseTime() default 3L;

}
