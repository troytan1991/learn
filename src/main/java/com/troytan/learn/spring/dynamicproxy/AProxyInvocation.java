package com.troytan.learn.spring.dynamicproxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * InvocationHandler接口为jdk动态代理必须传参对象，代理对象的所有方法调用都会进入invoke方法
 *
 * @Author troytan
 * @Date 2/12/2020
 */
public class AProxyInvocation implements InvocationHandler {
    /**
     * 动态代理的核心方法
     *
     * @param proxy  代理后的对象
     * @param method 被执行的方法
     * @param args   被执行的方法参数
     * @return
     * @throws Throwable
     */
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("方法执行前");
        System.out.printf("方法%s被调用，但未找到对象去执行\n",method.getName());
        System.out.println("方法执行后");
        return null;
    }
}
