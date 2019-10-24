# 4终止任务 - java编程 -724页

    使用标识来终止任务
    中断
      运行时中断
      阻塞时中断

## 使用标识来终止任务

#### 代码编写
定义共享资源
```java
public class Count {
    private int count = 0;
    private Random random = new Random(47);
    public synchronized int value(){
        return count;
    }
    public synchronized int increment(){
        int temp = count;
        if (random.nextBoolean()){
            Thread.yield();
        }
        return (count = ++temp);
    }
}
```

将value方法和increment方法设置为同步控制，保证多线程下安全访问。

定义线程入口的任务类
```java
package concurrent.termination;

public class Entrance implements Runnable {
    private static Count count = new Count();
    private static List<Entrance> entrances = new ArrayList<Entrance>();
    private static volatile boolean cancel = false;
    private int currentValue=0;
    private final int id;

    public Entrance(int id) {
        this.id = id;
        entrances.add(this);
    }
    public synchronized int getCurrentValue(){
        return currentValue;
    }
    public static void isCancel(){
        cancel = true;
    }

    @Override
    public void run() {
        while(!cancel){
            synchronized (this){
                ++currentValue;
            }
            System.out.println("entrance "+id+"; value "+count.increment());
            try{
                TimeUnit.MILLISECONDS.sleep(100);
            }catch(InterruptedException e){
                System.out.println("sleep interrupted");
            }
        }
        System.out.println("Stopping "+this);
    }

    public static int getTotalValue(){
        return count.value();
    }
    public static int sumEntrance(){
        int sum= 0;
        for (Entrance entrance: entrances){
            sum = sum + entrance.getCurrentValue();
        }
        return sum;
    }

    @Override
    public String toString() {
        return "Entrance{" +
                "currentValue=" + currentValue +
                ", id=" + id +
                '}';
    }
}
```

这个类即完成每个入口的人数统计，通过静态变量和静态方法也统一管理共享资源和所有入门的管理

使用标识 cancel 来终止任务的执行，这里用volatile来保存取消标识的原子性操作，

当然也可以用同步控制synchornized关键字，来控制isCancel()的方法

```java
public class Test {
    public static void main(String[] args) throws Exception {
        ExecutorService executorService = Executors.newCachedThreadPool();
        for (int i = 0; i < 5; i++) {
            executorService.execute(new Entrance(i));
        }
        TimeUnit.SECONDS.sleep(2);
        Entrance.isCancel();
        executorService.shutdown();
        if (!executorService.awaitTermination(250, TimeUnit.MILLISECONDS)){
            System.out.println("all entrance is not termination");
        }else{
            System.out.println("all entrance is termination");
        }
        System.out.println("Total Value: "+Entrance.getTotalValue());
        System.out.println("Sum of Entrance: "+Entrance.sumEntrance());
    }
}
```

输出
```
entrance 0; value 1
entrance 1; value 2
entrance 3; value 3

entrance 4; value 51
entrance 3; value 49
entrance 2; value 50
Stopping Entrance{currentValue=18, id=3}
Stopping Entrance{currentValue=18, id=2}
Stopping Entrance{currentValue=18, id=4}
Stopping Entrance{currentValue=18, id=0}
Stopping Entrance{currentValue=18, id=1}
all entrance is termination
Total Value: 50
Sum of Entrance: 90
```

#### 代码分析

所有线程的终止，使用类变量cancel的取消标识来控制， 而`awaitTermination(250, TimeUnit.MILLISECONDS)` 方法来判断

是否在等待的时间内线程已终止，如果已终止，则返回true，否则返回false

将`awaitTermination(10, TimeUnit.MILLISECONDS)` ,则输出如下
```
entrance 2; value 47
entrance 4; value 48
all entrance is not termination
Total Value: 48    //发现这个打印在Stopping前面
Sum of Entrance: 92 //发现这个打印在Stopping前面
Stopping Entrance{currentValue=18, id=3}
Stopping Entrance{currentValue=18, id=1}
Stopping Entrance{currentValue=18, id=0}
Stopping Entrance{currentValue=19, id=2}
Stopping Entrance{currentValue=19, id=4}
```

这是因为将等待时间10毫秒后，线程还未终止，所以先打印出main()线程的信息，而后在打印线程的信息


在线程run()方法内，由于使用了共享资源Count对象，因此对count对象的操作方法，并且进行同步控制

假若我们取消Count类的increment方法的关键字 synchronized,则输出如下

```
Total Value: 56
Sum of Entrance: 95
```
这也证明了多线程环境下，对共享资源的操作必须进行锁定，同步控制

Entrance类对变量currentValue的操作， getCurrentValue() 和++currentValue 都是在run方法下执行，

是否也要进行同步控制呢？ 在java编程思想的实例中，是要的，
```java
           synchronized (this){
                ++currentValue;
            }

    public synchronized int getCurrentValue(){
        return currentValue;
    }
```

但是由于currentValue是定义在每个任务对象上的， 因此是不需要做同步控制,去除synchronized关键，测试结果一致

## 中断

我们可以通过中断方式，来终止线程

java提供中断处理方法
```
1 Thread类提供了interrupt()方法的调用
2 如果使用Executor执行器的话，调用shutdowNow(),那么它就发送一个interrupt()给执行器中所有的线程
3 如果想中断某个一个线程，则那么通过Executor来调用submit()而不是execute()方法来启动线程，通过submit()返回Future类
该类的cancel()方法，可以中断这个任务的线程。
```

下面分别演示线程处于运行状态和阻塞状态时进行中断

### 运行时中断


```java
public class NoBlocked implements Runnable {
    @Override
    public void run() {
        while(true)
        System.out.println("NoBlocked is Run()");
    }
}
```

