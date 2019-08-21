package creational.demo.singleton;

/**
 * @author : zhenyun.su
 * @comment :  急切的单实例模式 - 在类加载时实例，缺点资源有可能浪费，可推荐使用
 * @since : 2019/8/19
 */

public class Singleton2 {
    public static Singleton2 instance = new Singleton2();

    private Singleton2() {
    }

    public static synchronized Singleton2 getInstance(){
        return instance;
    }
}
