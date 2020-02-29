package com.troytan.learn.spring.aop;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.stereotype.Component;

/**
 * 多切面切入同一个方法
 * <p>
 * 1）被切入的对象，只会生成一个代理对象，而不是包装代理对象
 * 2）每个通知方法，都会加入代理对象的advisors链中，依次执行
 *
 * @Author troytan
 * @Date 2/20/2020
 */
@ComponentScan
@Configuration
@EnableAspectJAutoProxy
public class MultiAspectConfig {
}
