# TimeUnit 

TimeUnit��java.util.concurrent�������һ���࣬TimeUnit�ṩ�˿ɶ��Ը��õ��߳���ͣ������ͨ�������滻Thread.sleep()

�ɶ��Բ�

// sleeping for 4 minutes
```
Thread.sleep��2400000��;
Thread.sleep(4*60*1000);

TimeUnit.MINUTES.sleep(4);

```

TimeUnit ��ʾ������

* DAYS 
* HOURS
* MINUTES
* SECONDS
* MILLISECONDS
* MICROSECONDS
* NANOSECONDS

ʱ��ת��

��ת���ɺ��룬�����ʹ��������룺
TimeUnit.SECONDS.toMillis(44)

�ṩ�ܶ�ת������

//������ĳ��÷��� 
TimeUnit.SECONDS.toMillis(1) 1��ת��Ϊ������ 
TimeUnit.SECONDS.toMinutes(60) 60��ת��Ϊ������ 
TimeUnit.SECONDS.sleep(5) �߳�����5�� 
TimeUnit.SECONDS.convert(1, TimeUnit.MINUTES) 1����ת��Ϊ���� 


ʵ��

```
import java.util.concurrent.TimeUnit;

public class MyTimeUnit {

    private TimeUnit timeUnit;

    public static void main(String[] args){

        try
        {
            MyTimeUnit myTimeUnit = new MyTimeUnit();
            myTimeUnit.timeUnit = TimeUnit.DAYS;
            myTimeUnit.timeUnitInfo();

            TimeUnit.SECONDS.sleep(2);

            myTimeUnit.timeUnit = TimeUnit.HOURS;
            myTimeUnit.timeUnitInfo();

        }catch(InterruptedException e){
            System.out.println(e.getStackTrace());
        }
    }

    public  void timeUnitInfo(){
        System.out.println(timeUnit.name());
        System.out.println(timeUnit.toDays(1));
        System.out.println(timeUnit.toHours(1));
        System.out.println(timeUnit.toMinutes(1));
        System.out.println(timeUnit.toSeconds(1));
        System.out.println(timeUnit.toMillis(1));
        System.out.println(timeUnit.toMicros(1));
        System.out.println(timeUnit.toNanos(1));

        System.out.println("48Hour is "+(timeUnit.convert(48, TimeUnit.HOURS))+timeUnit.name());
        System.out.println("-------------------");

    }
}

```