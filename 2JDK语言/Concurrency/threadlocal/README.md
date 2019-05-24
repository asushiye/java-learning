# Java ThreadLocal   ------ 本地线程

		ThreadLocal概念
		ThreadLocal实现
		ThreadLocal实例
		ThreadLocal初始化
	
## ThreadLocal概念

ThreadLocal 用于保证变量只能由同一个线程来读写。变量可以是基本变量或对象。

若两个线程或多个线程在操作同一个theadlocal时，每个线程有各自的theadlocal变量，线程之间不可见的。

如果在主线程中创建，则表示整个Java应用程序，就一份threadlocal变量，比较适合数据库连接对象。


## ThreadLocal实现

```
private ThreadLocal myThreadLocal = new ThreadLocal();

myThreadLocal.set("A thread local value");

String threadLocalValue = (String) myThreadLocal.get();

```

通过泛型指定数据类型

```
private ThreadLocal<String> myThreadLocal = new ThreadLocal<String>();

myThreadLocal.set("Hello ThreadLocal");

String threadLocalValue = myThreadLocal.get();
```



## ThreadLocal实例

```
public class ThreadLocalExample {
    public static class MyRunnable implements Runnable {

        private ThreadLocal<Integer> threadLocal =  new ThreadLocal<Integer>();

        @Override
        public void run() {
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

```

输出结果
```
52
94
```

此示例创建一个MyRunnable实例，该实例将传递给两个不同的线程。 

两个线程都执行run（）方法，在ThreadLocal实例上设置不同值。两个线程也分别得到不同最终结果。 

如果已经同步了对set（）调用的访问，并且它不是ThreadLocal对象，则第二个线程将覆盖第一个线程设置的值。

每个线程都有各自不同Theadlocal数据，但是我们可以控制theadlocal初始的值

## ThreadLocal初始化

默认ThreadLocal初始化值为null

```
private ThreadLocal myThreadLocal = new ThreadLocal<String>() {
    @Override protected String initialValue() {
        return "This is the initial value";
    }
};    
```

实例如下

```

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

```
输出结果

```
threadLocal initialValue=0
threadLocal initialValue=0
71
43
```

