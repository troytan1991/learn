package com.troytan.learn.spring.dynamicproxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * 代理一个对象，重点在于将被代理的target传入InvocationHandler
 * 以便通过method.invoke(target)去执行target的方法
 *
 * @Author troytan
 * @Date 2/12/2020
 */
public class AImplProxyInvocation implements InvocationHandler {

    //被代理的对象
    private Object target;

    public AImplProxyInvocation(Object target) {
        this.target = target;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("方法执行前");
        method.invoke(target, args);
        System.out.println("方法执行后");
        return null;
    }
}