```java
    public static void interrupt(){
        ExecutorService executorService = Executors.newCachedThreadPool();
        Future<?> future= executorService.submit(new NoBlocked());
        System.out.println("NoBlocked is Interrupting");
        try{
            TimeUnit.MILLISECONDS.sleep(1);
        }catch(Exception e){
            System.out.println("NoBlocked exception"+e.getMessage());
        }
        future.cancel(true);
        System.out.println("NoBlocked is Interrupted");
        System.exit(0);
    }
```

输出
```
NoBlocked is Interrupting
NoBlocked is Run()
NoBlocked is Run()
NoBlocked is Run()
NoBlocked is Interrupted
NoBlocked is Run()
```

如果屏蔽`TimeUnit.MILLISECONDS.sleep(1);`代码则直接输出
```
NoBlocked is Interrupting
NoBlocked is Interrupted
```

### 阻塞时中毒

线程会由于多种原因导致进入阻塞状态，而有时我们想终止被阻塞的任务。

有哪些原因呢?
1. 调用sleep(millisecords)使任务进入阻塞状态，在这种情况下，任务在指定时间内不会运行。
2. 调用wait()将线程挂起。直到线程等到通知，调用notify()和notifyAll()方法后，重新进入就绪状态
3. 任务在等待某个输入或输出的完成
4. 任务试图在某个对象上调用同步控制的方法，但是对象锁不可用，因为另外一个任务已经获取到这个对象锁。

注意：早期suspent()和resume() 代码来挂起和唤醒操作不在使用，stop()方法也不会使用，因此不释放锁。


但是不是所有阻塞都能被中断，请看下面实例
```java
public class BlockedSleep implements Runnable {
    @Override
    public void run() {
        try{
            TimeUnit.SECONDS.sleep(100);
        }catch(InterruptedException e){
            System.out.println("BlockedSleep InterruptedException ");
        }
        System.out.println("BlockedSleep exit Run()");
    }
}
```

```java
public class BlockedIO implements Runnable {
    private InputStream in;

    public BlockedIO(InputStream in) {
        this.in = in;
    }

    @Override
    public void run() {
        try {
            System.out.println("BlockedIo waiting for read()");
            in.read();
        } catch (IOException e) {
            if (Thread.currentThread().isInterrupted()){
                System.out.println("BlockedIo isInterrupted");
            }else{
                System.out.println("BlockedIo exception "+ e.getMessage());
            }
        }
        System.out.println("BlockedIo exit Run()");
    }
}
```

```java
public class BlockedSynchronized  implements  Runnable{

    public synchronized void f(){
        while (true) Thread.yield();
    }

    public BlockedSynchronized() {
        new Thread(){
            public void Run(){
                f(); //lock acquired by this thread
            }
        }.start();
    }

    @Override
    public void run() {
        System.out.println("BlockedSynchronized try to call f()");
        f();
        System.out.println("BlockedSynchronized exit Run()");
    }
}
```

```java
public class Test {
    public static void main(String[] args) throws Exception {
        blockedTermination();
    }

    public static void testBlocked(Runnable a){
        ExecutorService executorService = Executors.newCachedThreadPool();
        Future<?> future= executorService.submit(a);
        System.out.println(a.getClass().getSimpleName()+" is Interrupting");
        future.cancel(true);
        System.out.println(a.getClass().getSimpleName()+" is Interrupted");
    }

    public static void blockedTermination() {
        testBlocked(new BlockedSleep());
        testBlocked(new BlockedIO(System.in));
        testBlocked(new BlockedSynchronized());
        try{
            TimeUnit.SECONDS.sleep(3);
        }catch(Exception e){

        }
        System.out.println("Aborting with system.exit(0)");
        System.exit(0);
    }
}
```

输出
```
BlockedSleep is Interrupting
BlockedSleep is Interrupted
BlockedSleep InterruptedException
BlockedSleep exit Run()
BlockedIO is Interrupting
BlockedIO is Interrupted
BlockedSynchronized is Interrupting
BlockedSynchronized is Interrupted
Aborting with system.exit(0)
```

BlockedSleep类已经被中断，并抛出中断异常，而BlockedIO类和BlockedSynchronized类并没有被中断

说明，任务由于sleep()的阻塞是可以被中断的，并且通过异常方式退出线程，

而任务由于等待IO操作的阻塞和等待其他线程释放锁的阻塞都不能被中断。

但是有时，我们确实想让IO读写的阻塞线程中断调，这样其他线程也能对IO设备进行读写操作。

如果做呢？ 通过关闭IO设备资源来使阻塞线程不在阻塞

```java
    public static void IOBlockedInterrupt() {
        ExecutorService executorService = Executors.newCachedThreadPool();
        try{
            new ServerSocket(8080);
            InputStream socketInput = new Socket("localhost", 8080).getInputStream();
            executorService.execute(new BlockedIO(socketInput));
            TimeUnit.MILLISECONDS.sleep(100);
            System.out.println("Shutting down all thread ");
            executorService.shutdownNow();
            TimeUnit.SECONDS.sleep(1);
            System.out.println("Closing "+ socketInput.getClass().getSimpleName());
            socketInput.close();
        }catch(Exception e){
            e.printStackTrace();
        }
    }
```

输出
```
BlockedIo waiting for read()
Shutting down all thread
Closing SocketInputStream
BlockedIo isInterrupted
BlockedIo exit Run()
```

当socketInput关闭后，也就不在从socket 8080读取数据，线程就自动中断。

特别注意：

1. 同一个线程对同一个任务重，不同的synchronized都可以调用的，也就是对象能被同一个线程反复的锁定,而不会被阻塞

```java

```

2. BlockedSynchronized类的阻塞是不能被打断的，但是若使用ReentrantLock上阻塞是可以被中断，并抛出异常。
