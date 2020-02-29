package com.troytan.learn.myenum;

/**
 * jdk1.5后，通过enum关键字，进行了编译期的优化
 * <p>
 * 1)public static final EnumWithEnum PERSON1 = new EnumWithEnum(1,"person1")简化为PERSON1(1,"person1");
 * 2)默认了toString()方法为引用名
 * 3)构造方法默认为private
 *
 * @Author troytan
 * @Date 2/15/2020
 */
public enum EnumWithEnum {
    PERSON1(1, "person1"),
    PERSON2(2, "person2");

    private Integer id;
    private String name;

    EnumWithEnum(Integer id, String name) {
        System.out.println("执行构造方法");
        this.id = id;
        this.name = name;
    }
    public static void get(){}

    public static void main(String[] args) {
//        System.out.println(EnumWIthEnum.get());
        EnumWithEnum.get();
    }
}
