/**
 * @author : zhenyun.su
 * @since : 2018/12/27
 */

public class TestMain {

    public static void main(String[] args) {
//       TestMain.thread_test();
        TestMain.mutil_thread_test();
    }

    public static void thread_test(){
        Counter counter = new Counter();
        CounterThread counterThreadA = new CounterThread("CounterThreadA",counter);
        counterThreadA.start();
        System.out.println("---thread_test---");
    }


    public static void mutil_thread_test(){
        Counter counter = new Counter();
        CounterThread counterThreadA = new CounterThread("CounterThreadA",counter);
        CounterThread counterThreadB = new CounterThread("CounterThreadB",counter);
        counterThreadA.start();
        counterThreadB.start();

        System.out.println("---mutil_thread_test---");
    }
}
