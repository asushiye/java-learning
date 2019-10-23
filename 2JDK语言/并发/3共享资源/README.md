# 3共享资源 - java编程 707页

    共享受限资源
    如何引发共享资源问题
    解决共享资源竞争
      synchronized同步
      使用显性Lock对象
      原子性和可视性
        volatile - 原子操作
        原子类 - 原子操作
      临界区 - critical section
      线程本地存储 - ThreadLocal
      终止任务 - java编程 -724页

## 共享受限资源

有了并发就可以同时做多件事情，但是多线程彼此相互干扰的问题也出现，

比如两个线程同时访问一个银行账户，或向同一个打印机打印，改变同一个值等等问题

先看下面实例，共享受限资源问题

## 如何引发共享资源问题
## 解决共享资源竞争
### synchronized同步
### 使用显性Lock对象
### 原子性和可视性
#### volatile - 原子操作
#### 原子类 - 原子操作
定义个获取偶数的共享资源对象，定义多个线程对共享资源进行增量和读取操作，

并判断共享资源的计数值是否为偶数，如果不是，则退出线程执行。

1. 定义抽象共享资源类
2. 定义获取整数共享资源类
3. 定义要执行的任务
4. 10个线程并发执行任务

定义抽象共享资源对象
```java
public abstract class AbstractGenerator<T> {
    private volatile  boolean cancel = false;
    public void cancel(){
        this.cancel = true;
    }
    public boolean isCancel(){
        return this.cancel;
    }

    public abstract T next();
}
```
定义获取整数共享资源对象
```java
public class IntGenerator extends AbstractGenerator<Integer> {
    private Integer count = 0;
    @Override
    public Integer next() {
        ++count;  //多线程 - 危险的操作
//        Thread.yield();
        ++count;
        return count;
    }
}
```

定义线程要执行的任务
```java
public class EventJob implements Runnable {
    private AbstractGenerator<Integer> generator;

    public EventJob(AbstractGenerator<Integer> generator) {
        this.generator = generator;
    }

    @Override
    public void run() {
        while (!generator.isCancel()) {
            Integer index = generator.next();
            if (index % 2 != 0) {
                System.out.println(Thread.currentThread().getName() + " not event; value:"+index);
                generator.cancel();
            }
        }
    }
}
```

10个线程并发执行任务
```java
public class Test {
    public static void main(String[] args) {
        eventInt();
    }
    public static void eventInt() {
        ExecutorService executorService = Executors.newCachedThreadPool();

        IntGenerator intGenerator = new IntGenerator();
        for (int i = 0; i < 10; i++) {
            executorService.execute(new EventJob(intGenerator));
        }
        executorService.shutdown();
    }
}
```

输出
```
pool-1-thread-1 not event; value:48773
pool-1-thread-2 not event; value:48771
```

在测试代码中，创建10个线程，同时对一个资源intGenerator的整数count，都增量和读取操作。

并检测每个数值是否为偶数，如果不是就会打印错误信息，并且退出线程执行。

上面实例，有10个线程共享操作IntGenerator 对象，极有可能导致一个任务可以在另外一个任务执行第一个++count后，就执行next()方法。

在共享资源对象next()方法中添加 `Thread.yield();` 来更快，更频繁切换线程来执行count增量计数

这样能更快输出非偶数，并且更改取消状态，跳出循环。

这种多线程操作共享资源，导致资源竞争问题。

## 解决共享资源竞争

对并发工资，我们需要某种方式来防止两个任务访问相同的资源，至少在关键阶段不能出现这种情况。

防止这种冲突的方法就是资源被一个任务使用时，在其加上锁
```
在第一个访问资源的线程，先锁定这个资源，使其他线程在第一个线程未解锁之前就无法使用，
若第一个线程完全解锁了，则其他线程可以锁定并访问资源。
```

就像使用浴室一样，多个人希望单独使用浴室
```
所有的人拥在浴室门口，如果浴室没人，则最靠近门口的人进入浴室，并锁上门。
这时其他人要使用浴室的话，就被阻挡了，直到浴室使用完毕。浴室门锁打开并离开。
另外一个离门口最近的人，就可以进入浴室并锁上门
```
浴室就是共享资源，多个人同时需要使用时，则使用锁的机制来实现每个人独享浴室的功能

