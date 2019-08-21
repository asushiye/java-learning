package creational.demo.singleton;

/**
 * @author : zhenyun.su
 * @comment :  双重检查加锁单实例模式，推荐使用
 * @since : 2019/8/19
 */

public class Singleton3 {
    //volatile确保变量instance被初始化为Singleton3实例时，多个线程能正确的处理instance变量
    public volatile static Singleton3 instance;

    private Singleton3() {
    }

    public static synchronized Singleton3 getInstance(){
        //检查实例，当不存在时进入同步区
        if (instance ==null){
            synchronized (Singleton3.class) {
                //进入同步区后，再次检查，如未实例，则实例化
                if (instance == null) {
                    return new Singleton3();
                }
            }
        }
        return instance;
    }
}
