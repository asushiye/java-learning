# TimeUnit 

TimeUnit是java.util.concurrent包下面的一个类，TimeUnit提供了可读性更好的线程暂停操作，通常用来替换Thread.sleep()

可读性差

// sleeping for 4 minutes
```
Thread.sleep（2400000）;
Thread.sleep(4*60*1000);

TimeUnit.MINUTES.sleep(4);

```

TimeUnit 表示工具类

* DAYS 
* HOURS
* MINUTES
* SECONDS
* MILLISECONDS
* MICROSECONDS
* NANOSECONDS

时间转化

秒转换成毫秒，你可以使用下面代码：
TimeUnit.SECONDS.toMillis(44)

提供很多转化函数

//关于秒的常用方法 

* TimeUnit.SECONDS.toMillis(1) 1秒转换为毫秒数 
* TimeUnit.SECONDS.toMinutes(60) 60秒转换为分钟数 
* TimeUnit.SECONDS.sleep(5) 线程休眠5秒 
* TimeUnit.SECONDS.convert(1, TimeUnit.MINUTES) 1分钟转换为秒数 


实例

```
import java.util.concurrent.TimeUnit;

public class MyTimeUnit {

    private TimeUnit timeUnit;

    public static void main(String[] args){

        try
        {
            MyTimeUnit myTimeUnit = new MyTimeUnit();
            myTimeUnit.timeUnitInfo(Long.valueOf(1), TimeUnit.DAYS );

            TimeUnit.SECONDS.sleep(2);

            myTimeUnit.timeUnitInfo(Long.valueOf(1), TimeUnit.HOURS);

        }catch(InterruptedException e){
            System.out.println(e.getStackTrace());
        }
    }

    public  void timeUnitInfo(Long time, TimeUnit unit){
        timeUnit = unit;
        System.out.println(timeUnit.name());
        System.out.println(timeUnit.toDays(time));
        System.out.println(timeUnit.toHours(time));
        System.out.println(timeUnit.toMinutes(time));
        System.out.println(timeUnit.toSeconds(time));
        System.out.println(timeUnit.toMillis(time));
        System.out.println(timeUnit.toMicros(time));
        System.out.println(timeUnit.toNanos(time));

        System.out.println("48Hour is "+(timeUnit.convert(48, TimeUnit.HOURS))+timeUnit.name());
        System.out.println("-------------------");

    }
}


```
