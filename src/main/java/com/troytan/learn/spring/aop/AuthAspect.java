package com.troytan.learn.spring.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class AuthAspect {

    @Pointcut("execution(public * com..Calculator.*(..))")
    public void pointCut() {
    }

    @Before("pointCut()")
    public void authStart(JoinPoint jp) {
        System.out.println("授权开始");
    }

    @After("pointCut()")
    public void authEnd(JoinPoint jp) {
        System.out.println("授权结束");
    }

}
