# 6线程协作 java编程思想 735页

    概念
    线程挂起和线程解挂
      wait()与notifyAll()
      notify()与notifyAll()
      线程信号消失
    最佳实践
      生产者和消费者 - wait()&notifyAll
      生产者和消费者 - BlockingQueue
      生产者和消费者 - PipeWriter&PipeReader

## 概念

现在我们更近一步来学习多线程的并发编程，一个任务可以分解为多个任务，然后并发执行共享资源，以提高执行效率

对应汽车来说，长时间使用，导致汽车漆面氧化，老化，表面不在光亮，这时可以在4S店进行打蜡并抛光（实际上不建议打蜡和抛光）

对应打蜡和抛光我们可以交由两个线程来完成，一个线程负责打蜡，另外一个线程负责抛光，共享资源为汽车

假如每次的流程都是抛光后，再打蜡


## 线程挂起和线程解挂

1. wait()与notifyAll()
2. notify()与notifyAll()
3. 线程信号消失

### wait()与notifyAll()

wait()将本线程挂起，释放对象锁，在挂起期间对象的锁都是释放的，

wait()无参数时，无限的挂起，直到线程接收到notify() 或notifyAll()消息，才解挂
wait(long timeout) 在超时时间内，挂起，时间到自动解挂

notifyAll()唤醒对相同对象操作的所有挂起的线程

wait()，notify()及notifyAll()方法来源Object，而不是Thread类，是因为锁最终作用在所有对象上，

这样这些方法可以在任何synchronized中被调用

若没有定义在同步方法或同步代码块中，则运行时报错`Exception in thread "Thread-0" java.lang.IllegalMonitorStateException`


```java
public class Car {
    private String step = "wax"; //当前执行 polish 抛光，wax 打蜡
    public synchronized void waxed(){
        this.step= "polish";
        notifyAll();
    }
    public synchronized void polished(){
        this.step= "wax";
        notifyAll();
    }
    public synchronized  void waitForWaxing() throws InterruptedException {
        while("wax".equals(step)){
            wait();
        }
    }
    public synchronized  void waitForPolishing() throws InterruptedException {
        while("polish".equals(step)){
            wait();
        }
    }
}
```

打蜡任务
```java
public class WaxJob implements Runnable {
    private Car car;
    public WaxJob(Car car) {
        this.car = car;
    }

    @Override
    public void run() {
        try{
            while(!Thread.interrupted()){
                System.out.println("Car is waxing");   //完成汽车打蜡
                TimeUnit.MICROSECONDS.sleep(200);   // 阻塞200微妙
                car.waxed();   //打蜡完成，改成抛光状态，并通知Car所有线程，已经挂起线程进行解挂
                car.waitForPolishing(); //若是抛光状态，则挂起本线程，释放锁，若打蜡状态，则执行打蜡动作
            }
        }catch(InterruptedException e){
            System.out.println("WaxJob, exception:"+e.getMessage());
        }
        System.out.println("WaxJob, Car finished waxing");
    }
}
```

抛光任务
```java
public class PolishJob implements Runnable {
    private Car car;
    public PolishJob(Car car) {
        this.car = car;
    }

    @Override
    public void run() {
        try{
            while(!Thread.interrupted()){
                System.out.println("Car is polishing");
                car.waitForWaxing(); //若是打蜡状态，则挂起本线程，释放锁，若抛光状态，则执行抛光动作
                TimeUnit.MICROSECONDS.sleep(200);
                car.polished();  //抛光完成，改成打蜡状态，并通知Car所有线程,已挂起线程将解挂
            }
        }catch(InterruptedException e){
            System.out.println("PolishJob, exception:"+e.getMessage());
        }
        System.out.println("PolishJob, Car finished polishing");
    }
}
```

