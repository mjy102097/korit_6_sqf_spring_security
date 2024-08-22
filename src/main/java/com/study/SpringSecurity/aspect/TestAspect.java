package com.study.SpringSecurity.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

// AOP를 쓰기위한 기본 틀
@Aspect
@Component
@Order(value = 2)
public class TestAspect {

    @Pointcut("execution(* com.study.SpringSecurity.service.*.aop*(..))") // (..)은 0개 이상이라 없어도 되고 있어도 된다는 뜻
    private void pointCut() {}

    @Around("pointCut()")
    public Object around(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {

        System.out.println("전처리");
        Object result = proceedingJoinPoint.proceed(); // 핵심기능 호출
        System.out.println("후처리");

        return result;
    }
}