也许你觉得可以使用yield()方法或setPriority()方法来影响线程调度器，调整线程执行，但是这样的调整未必会有多大效果

因此所有并发模式在解决线程冲突，资源共享问题的时候，都是采用**序列化访问共享资源**的方案，

在某个时刻，只能有一个线程访问共享资源，通常做法给访问共享资源的方法上，添加锁语句来实现，

使的这个时刻只能有一个线程在运行这个代码，这种**锁的机制，也叫互斥量**

### synchronized同步

java提供关键字**synchronized**的形式，防止资源冲突提供内置支持，

它用于保护的代码片段，检测资源没有被锁，则加锁，执行代码，释放锁。若已锁，则等待直到释放锁

共享资源以对象形式存在，要控制共享资源，得先包装进一个对象中，然后将要访问这个资源的方法标记为synchronized

那么在这个线程从该方法返回之前，其他线程调用这个方法都被阻塞。

对访问共享资源的方法，添加synchronized关键字
```java
public class IntGenerator extends AbstractGenerator<Integer> {
    private Integer count = 0;
    @Override
    public synchronized Integer next() {
        ++count;
//        Thread.yield();
        ++count;
        return count;
    }
}
```

从新执行发现所有线程读取资源都是整数，线程永远都不会退出。

### 使用显性Lock对象

Lock对象必须显示创建，锁定，释放来使用，因此它与内建锁比代码缺乏优雅性。
```java
public class LockIntGenerator extends AbstractGenerator<Integer> {
    private Integer count = 0;
    private Lock lock = new ReentrantLock();
    @Override
    public Integer next() {
        lock.lock();
        try{
            ++count;
//        Thread.yield(); 这个在锁下，已经没用
            ++count;
            return count;
        }finally{
            lock.unlock();
        }
    }
}
```

在共享资源中创建Lock对象来添加一个互斥调用的锁，在访问共享资源方法中，使用lock()方法和unlock()方法来创建临界资源

```java
    public static void eventLockInt() {
        ExecutorService executorService = Executors.newCachedThreadPool();

        AbstractGenerator<Integer> intGenerator = new LockIntGenerator();
        for (int i = 0; i < 10; i++) {
            executorService.execute(new EventJob(intGenerator));
        }
        executorService.shutdown();
    }
```

1. 使用**synchronized**关键字时，需要些代码少，并且用户错误出现可用性也会降低，若失败，则抛出异常，就没有机会做清理工资
2. Lock显性对象，使用try-fianlly的代码比synchronized代码多，finally可以清理工作，ReentrantLock 允许你试着去获取但最终未获取锁，这样你可以立马去做其他事情


### 原子性和可视性

java并发原子性是指不会被线程调度机制打断的操作；这种操作一旦开始，就一直运行到结束；比如赋值操作和返回操作

对基本类型的变量读取和写入操作时，原子性就保证变量是整体操作内存，默认情况下除了64位的long和double基本类型都是原子性操作。

对应64位的long和double变量操作时，会分解两个32位操作来执行，这就产生一个读取和写入操作中间发生上下文切换。

刚写入第一个32位的值时，另外一个线程已经在读取这个变量，那么值就错了。

如果我们使用**volatile**关键字，就保证变量的原子性操作时写入和读取数据一致。

volatile还保证可视性，在写入时将数据保存到主存上，保证了读取数据也正确的

因此多cpu时，不同线程中缓存数据是不一致的，只有主存数据一致，而volatile数据写入和读取都是从主存来。


一个常不正确的认识“原子操作不需要同步控制”，这是很危险的

#### volatile - 原子操作

```java
public class AtomJob implements Runnable {
    private int i=0;
    public int getValue(){
        return i;
    }
    public synchronized void increment(){
        i++;
        i++;
    }
    @Override
    public void run() {
        while(true){
            increment();
        }
    }
}
```