测试代码
```java
    public static void carWaxAndPolish() {
        ExecutorService executorService = Executors.newCachedThreadPool();
        Car car = new Car();
        executorService.execute(new PolishJob(car));
        executorService.execute(new WaxJob(car));

        try{
            TimeUnit.MILLISECONDS.sleep(10);
        }catch(Exception e){
            //
        }
        executorService.shutdownNow();
    }
```

输出
```
Car is polishing
Car is waxing
Car is polishing
Car is waxing
Car is polishing
Car is waxing
WaxJob, exception:null
WaxJob, Car finished waxing
PolishJob, exception:sleep interrupted
PolishJob, Car finished polishing
```

### notify()与notifyAll()

如果有多个线程在单个Car对象上处于wait()状态，这时使用notifyAll()比notify()更安全，能保证挂起的线程都被唤醒。

由于上面只有一个线程对Car对象做挂起，因此你可以使用notify代替notifyAll，不过使用notify()是一种优化，

使用notify()需要注意的是
```
在多个线程等待同一个锁的中，只有一个线程被唤醒
所有线程建议有相同的等待条件，则才能唤醒恰当的线程
```

并发编程也有一个困惑是：notifyAll()将唤醒"所有等待的线程",

这是否意味着在程序的任何地方，任何处于挂起状态中的线程都会被任何调用notifyAll()唤醒呢？

在上面汽车抛光和打蜡实例基础上，新增一个加油动作，假设加油也只能在打蜡之后才能执行，加油和抛光可以并发执行

#### 定义加油任务
在汽车加油结束后，将状态改为需要打蜡动作，并通知其他线程解挂，这里为了方便测试，不需要解挂动作

```java
public class OilJob implements Runnable {
    private Car car;
    public OilJob(Car car) {
        this.car = car;
    }

    @Override
    public void run() {
        try{
            while(!Thread.interrupted()){
                System.out.println("Car is oiling");
                car.waitForWaxing();
                TimeUnit.MICROSECONDS.sleep(200);
                car.polished();  //加油后动作和抛光一样，因此调用已抛光， 可以在car定义一个完成加油的动作
            }
        }catch(Exception e){
            System.out.println("OilJob, exception:"+e.getMessage());
        }
        System.out.println("OilJob, Car finished polishing");
    }
}
```

通过下面两个实例来测试对比

#### 正对同一个对象，加油线程挂起动作
```java
    public static void carWaxAndPolishAndOil() {
        ExecutorService executorService = Executors.newCachedThreadPool();
        Car car = new Car();
        executorService.execute(new PolishJob(car));
        executorService.execute(new WaxJob(car));
        executorService.execute(new OilJob(car));
        try{
            TimeUnit.MILLISECONDS.sleep(10);
        }catch(Exception e){
            //
        }
        executorService.shutdownNow();
    }
```
输出
```
Car is polishing
Car is oiling
Car is waxing
Car is oiling
Car is polishing
Car is waxing
Car is polishing
Car is oiling
Car is waxing
Car is oiling
Car is polishing
Car is waxing
Car is oiling
Car is polishing
PolishJob, exception:null
PolishJob, Car finished polishing
OilJob, exception:null
OilJob, Car finished polishing
WaxJob, exception:sleep interrupted
WaxJob, Car finished waxing
```

抛光，加油，打蜡轮流完成，

说明正对同一个对象的不同线程PolishJob和OilJob挂起，都能被WaxJob的notifyAll()解挂

#### 正对不同一个对象，加油线程挂起动作
```java
    public static void carWaxAndPolishAndOil() {
        ExecutorService executorService = Executors.newCachedThreadPool();
        Car car = new Car();
        executorService.execute(new PolishJob(car));
        executorService.execute(new WaxJob(car));
        Car car2 = new Car();
        executorService.execute(new OilJob(car2));
        try{
            TimeUnit.MILLISECONDS.sleep(10);
        }catch(Exception e){
            //
        }
        executorService.shutdownNow();
    }
```

