# 3线程池执行器

		ScheduledThreadPoolExecutor
		Semaphore -- java编程思想 766页

## ScheduledThreadPoolExecutor

调度线程池执行器，用于按计划来创建线程执行任务。

```java

public class WaterJob implements Runnable {
    @Override
    public void run() {
        try{
            System.out.print(System.currentTimeMillis()+" Water Job ");
        }catch(Exception e){
            System.out.println("Interrupted");
        }
        System.out.println("finish");
    }
}
```


```java
public class LightJob implements Runnable {

    @Override
    public void run() {
        try{
            System.out.print(System.currentTimeMillis()+" light job ");
        }catch(Exception e){
            System.out.println("Interrupted");
        }
        System.out.println("finish");
    }
}
```

```java
public class Test {
    public static void main(String[] args) {
        scheduledThreadPoolExecutor(args);
    }
    public static void scheduledThreadPoolExecutor(String[] args) {
        ScheduledThreadPoolExecutor executor = new ScheduledThreadPoolExecutor(10);
        System.out.println(System.currentTimeMillis()+" Scheduled Start");
        executor.schedule(new WaterJob(), 5000, TimeUnit.MILLISECONDS);
        executor.scheduleAtFixedRate(new LightJob(), 1000, 3000,TimeUnit.MILLISECONDS);
//        executor.scheduleWithFixedDelay(new LightJob(), 1000, 3000,TimeUnit.MILLISECONDS);
        try{
            TimeUnit.SECONDS.sleep(10);
            executor.shutdown();
        }catch(Exception e){

        }
    }
}
```

输出如下
```
1572415551325 Scheduled Start
1572415552325 light job finish
1572415555340 light job finish
1572415556340 Water Job finish
1572415558340 light job finish
```


## Semaphore -- java编程思想 766页

用于创建对象池
略
## Exchanger
略

### 分发工作 -- java编程思想 777页
