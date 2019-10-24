# 2快速入门
    显性线程 - Thread
      定义任务 - Runnable
      定义线程 - Thread
      线程调度器

    线程管理器 - Executor
      Executors工具来实现
      最佳实现方式 - ThreadPoolExecutor
      从任务中返回值 - Callable
      休眠 - sleep()
      优先级 - setPriority()
      后台线程 - setDaemon()

## 显性线程 - Thread

### 定义任务 - Runnable

java通过实现Runnable接口来定义任务
```java
public class LiftOff implements Runnable {
    protected Integer downCount=8;
    private static Integer counter=0;
    protected final Integer id= counter++;

    @Override
    public void run() {
        while (downCount >= 0){
            System.out.print("#"+id+"("+downCount+") ");
            downCount--;
            Thread.yield();
        }
    }
}
```

```java
public class Test {
    public static void main(String[] args) {
       runable();
    }
    public static void runable() {
        LiftOff liftOff = new LiftOff();
        liftOff.run();
    }
}
```

Thread.yield(); 使CPU在执行完成while一个循环后，就能从一个线程切换到另外一个线程


输出： `0(8) 0(7) 0(6) 0(5) 0(4) 0(3) 0(2) 0(1) 0(0)`

这个任务在main()线程中执行，下面我们创建另外一个线程来执行

### 定义线程 - Thread

我们使用for循环来创建多3个线程，来执行三个任务
```java
    public static void multiThread() {
        System.out.println("multiThread begin");
        for (int i = 0; i < 3; i++) {
            Thread thread = new Thread(new LiftOff());
            thread.start();
        }
        System.out.println("multiThread end");
    }
```

Thread构造器来接收一个任务Runnable实现类

运行时，三个thread线程和main()线程同时执行

由于在单一线程main(),来创建其他线程和任务，因此还有不同id，如果在多个线程中创建LiftOff任务线程，则会有相同的id
输出结果
```
multiThread begin
multiThread end
0(8) 1(8) 0(7) 2(8) 1(7) 0(6) 2(7) 1(6) 0(5)
2(6) 1(5) 0(4) 2(5) 1(4) 0(3) 2(4) 1(3) 0(2)
2(3) 1(2) 0(1) 2(2) 1(1) 0(0) 2(1) 1(0) 2(0)
```

### 线程调度器

它调用时java线程调度器，是java线程机制的一部分

如果我们屏蔽Thread.yield();
输出结果
```
0(8) 0(7) 0(6) 0(5) 0(4) 0(3) 0(2) 0(1) 0(0)
1(8) 2(8) 2(7) 2(6) 2(5) 2(4) 2(3) 2(2) 2(1)
2(0) 1(7) 1(6) 1(5) 1(4) 1(3) 1(2) 1(1) 1(0)
```
说明使用Thread.yield()可以改进在CPU中不断切换线程来执行


如果你的机器有多个CPU，则线程调度器会在这些处理器中分发线程

**主要说明**

```
线程资源必须通过线程池提供，不允许在应用中自行显式创建线程。
说明：
使用线程池的好处是减少在创建和销毁线程上所花的时间以及系统资源的
```

## 线程管理器 - Executor

java.util.concurrent包中的Executor将为你管理Thread对象，从简化并发编程。

ExecutorService通过统一命令方法来执行Executor线程的任务，通过工具Executors来创建不同线程池

### Executors工具来实现

1. Executors.newCachedThreadPool  缓存线程池，推荐使用线程池
2. Executors.newFixedThreadPool  固定线程池，线程池的线程时有限的，因此内存开销更小，速度更快
3. Executors.newSingleThreadExecutor 单一线程执行器，用于当一个线程执行完毕后才能执行下一个线程

```java
    public static void cachedThreadPool() {
        System.out.println("cachedThreadPool");
        ExecutorService executor = Executors.newCachedThreadPool();
        for (int i = 0; i < 3 ; i++) {
            executor.execute(new LiftOff());
        }
        executor.shutdown();
    }
    public static void fixedThreadPool() {
        System.out.println("fixedThreadPool");
        ExecutorService executor = Executors.newFixedThreadPool(3);
        for (int i = 0; i < 3 ; i++) {
            executor.execute(new LiftOff());
        }
        executor.shutdown();
    }
    public static void singleThreadPool() {
        System.out.println("singleThreadPool");
        ExecutorService executor = Executors.newSingleThreadExecutor();
        for (int i = 0; i < 3 ; i++) {
            executor.execute(new LiftOff());
        }
        executor.shutdown();
    }
```

`shutdown()`防止新的任务提交给Executor，使用程序在执行完所有任务后，尽快退出。

但是不推荐使用Excutors工具来生成上面这些线程池，因为

线程池不允许使用Executors去创建，而是通过ThreadPoolExecutor的方式，这样的处理方式让写的同学更加明确线程池的运行规则，规避资源耗尽的风险。 说明：Executors各个方法的弊端：
1）newFixedThreadPool和newSingleThreadExecutor:
  主要问题是堆积的请求处理队列可能会耗费非常大的内存，甚至OOM。
2）newCachedThreadPool和newScheduledThreadPool:
  主要问题是线程数最大数是Integer.MAX_VALUE，可能会创建数量非常多的线程，甚至OOM。


### 最佳实现方式 - ThreadPoolExecutor

```java
Positive example 1：
    //org.apache.commons.lang3.concurrent.BasicThreadFactory
    ScheduledExecutorService executorService = new ScheduledThreadPoolExecutor(1,
        new BasicThreadFactory.Builder().namingPattern("example-schedule-pool-%d").daemon(true).build());



Positive example 2：
    ThreadFactory namedThreadFactory = new ThreadFactoryBuilder()
        .setNameFormat("demo-pool-%d").build();

    //Common Thread Pool
    ExecutorService pool = new ThreadPoolExecutor(5, 200,
        0L, TimeUnit.MILLISECONDS,
        new LinkedBlockingQueue<Runnable>(1024), namedThreadFactory, new ThreadPoolExecutor.AbortPolicy());

    pool.execute(()-> System.out.println(Thread.currentThread().getName()));
    pool.shutdown();//gracefully shutdown
```
这部分以后在验证

