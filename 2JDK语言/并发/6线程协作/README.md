# 6线程协作 java编程思想 735页

    概念
    线程挂起和线程解挂
      wait()与notifyAll()
      notify()与notifyAll()
      线程信号消失
    最佳实践
      生产者和消费者
      java编程思想， 743页

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

### 生产者和消费者
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
