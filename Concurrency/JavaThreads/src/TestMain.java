/**
 * @author : zhenyun.su
 * @since : 2018/12/25
 */

public class TestMain {
    public static void main(String[] args) {
        TestMain.thread_subClass_test();
        TestMain.implement_runable_test();
        TestMain.Anonymous_Runable_test();
        TestMain.lambda_Runable_test();
        TestMain.currectThread_test();
    }

    public static void thread_subClass_test() {
        MyThread myThread =  new MyThread("My Thread");
        myThread.start();
    }

    public static void implement_runable_test(){
        MyRunnable myRunnable = new MyRunnable();
        Thread thread =  new Thread(myRunnable);
        thread.start();
    }

    public static void Anonymous_Runable_test(){
        Runnable myRunnable = new Runnable(){
            @Override
            public void run() {
                System.out.println("Runnable running");
            }
        };
        Thread thread =  new Thread(myRunnable);
        thread.start();
    }

    public static void lambda_Runable_test(){
        Runnable runnable = () -> { System.out.println("Lambda Runnable running"); };
        Thread thread =  new Thread(runnable,"Lambda");
        thread.start();
        System.out.println(thread.getName());
    }

    public static void currectThread_test(){
        System.out.println("current Thread name="+Thread.currentThread().getName());
        for(int i=0; i<10; i++){
            new Thread("" + i){
                @Override
                public void run(){
                    System.out.println("Thread: " + getName() + " running");
                }
            }.start();
        }
    }
}