### 从任务中返回值 - callable

Runnable是执行独立任务的，但是它不返回任何值。如果希望任务在完成后能够返回值，那么通过Callable接口来实现

```java
public class TaskWithResult implements Callable<String> {
    private int id;
    public TaskWithResult(int id) {
        this.id = id;
    }

    @Override
    public String call() throws Exception {
        return "result of TaskWithResult "+id;
    }
}
```

Callable是泛型接口，因此通过泛型类型我们指定call()方法返回值类型

```java
    public static void taskWithResult(){
        ExecutorService executorService = Executors.newCachedThreadPool();
        Future<String> stringFuture = executorService.submit(new TaskWithResult(1));
        try{
            System.out.println(stringFuture.get());
        }catch(Exception e){
            throw new RuntimeException(e);
        }
    }
```


输出 `result of TaskWithResult 1`

submit() 方法，执行Callable接口的call方法，并返回Future对象

通过Future.get()方法获取返回值，如果线程没有执行结束，则处于阻塞状态，直到线程执行结束

为了防止进入阻塞状态，可以使用isDone()来判断是否执行结束

```java
        try{
            if (stringFuture.isDone()){
                System.out.println(stringFuture.get());
            }else{
                System.out.println("TaskWithResult is not done");
            }
        }catch(Exception e){
            throw new RuntimeException(e);
        }
```

输出 `TaskWithResult is not done`

Future 还提供了cancel() 方法来取消线程的执行， get()带参数来，控制执行的超时时间

### 休眠 - sleep()

使用sleep()方法来睡眠线程(即阻塞)

```java
public class SleepingTask extends LiftOff {
    @Override
    public void run() {
        try{
            while (downCount >= 0){
                System.out.print(id+"("+downCount+") ");
                downCount--;
                TimeUnit.MILLISECONDS.sleep(100);
            }
        }catch(Exception e){
            System.err.println(e.getMessage());
        }
    }
}
```

```java
    public static void sleepingTask() {
        ExecutorService exec = Executors.newCachedThreadPool();
        for (int i = 0; i < 3; i++) {
            exec.execute(new SleepingTask());
        }
        exec.shutdown();
    }
```

输出 `1(8) 2(8) 0(8) 2(7) 1(7) 0(7) 0(6) 1(6) 2(6) 0(5) 2(5) 1(5) 1(4) 2(4) 0(4) 0(3) 2(3) 1(3) 1(2) 2(2) 0(2) 0(1) 2(1) 1(1) 1(0) 2(0) 0(0) `

使用sleep()方法来睡眠线程(即阻塞)，线程调度器将CPU执行线程切换到另外一个线程上

### 优先级 - setPriority()

CPU处理线程集的顺序是不确定的，但是我们可以让线程调度器来倾向让优先级最高的线程先执行

这不意外者优先级低的线程就不执行了，只是执行频率变低了。

```java
public class SleepingTask extends LiftOff {
    @Override
    public void run() {
        Thread.currentThread.setPriority(Thread.MIN_PRIORITY);
        try{
            while (downCount >= 0){
                System.out.print(id+"("+downCount+") ");
                downCount--;
                TimeUnit.MILLISECONDS.sleep(100);
            }
        }catch(Exception e){
            System.err.println(e.getMessage());
        }
    }
}
```

设置当前线程的优先级别为最低。


### 后台线程 - setDaemon()

所谓后台线程是指在程序运行的时候后台提供一个种通用服务的线程，并且这种线程不是程序中不可或缺的一部分。

当非后台线程结束时，程序也终止了，也就是说这个程序所有后台线程也结束了。反之，后台线程结束，非后台线程并不一定结束。

使用Thread的setDaemon(true)来设置这个线程为后台线程
```java
    public static void daemonTask() {
        try{
            for (int i = 0; i < 3; i++) {
                Thread daemon = new Thread(new SleepingTask());
                daemon.setDaemon(true);
                daemon.start();
            }
            TimeUnit.MILLISECONDS.sleep(200);
        }catch(Exception e){
            throw new RuntimeException(e);
        }
    }
```

由于daemonTask(),睡眠200后就结束了，也就后台线程还没有执行完就被终止了

输出如下`0(8) 1(8) 2(8) 2(7) 1(7) 0(7)`， 我们可以调大main方法中sleep时间，则就可以更多输出

通过**线程工厂**来实现

实现ThreadFactory接口的newThread()方法，返回线程来实现
```java
public class DaemonThreadFactory implements ThreadFactory {
    @Override
    public Thread newThread(Runnable r) {
        Thread thread = new Thread(r);
        thread.setDaemon(true);
        return thread;
    }
}
```

```java
   public static void daemonTaskFromThreadFactory() {
        try{
            ExecutorService executorService = Executors.newCachedThreadPool(new DaemonThreadFactory());
            for (int i = 0; i < 3; i++) {
                executorService.execute(new SleepingTask());
            }
            TimeUnit.MILLISECONDS.sleep(100);
        }catch(Exception e){
            throw new RuntimeException(e);
        }
    }
```

输出`0(8) 1(8) 2(8) 2(7) 1(7) 0(7) `


### 共享受限资源 - java编程 707页

有了并发就可以同时做多件事情，但是多线程彼此相互干扰的问题也出现，

比如两个线程同时访问一个银行账户，或向同一个打印机打印，改变同一个值等等问题
