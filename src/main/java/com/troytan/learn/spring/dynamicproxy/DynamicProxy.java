package com.troytan.learn.spring.dynamicproxy;

import java.lang.reflect.Proxy;

/**
 * jdk动态代理的原理是：为接口类生成代理的对象
 * <p>
 * 1）可以利用传对象给invocationHandler,修改执行代理方法的逻辑，达到代理一个对象的目地
 * 2）动态代理生成的类，前缀以$Proxy标识
 *
 * @Author troytan
 * @Date 2/12/2020
 */
public class DynamicProxy {

    /**
     * 为接口生成代理的对象
     *
     * @param aInterfaceClass 被代理的接口class（非对象）
     * @return
     */
    public AInterface proxyInterface(Class<AInterface> aInterfaceClass) {
        //jdk动态代理调用
        Object o = Proxy.newProxyInstance(DynamicProxy.class.getClassLoader(),
                new Class[]{aInterfaceClass},
                new AProxyInvocation());
        return (AInterface) o;
    }

    /**
     * 为对象生成代理的包装对象
     *
     * @param target 被代理的对象
     * @return
     */
    public AInterface proxyObject(AInterface target) {
        Object o = Proxy.newProxyInstance(this.getClass().getClassLoader(),
                target.getClass().getInterfaces(),
                new AImplProxyInvocation(target));
        return (AInterface) o;
    }

    public static void main(String[] args) {
        DynamicProxy dynamicProxy = new DynamicProxy();
        System.out.println("=========代理接口=======");
        AInterface aProxy = dynamicProxy.proxyInterface(AInterface.class);
        //纯代理接口的对象，无法执行被代理接口方法
        aProxy.method1();
        System.out.println("=========代理对象======");
        AInterface aObjectProxy = dynamicProxy.proxyObject(new AImpl());
        //这里可以执行被代理的方法
        aObjectProxy.method1();
    }
}
