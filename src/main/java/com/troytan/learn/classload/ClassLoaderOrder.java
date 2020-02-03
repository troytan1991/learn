package com.troytan.learn.classload;

/**
 * 类加载顺序测试的目标类
 *
 * @Author troytan
 * @Date 2/3/2020
 */
public class ClassLoaderOrder extends Parent {

    private Member member = new Member();
    public static StaticMember staticMember = new StaticMember();
    public final static String NAME = "TROY";
    public static String AGE = "19";

    static {
        System.out.println("子类加载");
    }

    public ClassLoaderOrder() {
        System.out.println("子类对象初始化");
    }

    public static void staticMethod() {
        System.out.println("子类静态方法执行");
    }
}

/**
 * 父类
 */
class Parent {
    static {
        System.out.println("父类加载");
    }

    public Parent() {
        System.out.println("父类对象实例化");
    }
}

/**
 * 静态成员类
 */
class StaticMember {
    static {
        System.out.println("静态成员类加载");
    }

    public StaticMember() {
        System.out.println("静态成员对象实例化");
    }
}

/**
 * 非静态成员类
 */
class Member {
    static {
        System.out.println("非静态成员类加载");
    }

    public Member() {
        System.out.println("非静态成员对象实例化");
    }
}
