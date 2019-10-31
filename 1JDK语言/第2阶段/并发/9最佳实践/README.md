# 9最佳实践

		分发工作
			编写代码
			程序输出结果
			结论
		性能调优
		  互斥比较 -  java编程思想 781页
			免锁容器 -  java编程思想 788页

## 分发工作

以组装汽车为例，
```
按生产计划，创建需要组装的汽车到未组装汽车队列中
从未组装汽车队列取出汽车，将汽车放在转配工作台上，
从机器池中获取不同的机器人进行不同组装工作，比如安装发动机引擎，动力驱动系统，轮胎等等。
将组装好的汽车放到已组装队列中
从组装队列表中取出汽车来打印信息信息
```

### 编写代码
定义汽车
```java
package concurrent.vip;

public class Car {
    private final int id;
    private boolean engine = false, driveTrain = false, wheels = false;
    public Car(int id) {
        this.id = id;
    }
    public synchronized int getId() {
        return id;
    }
    public synchronized void addEngine(String robot) {
        this.engine = true;
        System.out.println(robot+" "+ this);
    }
    public synchronized void addDriveTrain(String robot) {
        this.driveTrain = true;
        System.out.println(robot+" "+ this);
    }
    public synchronized void addWheels(String robot) {
        this.wheels = true;
        System.out.println(robot+" "+ this);
    }
    @Override
    public synchronized String toString() {
        return "Car{" +
                "id=" + id +
                ", engine=" + engine +
                ", driveTrain=" + driveTrain +
                ", wheels=" + wheels +
                '}';
    }
}
```

定义为组装汽车队列
```java
public class CarQueue extends LinkedBlockingQueue<Car> {}

public class CarQueueJob implements  Runnable {
    private CarQueue carQueue;
    private int counter= 0;
    public CarQueueJob(CarQueue carQueue) {
        this.carQueue = carQueue;
    }

    @Override
    public void run() {
        try{
            while(!Thread.interrupted()){
                Car car = new Car(counter++);
                System.out.println("CarQueueJob "+car);
                carQueue.put(car);
                TimeUnit.MILLISECONDS.sleep(500);
            }
        }catch(InterruptedException e){
            System.out.println("CarQueueJob interrupted");
        }
        System.out.println("CarQueueJob finish");
    }
}
```

定义机器人组装类
```java
public abstract class AbstractRobot implements Runnable {
    private RobotPool pool;
    private boolean power= false;
    protected Assembler assembler;

    public AbstractRobot(RobotPool pool) {
        this.pool = pool;
    }

    abstract void perform(); //执行装配工作

    public synchronized void powerOn(Assembler assembler){  //开启工作，并唤醒线程
        power = true;
        this.assembler = assembler;
        notifyAll();
    }

    public synchronized void powerOff() throws InterruptedException { //关闭工作,并挂起线程
        power = false;
        assembler = null;
        pool.add(this);
        while (!power){
            wait();
        }
    }

    @Override
    public void run() {   //每个机器人，有对应线程在执行
        try{
            powerOff();
            while(!Thread.interrupted()){
                perform();
                assembler.getBarrier().await();
                powerOff();
            }
        }catch(InterruptedException e){
            System.out.println(this+" interrupted");
        }catch (BrokenBarrierException bbe){
            System.out.println(this+ " BrokenBarrierException "+bbe.getMessage());
        }
        System.out.println(this+" finish");
    }

    @Override
    public String toString() {
        return this.getClass().getSimpleName();
    }
}

```
机器人创建时，默认放入机器池中，并且默认关机中，并挂起本线程

定义安装发动机机器人
```java
public class EngineRobot extends AbstractRobot {
    public EngineRobot(RobotPool pool) {
        super(pool);
    }
    @Override
    void perform() {  //完成发动机引擎安装
        assembler.getCar().addEngine(toString());
    }
}
```

定义安装动力驱动系统机器人
```java
public class DriveTrainRobot extends AbstractRobot {
    public DriveTrainRobot(RobotPool pool) {
        super(pool);
    }
    @Override
    void perform() { //完成动力驱动系统安装
        assembler.getCar().addDriveTrain(toString());
    }
}

```

定义安装轮胎机器人
```java
public class WheelRobot extends AbstractRobot {
    public WheelRobot(RobotPool pool) {
        super(pool);
    }
    @Override
    void perform() { //完成轮胎安装
        assembler.getCar().addWheels(toString());
    }
}
```

定义机器人池，用于获取机器人并从池中删除，同时开启机器人，等机器人完成工作后，放回池中
```java
public class RobotPool {
    private Set<AbstractRobot> pool = new HashSet<AbstractRobot>();
    public synchronized void add(AbstractRobot robot){
        pool.add(robot);
    }

    public synchronized void hive(Class<? extends AbstractRobot> robotType, Assembler a)
            throws InterruptedException {
        for (AbstractRobot robot: pool){
            if (robot.getClass().equals(robotType)){
                pool.remove(robot);
                robot.powerOn(a);
                return ;
            }
        };
        wait();
        hive(robotType, a);
    }
}
```

