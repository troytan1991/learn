package com.troytan.learn.myenum;

/**
 * jdk1.5之前的枚举类实现
 *
 * @Author troytan
 * @Date 2/15/2020
 */
public class EnumWithClass {

    public static final EnumWithClass PERSON1 = new EnumWithClass(1, "person1");
    public static final EnumWithClass PERSON2 = new EnumWithClass(2, "person2");

    private Integer id;
    private String name;

    private EnumWithClass(Integer id, String name) {
        this.id = id;
        this.name = name;
    }

    @Override
    public String toString() {
        return "EnumWithClass{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }

    public static void main(String[] args) {
        System.out.println(EnumWithClass.PERSON1);
    }
}
