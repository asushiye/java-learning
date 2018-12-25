# java Thread

		概念
		实现
		Thread属性和方法
		        线程名称
			获取当前线程实例
			暂停线程
			停止线程


## 概念

java有两种方式来实现创建线程，一种从Thread线程类继承子类来实现，另外一种通过实现Runnable接口来实现

## 实现

### Thread子类

```
public class MyThread extends Thread {
    @Override
    public void run() {
        System.out.println("thread subclass running");
    }
}
```

创建线程并启动
```
public class TestMain {
    public static void main(String[] args) {
        TestMain.thread_subClass_test();
    }

    public static void thread_subClass_test() {
        MyThread myThread =  new MyThread();
        myThread.start();
    }
}
```

### Runnable接口

实现接口类
```
public class MyRunnable implements Runnable {
    @Override
    public void run() {
        System.out.println("Runnable implements running");
    }
}

```

```
    public static void implement_runable_test(){
        MyRunnable myRunnable = new MyRunnable();
        Thread thread =  new Thread(myRunnable);
        thread.start();
    }
```

匿名类实现接口

```
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
```

Lambda表达式实现接口类
```
    public static void lambda_Runable_test(){
        Runnable runnable = () -> { System.out.println("Lambda Runnable running"); };
        Thread thread =  new Thread(runnable);
        thread.start();
    }
```

关于两种方法中哪一种最好的规则没有规定。 两种方法都有效。 

就个人而言，我更喜欢实现Runnable，并将实现的实例交给Thread实例。 

当线程池执行Runnable时，很容易将Runnable实例排队，直到来自池的线程空闲

## Thread属性和方法


### 线程名称 Thread Names

通过设置线程名称，我们很容易区分出作业由哪个线程执行完。

```
public class MyThread extends Thread {
    public MyThread(String name) {
        super(name);
    }

    @Override
    public void run() {
        System.out.println("thread subclass running"+this.getName());
    }
}

    public static void thread_subClass_test() {
        MyThread myThread =  new MyThread("My Thread");
        myThread.start();
    }
```

```
    public static void lambda_Runable_test(){
        Runnable runnable = () -> { System.out.println("Lambda Runnable running"); };
        Thread thread =  new Thread(runnable,"Lambda");
        thread.start();
        System.out.println(thread.getName());
    }
```


### 获取当前线程的实例

Thread thread = Thread.currentThread();

```
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
```

这里通过main函数来执行，因此打印出来的当前线程名称current Thread name=main


### 暂停线程

```
try {
    Thread.sleep(10L * 1000L);
} catch (InterruptedException e) {
    e.printStackTrace();
}
```

### 停止线程

```
try {
    Thread.stop();
} catch (InterruptedException e) {
    e.printStackTrace();
}
```