定义装配台
```java
public class Assembler implements Runnable{
    private CarQueue chassisQueue, finishingQueue;
    private RobotPool pool;
    private Car car;
    private CyclicBarrier barrier = new CyclicBarrier(4);

    public Assembler(CarQueue chassisQueue, CarQueue finishingQueue, RobotPool pool) {
        this.chassisQueue = chassisQueue;
        this.finishingQueue = finishingQueue;
        this.pool = pool;
    }

    public synchronized Car getCar() {
        return car;
    }

    public CyclicBarrier getBarrier() {
        return barrier;
    }

    @Override
    public void run() {
        try{
            while(!Thread.interrupted()){
                car = chassisQueue.take();
                pool.hive(EngineRobot.class, this);
                pool.hive(DriveTrainRobot.class, this);
                pool.hive(WheelRobot.class, this);
                barrier.await();
                finishingQueue.put(car);
                System.out.println("---------------------------");
            }
        }catch(InterruptedException ie){
            System.out.println("Assembler Interrupted");
        }catch (BrokenBarrierException bbe){
            System.out.println("Assembler BrokenBarrierException");
        }
        System.out.println("Assembler finish");
    }
}
```

使用CyclicBarrier控制一台汽车必须完成所有装配工作后，才能转配另外一台机器

这里CyclicBarrier预定四个挂起，当完成了四个await(),就能继续往下执行，并初始化执行下次

定义打印任务，用于将已组装汽车打印出来
```java
public class ReporterQueueJob implements  Runnable {
    private CarQueue carQueue;
    public ReporterQueueJob(CarQueue carQueue) {
        this.carQueue = carQueue;
    }

    @Override
    public void run() {
        try{
            while(!Thread.interrupted()){
                System.out.println("ReporterQueueJob "+carQueue.take());
            }
        }catch(InterruptedException e){
            System.out.println("CarQueueJob interrupted");
        }
        System.out.println("CarQueueJob finish");
    }
}
```

定义汽车构建器，完成所有未组装汽车的组装工作
```java
public class CarBuilder {
    public static void main(String[] args) throws InterruptedException {
        ExecutorService executorService = Executors.newCachedThreadPool();
        CarQueue chassisQueue = new CarQueue();
        CarQueue finishQueue = new CarQueue();
        RobotPool pool = new RobotPool();
        executorService.execute(new EngineRobot(pool));
        executorService.execute(new DriveTrainRobot(pool));
        executorService.execute(new WheelRobot(pool));
        executorService.execute(new Assembler(chassisQueue, finishQueue, pool));
        executorService.execute(new ReporterQueueJob(finishQueue));
        executorService.execute(new CarQueueJob(chassisQueue));
        TimeUnit.SECONDS.sleep(1);
        executorService.shutdownNow();
    }
}
```

### 程序输出结果
输出
```
CarQueueJob Car{id=0, engine=false, driveTrain=false, wheels=false}
DriveTrainRobot Car{id=0, engine=false, driveTrain=true, wheels=false}
WheelRobot Car{id=0, engine=false, driveTrain=true, wheels=true}
EngineRobot Car{id=0, engine=true, driveTrain=true, wheels=true}
---------------------------
ReporterQueueJob Car{id=0, engine=true, driveTrain=true, wheels=true}
CarQueueJob Car{id=1, engine=false, driveTrain=false, wheels=false}
EngineRobot Car{id=1, engine=true, driveTrain=false, wheels=false}
WheelRobot Car{id=1, engine=true, driveTrain=false, wheels=true}
DriveTrainRobot Car{id=1, engine=true, driveTrain=true, wheels=true}
---------------------------
ReporterQueueJob Car{id=1, engine=true, driveTrain=true, wheels=true}
Assembler Interrupted
Assembler finish
EngineRobot interrupted
EngineRobot finish
CarQueueJob interrupted
CarQueueJob finish
WheelRobot interrupted
WheelRobot finish
CarQueueJob interrupted
CarQueueJob finish
DriveTrainRobot interrupted
DriveTrainRobot finish
```


### 结论


1. 定义汽车类时，需要同步控制sychronized
```
由于汽车是共享资源，在转配，发动机安装机器人，动力驱动系统机器人，轮胎机器人，及打印线程中使用
汽车类的方法需要定义 sychronized，避免资源竞争问题
```

2. 转配时，需要CyclicBarrier来控制3个组装工作都完成,才能将汽车移到已组装汽车队列中

3. 转配汽车和打印汽车，使用LinkedBlockingQueue阻塞队列，并发执行汽车创建，转配和打印


## 性能调优

对应并发编程，java.util.concurrent中存在大量提供性能的类，

当我们在使用concurrent类库时就会发现很难，辨认哪些类适合常规应用，哪些类适合提供性能。

### 互斥比较 -  java编程思想 781页

实现互斥技术有

1. synchronized关键字，2. 实现Lock接口的类， 3. Atomic原子类

不同计算机，不同的时刻表现出来性能也是不太一样的。

推荐使用方式如下：
1. synchronized关键字，更好代码可读性，除了对整个方法互斥以外，也可以对代码块进行互斥
2. 实现Lock接口的类， 比如ReentrantLock，当并发量超级多时，相比synchronized，lock要更快，要稳定
3.  Atomic原子类, 适用共享资源相对简单

### 免锁容器 -  java编程思想 788页

早期的容器使用synchronzied技术使对象容器（集合）支持多线程并发读取和写入操作，

但是由于使用同步互斥技术，导致不可接受的开销，因此现在新增容器，通过免锁技术来消除加锁，提供并发操作

免锁技术：
```
对容器写入操作和读取操作可同时进行，实现方法是写入时保存一个数据结构中某个或整个部分的副本，

然后在副本上写入，当写入完成时，再将副本合并到原来的容器上，这时在读取就能看到最新数据。
```

java提供哪些免锁容器？

CopyOnWriteArrayList, CopyOnWriteArraySet, ConcurrentHashMap, ConcurrentLinkedQueue

### 乐观锁  788