输出
```
Car is polishing
Car is oiling
Car is waxing
Car is polishing
Car is waxing
Car is polishing
Car is waxing
Car is polishing
Car is waxing
OilJob, exception:null
OilJob, Car finished polishing
WaxJob, exception:null
WaxJob, Car finished waxing
PolishJob, exception:sleep interrupted
PolishJob, Car finished polishing
```

只有在第一次是有加油动作，其他时候，即使WaxJob线程解挂了，还是不能加油，

说明
```
针对不同的对象，不同线程PolishJob和OilJob即使都挂起了，虽然WaxJob调用notifyAll()来解挂，
也只能解挂成功同一个对象的PolishJob线程
而另外一个对象Car2的OilJob线程不能被解挂
```

### 线程信号消失

```java
T1:
synchronized(sharedMonitor){
  //setup condition for T2
  sharedMonitor.notifyAll();
}

T2:
while(someCondition){
  //do something
  synchronized(sharedMonitor){
    sharedMonitor.wait();
  }
}
```

执行T2时，someCondition条件满足后，执行do smoething代码，

这时由于不在同步控制范围内，有可能cpu切换执行另外T1的代码

T1为T2修改条件并继续执行notifyAll(),此时对T2已经太晚，

会麻木进入wait()状态，而T1的notifyAll()已经不在执行，最终到死锁

```java
T2:
synchronized(sharedMonitor){
  while(someCondition){
    sharedMonitor.wait();
  }
}
```

## 最佳实践

### 生产者和消费者 - wait()&notifyAll
```
以一个餐厅为例，它有一个厨师和一个服务员，这个服务员必须等待厨师准备好膳食，
当厨师准备好膳食后，会通知服务员并且等待服务上菜。
当服务员上好菜后，会通知厨师准备膳食并且等待膳食完成。
```

这是一个针对膳食的多线程协作示例，厨师代表生产者，服务员代表消费者，

两个线程在膳食被生产和被消费时进行握手，而且系统已准备5份膳食方式来关闭。

定义膳食
```java
public class Meal {
    private final int id;
    public Meal(int id) {
        this.id = id;
    }
    @Override
    public String toString() {
        return "Meal "+ id;
    }
}
```

定义服务员
```java
package concurrent.productorconsumer;

/**
 * @author : zhenyun.su
 * @comment :
 * @since : 2019-10-25
 */

public class WaitPerson implements Runnable {
    private Restaurant restaurant;
    public WaitPerson(Restaurant restaurant) {
        this.restaurant = restaurant;
    }

    @Override
    public void run() {
        try{
            while(!Thread.interrupted()){
                synchronized (this){
                    while(restaurant.meal == null){
                        wait();
                    }
                }
                System.out.println("WaitPerson got "+restaurant.meal.toString());

                synchronized (restaurant.chef){
                    restaurant.meal = null;
                    restaurant.chef.notifyAll();
                }
            }
        }catch(InterruptedException e){
            System.out.println("WaitPerson InterruptedException:"+e.getMessage());
        }
    }
}
```

定义厨师
```java
package concurrent.productorconsumer;

public class Chef implements  Runnable {
    private Restaurant restaurant;
    private volatile int count = 0;
    public Chef(Restaurant restaurant) {
        this.restaurant = restaurant;
    }

    @Override
    public void run() {
        try{
            while(!Thread.interrupted()){
                synchronized (this){
                    while(restaurant.meal != null){  //使用while写法，保证信号不会丢失
                        wait();
                    }
                }

                if (++count > 5){
                    System.out.println("Out of food, closing!");
                    restaurant.executorService.shutdownNow();
                }else{
                    System.out.print("Order up, chef has completed "+count+" meal, ");
                }

                synchronized (restaurant.waitPerson){
                    restaurant.meal = new Meal(count);
                    restaurant.waitPerson.notifyAll();
                }
            }
        }catch(InterruptedException e){
            System.out.println("Chef InterruptedException:"+e.getMessage());
        }
    }

}
```
当厨师准备好膳食后，就挂起本线程，若没有准备好膳食，则准备膳食并唤醒服务员线程的挂起。

