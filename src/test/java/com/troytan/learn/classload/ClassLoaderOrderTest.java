package com.troytan.learn.classload;


import org.junit.Test;

/**
 * 类加载：
 * 1）执行顺序：父类加载->子类静态成员赋值->子类静态代码块
 * 2）类的主动使用时，类加载会执行静态代码块：
 * * *1）调用类的静态变量
 * * *2）执行类静态方法
 * * *3）实例化对象
 * 3）类的被动使用，不会执行静态代码块：classLoad.load(class)，也不会触发父类加载
 * <p>
 * 对象实例化：
 * 1）执行顺序：父类加载->子类静态成员赋值->子类静态代码块->父类对象实例化->子类非静态成员赋值->子类对象构造方法
 *
 * @Author troytan
 * @Date 2/3/2020
 */
public class ClassLoaderOrderTest {


    /**
     * 调用静态变量
     */
    @Test
    public void testActive01() {
        StaticMember staticMember = ClassLoaderOrder.staticMember;
    }


    /**
     * 调用静态方法
     */
    @Test
    public void testActive02() {
        ClassLoaderOrder.staticMethod();
    }

    /**
     * 实例化对象
     */
    @Test
    public void testActive03() {
        ClassLoaderOrder classLoaderOrder = new ClassLoaderOrder();
    }

    /**
     * 类的被动使用
     *
     * @throws ClassNotFoundException
     */
    @Test
    public void testPassive01() throws ClassNotFoundException, IllegalAccessException, InstantiationException {
        //classLoader.loadClass() 不会触发父类加载、子类的静态代码块执行与静态变量的赋值
        Class<?> clazz = this.getClass().getClassLoader().loadClass("com.troytan.learn.classload.ClassLoaderOrder");
        //此时通过反射创建对象，依次进行：
        //父类加载->子类静态成员赋值->子类静态代码块->父类对象实例化->子类非静态成员赋值->子类对象实例化
        Object o = clazz.newInstance();
    }

    /**
     * final static修饰的基本数据类型为常量，在编译时放到常量池，不需要先加载类
     */
    @Test
    public void testActive04() {
        String name = ClassLoaderOrder.NAME;
    }

    /**
     * 调用static修饰的成员（包括基本数据类型、对象类型），需要先加载类
     */
    @Test
    public void testActive05() {
        String age = ClassLoaderOrder.AGE;
    }


}
