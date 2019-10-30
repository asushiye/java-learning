# 1倒数锁存器

    概述
    最佳实践

## 概述

java.util.concurrent引入大量新类，来解决并发编程的问题。

其中倒数锁存器CountDownLatch 使我们本章要讲解

倒数的锁存器用于简单控制，一部分线程执行完后，才能执行其他线程

它同步机制是CountDownLatch创建时，初始化计数值，比如5，

可以创建5个线程，当线程的任务执行结束时调用CountDownLatch.countDown(),将计数值减一，

另外创建2个线程，调用CountDownLatch.await()挂起任务，

当锁存器的计数值为0时，这两个线程则被唤醒执行


## 最佳实践

```java
package concurrent.latch;

import java.util.Random;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

/**
 * @author : zhenyun.su
 * @comment :
 * @since : 2019-10-29
 */

public class TaskPortionJob implements  Runnable{
    private static int counter = 0;
    private final int id = counter++;
    private CountDownLatch latch;
    private Random random = new Random(47);
    public TaskPortionJob(CountDownLatch latch) {
        this.latch = latch;
    }
    @Override
    public void run() {
        try{
            doWork();
            latch.countDown();
            System.out.println("; CountDownLatch "+latch.getCount());
        }catch(InterruptedException e){
            System.out.println(this+" interrupted");
        }
    }

    private void doWork() throws InterruptedException{
        TimeUnit.MILLISECONDS.sleep (random.nextInt(500));
        System.out.print(this + " completed");
    }

    @Override
    public String toString() {
        return "TaskPortionJob "+id;
    }
}
```

```java
public class TaskWaitJob implements  Runnable{
    private static int counter = 0;
    private final int id = counter++;
    private CountDownLatch latch;
    private Random random = new Random(47);
    public TaskWaitJob(CountDownLatch latch) {
        this.latch = latch;
    }
    @Override
    public void run() {
        try{
            latch.await();
            System.out.println(this+ "");
        }catch(InterruptedException e){
            System.out.println(this+" interrupted");
        }
    }
    @Override
    public String toString() {
        return "TaskWaitJob "+id;
    }
}
```


```java
public class Test {
    public static void main(String[] args) {
        ExecutorService executorService = Executors.newCachedThreadPool();
        int count = 5;
        CountDownLatch latch= new CountDownLatch(count);
        for (int i = 0; i < 2; i++) {
            executorService.execute(new TaskWaitJob(latch));
        }
        for (int i = 0; i < count; i++) {
            executorService.execute(new TaskPortionJob(latch));
        }
        System.out.println("launched all tasks");
        executorService.shutdown();
    }
}
```

创建两个线程，并调用await挂起，另外创建5个线程并立马执行

```
launched all tasks
TaskPortionJob 0 completed; count 4
TaskPortionJob 1 completed; count 3
TaskPortionJob 2 completed; count 2
TaskPortionJob 3 completed; count 1
TaskPortionJob 4 completed; count 0
TaskWaitJob 1
TaskWaitJob 0
```

CountDownLatch被用来触发一次，计数值是不能被重置的

## CyclicBarrier

和CountDownLatch类似，不过CountDownLatch 只触发一次的事件，而CyclicBarrier可以多次重用