这个notifyAll()只能唤醒被锁定的waitPerson对象的并挂起线程


定义餐厅
```java
package concurrent.productorconsumer;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Restaurant {
    public Meal meal;
    public WaitPerson waitPerson = new WaitPerson(this);
    public Chef chef = new Chef(this);
    ExecutorService executorService = Executors.newCachedThreadPool();

    public Restaurant() {
        executorService.execute(waitPerson);
        executorService.execute(chef);
    }

    public static void main(String[] args) {
        new Restaurant();
    }
}

```

输出
```
Order up, chef has completed 1 meal, WaitPerson got Meal 1
Order up, chef has completed 2 meal, WaitPerson got Meal 2
Order up, chef has completed 3 meal, WaitPerson got Meal 3
Order up, chef has completed 4 meal, WaitPerson got Meal 4
Order up, chef has completed 5 meal, WaitPerson got Meal 5
Out of food, closing!
WaitPerson InterruptedException:null
Chef InterruptedException:sleep interrupted
```

### 生产者和消费者 - BlockingQueue

下面我们使用实例，使用BlockingQueue来替代生产消费模式下的wait()和notifyAll()

通过四个线程完成，吐司制作，涂抹黄油，涂抹果酱，吃掉吐司

1. 定义共享资源，吐司Toast
2. 不断的生产吐司任务
3. 不断消费刚生产吐司涂抹黄油
4. 不断消费黄油吐司涂抹果酱
5. 将制作好的吐司吃掉
6. 编写测定代码
7. 结论

定义共享资源，吐司Toast
```java
public class Toast {
    public enum Status {DRY,BUTTERED, JAMMED};
    private Status status = Status.DRY;
    private final  int id;
    public Toast(int id) {
        this.id = id;
    }
    public void buttered(){
        status = Status.BUTTERED;
    }
    public void jammed(){
        status= Status.JAMMED;
    }

    public int getId() {
        return id;
    }
    public Status getStatus() {
        return status;
    }
    @Override
    public String toString() {
        return "Toast "+id +": "+ status;
    }
}

```

不断的生产吐司任务
```java
public class DryJob implements Runnable{
    private ToastQueue toastQueue;
    private int count = 0;
    private Random random = new Random(47);

    public DryJob(ToastQueue toastQueue) {
        this.toastQueue = toastQueue;
    }

    @Override
    public void run() {
        try{
            System.out.println("DryJob start");
            while(!Thread.interrupted()){
                TimeUnit.MILLISECONDS.sleep(100+random.nextInt(500));
                Toast t= new Toast(count++);
                System.out.println("DryJob"+ t.toString());
                toastQueue.put(t);
            }
            System.out.println("DryJob success");
        }catch(InterruptedException ie){
            System.out.println("DryJob interrupted");
        }catch (Exception e){
            System.out.println("DryJob exception "+e.getMessage());
        }
        System.out.println("DryJob  finish");
    }
}
```

