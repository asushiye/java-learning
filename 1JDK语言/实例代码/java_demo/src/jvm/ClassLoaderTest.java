package jvm;

/**
 * @author : zhenyun.su
 * @comment :
 * @since : 2019-11-13
 */

public class ClassLoaderTest {
    public static void main(String[] args) throws Exception{
        //获取系统/应用类加载器
        ClassLoader appClassLoader = ClassLoaderTest.class.getClassLoader();
//        ClassLoader appClassLoader = ClassLoader.getSystemClassLoader();
        System.out.println("应用类加载器：" + appClassLoader);
        //获取系统/应用类加载器的父类加载器，得到扩展类加载器

        ClassLoader extcClassLoader = appClassLoader.getParent();
        System.out.println("扩展类加载器： " + extcClassLoader);
        System.out.println("扩展类加载器的加载路径： " + System.getProperty("java.ext.dirs"));

        //获取扩展类加载器的父加载器，但因根类加载器并不是用Java实现的所以不能获取
        System.out.println("扩展类的父类加载器： " + extcClassLoader.getParent());
    }
}

