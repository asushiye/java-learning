# 6死锁
    什么是死锁
    死锁如何形成
    死锁如何防止

## 什么是死锁

线程间相互等待的连续循环，没有哪个线程能继续，这称为死锁。

A线程等待B线程释放锁，而B线程等待线程C释放锁，可是C线程又等待A线程是否锁，这种循环导致每一个线程都处于阻塞状态

这种死锁，可能看起来程序运行良好，但是任务就不执行，不处理数据，这种缺陷在生产环境很难察觉和追踪。

**因此在并发编程时，仔细的程序设计防止死锁发生，这很重要**


## 死锁如何形成

下面通过一个经典死锁案例，哲学家就餐
```
哲学家们围着餐桌而坐，他们有时处于思考，有时处于就餐，思考时不需要共享资源，就餐需要共享资源筷子,
哲学家很穷，因此每个哲学家各配一个筷子，

下面使用5个哲学家使用5个筷子来就餐,
他们围着桌子就餐，每个人左右两边各放一个筷子，需要就餐时，必须同时使用左右两边的筷子，
这时左边或右边的哲学家只能处于等待，直到可以使用筷子
```

这个示例就可能出现死锁问题，当每个哲学家都不思考，同时拿取右边的筷子时，

所有对应的左边的哲学家，就只能等待右边的哲学家放下筷子，才能吃饭，

这就造成所有哲学家等待另外一个哲学家，形成连续循环的等待，导致死锁

```java
package concurrent.deadlock;

public class Chopstick {
    private boolean taked = false;
    public synchronized void take() throws InterruptedException {
        while (taked)
            wait();
        taked = true;
    }

    public synchronized void drop(){
        taked = false;
        notifyAll();
    }
}
```

```java
public class Philosopher implements Runnable {
    private Chopstick right;
    private Chopstick left;
    private final int id;
    private final int ponder;
    private Random random = new Random(47);

    public Philosopher(Chopstick right, Chopstick left, int id, int ponder) {
        this.right = right;
        this.left = left;
        this.id = id;
        this.ponder = ponder;
    }

    private void pause() throws InterruptedException {
        if (ponder == 0) {
            return;
        } else {
            TimeUnit.MILLISECONDS.sleep(random.nextInt(ponder * 250));
        }
    }

    @Override
    public void run() {
        try{
            while(!Thread.interrupted()){
                System.out.println(this+" thinking");
                pause();
                System.out.println(this+" take right");
                right.take();
                System.out.println(this+" take left");
                left.take();
                pause();
                System.out.println(this+" drop right");
                right.drop();
                System.out.println(this+" drop left");
                left.drop();
            }
        }catch(InterruptedException e){
            System.out.println(this+" interrupted");
        }
    }

    @Override
    public String toString() {
        return "Philosopher "+id;
    }
}
```

```java
public class Test {
    public static void main(String[] args) throws InterruptedException{
        int size = 5;
        Chopstick[] chopsticks = new Chopstick[size];
        for (int i = 0; i < size; i++) {
            chopsticks[i] = new Chopstick();
        }
        ExecutorService executorService = Executors.newCachedThreadPool();
        for (int i = 0; i < size; i++) {
            executorService.execute(new Philosopher(chopsticks[i], chopsticks[(i + 1) % size], i, 0));
        }
        TimeUnit.SECONDS.sleep(5);
        executorService.shutdownNow();
    }
}
```

## 死锁如何防止

我们先总结下死锁形成4个要输

1. 互斥条件。共享资源在一个时刻，只能被一个线程锁定; 也就是一个筷子，只能被一个哲学家使用
2. 一个线程操作两个以上共享资源。一个线程已经持有并锁定一个资源，同时它需要等待获取另一个已被其他线程锁定的资源
3. 资源不能被线程抢占。被锁定的资源，不能再被其他线程锁定
4. 必须由循环等待，一个线程等待其他线程的持有的资源，而后者线程又等待另个一个线程所有持有的资源，这样一直下去，直到一个线程等待第一个线程持有的资源，使的所有线程被锁住


若要发生死锁，就需要满足上面四个条件，因此只要让四个条件，任意其中一个不满足，则就不会造成死锁。

下面通过控制第4点，防止循环等待。

防止在同一个时间，每个哲学家都拿取右边的筷子再取左边的筷子

先让哲学家都拿取右边的筷子再取左边的筷子，不过最后一个哲学家时，先拿取左边的筷子，再拿右边的筷子
```java
    public static void fixDiningPhilosopher() throws InterruptedException{
        int size = 5;
        int ponder = 0;
        Chopstick[] chopsticks = new Chopstick[size];
        for (int i = 0; i < size; i++) {
            chopsticks[i] = new Chopstick();
        }
        ExecutorService executorService = Executors.newCachedThreadPool();
        for (int i = 0; i < size; i++) {
            if (i == size-1) {
                executorService.execute(new Philosopher(chopsticks[i], chopsticks[(0)], i, ponder));
            }else {
                executorService.execute(new Philosopher(chopsticks[i], chopsticks[(i + 1) ], i, ponder));
            }
        }
        TimeUnit.SECONDS.sleep(5);
        executorService.shutdownNow();
    }
```
这样就可以移除死锁，程序能平滑执行