```java
    public static void atomTest() {
        ExecutorService executorService = Executors.newCachedThreadPool();
        AtomJob atomJob = new AtomJob();
        executorService.execute(atomJob);
        while(true){
            int index= atomJob.getValue();
            if (index % 2 != 0){
                System.out.println(index);
                System.exit(0);
            }
        }
    }
```

该程序很快就找到奇数，并终止程序执行

尽管getValue()的 return i;是原子操作，但是缺少同步使的数值可以处于不稳定的中间状态被读取。

我们保证getValue() 和 increment()都是同步的 synchronized

另外由于i不是volatile，在多CPU下存在可视化问题，多线程读写不一致

```java
public class AtomJob implements Runnable {
    private volatile int i=0;
    public synchronized int getValue(){
        return i;
    }
    ...
}
```

#### 原子类 - 原子操作

javaSE5，提供很多原子类AtomicInterger, AtomicLong 等等

使用原子类替代基本类型包装了，可以去除synchronized 同步

```java
public class AtomClassJob implements Runnable {
    private AtomicInteger i= new AtomicInteger(0);
    public int getValue(){
        return i.get();
    }
    public void increment(){
        i.addAndGet(2);
    }
    @Override
    public void run() {
        while(true){
            increment();
        }
    }
}
```

```java
    public static void atomClassTest() {
        new Timer().schedule(new TimerTask() {
            @Override
            public void run() {
                System.err.println("Aborting");
                System.exit(0);
            }
        }, 5000);

        ExecutorService executorService = Executors.newCachedThreadPool();
        AtomClassJob atomJob = new AtomClassJob();
        executorService.execute(atomJob);
        while(true){
            int index= atomJob.getValue();
            if (index % 2 != 0){    //由于保证原子性操作，这段代码基本不会再执行
                System.out.println(index);
                System.exit(0);
            }else{
                System.out.println(index);
            }
        }
    }
```

### 临界区 - critical section

有时你只希望防止多个线程同时访问方法内部的部分代码而不是整个方法。对这部分代码称为临界区(critical section)

它也使用**synchronized** 来建立，这里synchronized需要指定某个对象，此对象锁是对花括号的代码进行同步控制

```java
   synchronized(syncObject){
     代码块
   }
```

在进入代码块前，需要从对象获取锁，如果其他线程已经得到这个锁，那么就必须等到锁释放，才能获取锁，并创建临这个代码块的临界区


临界区对代码块同步控制，比对整个方法同步控制，线程并发量更大。


一般情况下，我们都对本对象来设置临界区
```java
   synchronized(this){
     代码块
   }
```

但是也可以对另外一个对象设置临界区，只要保证另外一个对象是在本对象上定义的。

### 线程本地存储 - ThreadLocal
防止共享资源的冲突的第二种方法是根除对变量的共享，使用每个变量在每个线程中都有存储。

java提供线程本地存储功能，ThreadLocal

 定义可以存储在本地的共享资源
```java
public class ThreadLocalVariableHolder {
    private static ThreadLocal<Integer> value = new ThreadLocal<Integer>(){
        protected synchronized Integer initialValue(){
            return 0;
        }
    };

    public static int get(){
        return value.get();
    }

    public static void increment(){
        value.set(value.get()+1);
    }
}
```

```java
public class ThreadLocalJob implements Runnable {
    private final int id;
    public ThreadLocalJob(int id) {
        this.id = id;
    }

    @Override
    public void run() {
        while(!Thread.currentThread().isInterrupted()){
            ThreadLocalVariableHolder.increment();
            System.out.println(this);
            Thread.yield();
        }
    }

    @Override
    public String toString() {
        return "id"+ id+" value: "+ThreadLocalVariableHolder.get();
    }
}
```

```java
    public static void threadLocal() {
        new Timer().schedule(new TimerTask() {
            @Override
            public void run() {
                System.err.println("Aborting");
                System.exit(0);
            }
        }, 1000);

        ExecutorService executorService = Executors.newCachedThreadPool();
        for (int i = 0; i < 5; i++) {
            executorService.execute(new ThreadLocalJob(i));
        }
        executorService.shutdown();
    }
```

使用Threadlocal保证资源不会出现竞争。

### 终止任务
