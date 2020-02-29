package com.troytan.learn.spring.aop;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * 通过SpringTest，整合junit进行ioc容器环境的测试
 * 可以直接@Autowired容器中的对象
 *
 * @Author troytan
 * @Date 2/20/2020
 */
@ContextConfiguration(classes = MultiAspectConfig.class)
@RunWith(SpringJUnit4ClassRunner.class)
public class AopTestWithSpring {

    @Autowired
    private Calculator calculator;

    @Test
    public void test01() {

        calculator.add(1, 3);
    }
}