不断消费刚生产吐司涂抹黄油
```java
public class ButterJob implements Runnable{
    private ToastQueue dryQueue, butterQueue;
    public ButterJob(ToastQueue dryQueue, ToastQueue butterQueue) {
        this.dryQueue = dryQueue;
        this.butterQueue = butterQueue;
    }

    @Override
    public void run() {
        try{
            System.out.println("ButterJob start");
            while(!Thread.interrupted()){
                //若dryQueue队列无元素，则本线程阻塞
                Toast t= dryQueue.take();
                t.buttered();
                System.out.println("ButterJob "+t.toString());
                butterQueue.put(t);
            }
            System.out.println("ButterJob success");
        }catch(InterruptedException ie){
            System.out.println("ButterJob interrupted");
        }catch (Exception e){
            System.out.println("ButterJob exception "+e.getMessage());
        }
        System.out.println("ButterJob  finish");
    }
}
```
不断消费黄油吐司涂抹果酱
```java
public class JammerJob implements Runnable{
    private ToastQueue butterQueue, jammerQueue;
    public JammerJob(ToastQueue butterQueue, ToastQueue jammerQueue) {
        this.butterQueue = butterQueue;
        this.jammerQueue = jammerQueue;
    }

    @Override
    public void run() {
        try{
            System.out.println("JammerJob start");
            while(!Thread.interrupted()){
                //若dryQueue队列无元素，则本线程阻塞
                Toast t= butterQueue.take();
                t.jammed();
                System.out.println("JammerJob"+t.toString());
                jammerQueue.put(t);
            }
            System.out.println("JammerJob success");
        }catch(InterruptedException ie){
            System.out.println("JammerJob interrupted");
        }catch (Exception e){
            System.out.println("JammerJob exception "+e.getMessage());
        }
        System.out.println("JammerJob  finish");
    }
}
```

将制作好的吐司吃掉
```java
public class EatJob implements Runnable{
    private ToastQueue jammerQueue;
    private int count = 0;
    private Random random = new Random(47);
    public EatJob(ToastQueue jammerQueue) {
        this.jammerQueue = jammerQueue;
    }

    @Override
    public void run() {
        try{
            System.out.println("EatJob start");
            while(!Thread.interrupted()){
                Toast t= jammerQueue.take();
                if (t.getId() !=count++ ||t.getStatus() !=Toast.Status.JAMMED){
                    System.out.println("EatJob toast status error:" +t);
                    System.exit(1);
                }else{
                    System.out.println("EatJob "+t+" , toast is finally");
                }
            }
            System.out.println("EatJob success");
        }catch(InterruptedException ie){
            System.out.println("EatJob interrupted");
        }catch (Exception e){
            System.out.println("EatJob exception "+e.getMessage());
        }
        System.out.println("EatJob  finish");
    }
}
```

编写测定代码
```java
public class Test {
    public static void main(String[] args) {
        toastBlockingQueue();
    }

    public static void toastBlockingQueue() {
        ToastQueue dryQueue = new ToastQueue(),
                butterQueue = new ToastQueue(),
                jammerQueue = new ToastQueue();
        ExecutorService executorService = Executors.newCachedThreadPool();
        executorService.execute(new DryJob(dryQueue));
        executorService.execute(new ButterJob(dryQueue,butterQueue));
        executorService.execute(new JammerJob(butterQueue,jammerQueue));
        executorService.execute(new EatJob(jammerQueue));
        try{
            TimeUnit.SECONDS.sleep(1);
            executorService.shutdownNow();
        }catch(Exception e){
        }
    }
}
```

输出
```
DryJob start
ButterJob start
JammerJob start
EatJob start
DryJobToast 0: DRY
ButterJob Toast 0: BUTTERED
JammerJobToast 0: JAMMED
EatJob Toast 0: JAMMED , toast is finally
DryJobToast 1: DRY
ButterJob Toast 1: BUTTERED
JammerJobToast 1: JAMMED
EatJob Toast 1: JAMMED , toast is finally
DryJobToast 2: DRY
ButterJob Toast 2: BUTTERED
JammerJobToast 2: JAMMED
EatJob Toast 2: JAMMED , toast is finally
ButterJob interrupted
ButterJob  finish
JammerJob interrupted
JammerJob  finish
EatJob interrupted
EatJob  finish
DryJob interrupted
DryJob  finish
```

#### 结论

在上面代码中，没有任何锁存在 即Lock和synchronized存在，通过多个阻塞队列来隐性实现多线程的同步逻辑

保证了每个Toast在任何时刻都只有一个线程任务在操作，

