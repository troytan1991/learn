package com.troytan.learn.spring.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LogAspect {

    @Pointcut("execution(public * com..Calculator.*(..))")
    public void pointCut() {
    }

    @Before("pointCut()")
    public void logStart(JoinPoint jp) {
        String param = "";
        for (Object arg : jp.getArgs()) {
            param += arg.toString() + ",";
        }
        System.out.println("方法开始执行:" + jp.getSignature().getName());
    }

    @After("pointCut()")
    public void logEnd(JoinPoint jp) {
        String param = "";
        for (Object arg : jp.getArgs()) {
            param += arg.toString() + ",";
        }
        System.out.println("方法结束执行:" + jp.getSignature().getName());
    }

}
