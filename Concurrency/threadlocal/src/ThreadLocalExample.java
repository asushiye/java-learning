/**
 * @author : zhenyun.su
 * @since : 2018/12/27
 */

public class ThreadLocalExample {
    public static class MyRunnable implements Runnable {

        private ThreadLocal<Integer> threadLocal =
                new ThreadLocal<Integer>(){
                    @Override
                    protected Integer initialValue() {
                        return 0;
                    }
                };

        @Override
        public void run() {
            System.out.println("threadLocal initialValue="+threadLocal.get());
            threadLocal.set( (int) (Math.random() * 100D) );
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
            }
            System.out.println(threadLocal.get());
        }
    }


    public static void main(String[] args) {
        MyRunnable sharedRunnableInstance = new MyRunnable();

        Thread thread1 = new Thread(sharedRunnableInstance);
        Thread thread2 = new Thread(sharedRunnableInstance);
        thread1.start();
        thread2.start();
        try{
            thread1.join(); //wait for thread 1 to terminate
            thread2.join(); //wait for thread 2 to terminate
        } catch (InterruptedException e) {

        }
    }
}
