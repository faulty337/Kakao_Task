package com.example.kakao_tesk.common.aop;

import com.example.kakao_tesk.common.config.RedissonCallTransaction;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

@Aspect
@Component
@Slf4j
@RequiredArgsConstructor
public class RedissonLockAop {
    private final RedissonClient redissonClient;
    private final RedissonCallTransaction redissonCallTransaction;

    @Around("@annotation(com.example.kakao_tesk.common.aop.RedissonLock)")
    public Object lock(final ProceedingJoinPoint joinPoint) throws Throwable {
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();
        RedissonLock redissonLock = method.getAnnotation(RedissonLock.class);


        String key = this.createKey(signature.getParameterNames(), joinPoint.getArgs(), redissonLock.key());

        RLock rLock = redissonClient.getLock(key);

        try{
            //Lock 획득 기다리는 시간, Lock 만료 시간, 시간 단위
            boolean isPossible = rLock.tryLock(redissonLock.waitTime(), redissonLock.leaseTime(), redissonLock.timeUnit());
            if(!isPossible){
                return false;
            }

            log.info("Redisson Lock Key : {}", key);
            //Lock 획득에 성공하면 실질적인 service 로직(해당 어노테이션을 사용한 함수)을 호출
            return redissonCallTransaction.proceed(joinPoint);
        } catch (Exception e){
            throw new InterruptedException();
        } finally{
            //모든 요청 완료시 Lock 해제
            rLock.unlock();
        }

    }

    //파라미터에서 동일한 값을 key로 설정한다.
    private String createKey(String[] parameterNames, Object[] args, String key) {
        String resultKey = key;
        for(int i = 0; i < parameterNames.length; i++){
            if(parameterNames[i].equals(key)){
                resultKey += args[i];
                break;
            }
        }
        return resultKey;
    }
}
