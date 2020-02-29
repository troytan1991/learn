package com.troytan.learn.spring.aop;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * junit 原生模式测试，通过获取AopConfig配置类创建ApplicationContext容器
 *
 * @Author troytan
 * @Date 2/20/2020
 */
public class AopTest {

    @Test
    public void test01() {
        ApplicationContext context = new AnnotationConfigApplicationContext(MultiAspectConfig.class);

        Calculator calculator = context.getBean(Calculator.class);
        calculator.add(1, 3);

    }
}
