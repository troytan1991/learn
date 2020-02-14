package com.troytan.learn.spring.dynamicproxy;

/**
 * AInterface的实现类，演示动态代理对象
 *
 * @Author troytan
 * @Date 2/12/2020
 */
public class AImpl implements AInterface {

    @Override
    public void method1() {
        System.out.println("执行method1");
    }

    @Override
    public void method2() {
        System.out.println("执行method2");
    }
}
