package com.troytan.learn.myenum;


import com.troytan.learn.support.Singleton;

/**
 * 利用枚举类实现单例：枚举类普通类一样，包含属性与方法，只是多了枚举变量
 * <p>
 * 1) 枚举的变量为单例
 * 2) 防止反射调用private构造方法，执行攻击
 *
 * @Author troytan
 * @Date 2/15/2020
 */
public enum EnumSingleton {
    INSTANCE;   //枚举变量实际为类final static属性，单例对象
    private Singleton singleton;

    private EnumSingleton() {
        singleton = new Singleton();
    }

    public Singleton getSingleton() {
        return singleton;
    }

    public static void main(String[] args) {
        //获取一个枚举对象INSTANCE，并调用返回实例方法
        EnumSingleton.INSTANCE.getSingleton();
    }

}