利用阻塞队列，若没有吐司时，则本线程自动挂起；若插入吐司时，则本线程自动唤醒

使用BlockingQueue好处，时同步逻辑更加简化，也消除了使用wait()和notifyAll()时类与类之间的耦合。

### 生产者和消费者 - PipeWriter&PipeReader

下面通过IO的写入管道和读取管道来实现类似阻塞队列的生产者和消费者模式的，多线程同步操作

通过管道来完成写入任务
```java
package concurrent.io;

public class WriteJob implements Runnable {
    private PipedWriter pipedWriter = new PipedWriter();
    private Random random = new Random(47);

    public PipedWriter getPipedWriter() {
        return pipedWriter;
    }

    @Override
    public void run() {
        try {
            for (char i = 'A'; i <= 'z'; i++) {
                pipedWriter.write(i);
                TimeUnit.MILLISECONDS.sleep(random.nextInt(50));
            }
        } catch (IOException ioe) {
            System.out.println("WriteJob IOException: " + ioe.getMessage());
        } catch (InterruptedException ie) {
            System.out.println("WriteJob interrupted");
        } catch (Exception e) {
            System.out.println("WriteJob exception:" + e.getMessage());
        }
        System.out.println("WriteJob finish");
    }
}
```

通过读出和写入管道实现线程通讯
```java
public class ReadJob implements Runnable {
    private PipedReader pipedReader;

    public ReadJob(WriteJob writeJob) throws IOException {
        this.pipedReader = new PipedReader(writeJob.getPipedWriter());
    }

    @Override
    public void run() {
        try {
            while (true) {
                //pipedReader若读不到数据，则阻塞，直到有数据，自定唤醒
                System.out.println("ReadJob Read:" + (char) pipedReader.read() + ", ");
            }
        } catch (IOException ioe) {
            System.out.println("ReadJob IOException: " + ioe.getMessage());
        } catch (Exception e) {
            System.out.println("ReadJob exception:" + e.getMessage());
        }
        System.out.println("ReadJob finish");
    }
}
```

```java
public class Test {
    public static void main(String[] args) throws  Exception{
        WriteJob writeJob = new WriteJob();
        ReadJob readJob = new ReadJob(writeJob);
        ExecutorService executorService = Executors.newCachedThreadPool();
        executorService.execute(writeJob);
        executorService.execute(readJob);
    }
}
```

完成字符‘A’到'z'输出

输出
```
ReadJob Read:A,
。。。
ReadJob Read:n,
WriteJob finish
ReadJob Read:o,
\ReadJob Read:y,
ReadJob Read:z
```

#### 结论
WriteJob任务将字符‘A’到'z'输出，通过写入管道PipedWriter来实现，

ReadJob任务读取WriteJob任务写入的字符，通过读取PipedReader来实现

```
两个线程通过读取管道PipeReader连接到写入管道PipedWriter来不同线程的数据通讯，
PipedWriter完成数据写入，而PipedReader完成数据读取，当PipeReader没有数据时，本线程阻塞，若有数据，则唤醒并读取。

PipedReader可以被中断，而system.in.read()不能被中断，
```

PipedReader和PipedWriter用于字符操作，而PipedInputStream和PipedOutputStram用于字节操作


## 死锁

线程间相互等待的连续循环，没有哪个线程能继续，这称为死锁。

A线程等待B线程释放锁，而B线程等待线程C释放锁，可是C线程又等待A线程是否锁，这种循环导致每一个线程都处于阻塞状态

这种死锁，可能看起来程序运行良好，但是任务就不执行，不处理数据，这种缺陷在生产环境很难察觉和追踪。

**因此在并发编程时，仔细的程序设计防止死锁发生，这很重要**


### 死锁如何形成

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

### 死锁如何防止

我们先总结下死锁形成4个要输

1. 互斥条件
2. 一个线程操作两个以上共享资源
3. 资源不能被线程抢占
4. 必须由循环等待
