# Java Calender

## chapter-06-03



		概念
		实例

## 概念

日历主要分为公历（格里高利日历或阳历），和农历（阴历）
每个国家，在不同时期使用日历，也不一样，目前多数包括中国都是格里高利日历

java.util.Calender 表示抽象类。不能进行实例化的，原因是世界上有很多不同日历。

一般我们公历使用 **GregorianCalendar** 实现类

## 实例


```
    public static void printCalenderInfo(){
        Calendar calendar = new GregorianCalendar();
        System.out.println("Year= "+calendar.get(Calendar.YEAR));
        System.out.println("month= "+calendar.get(Calendar.MONTH));
        System.out.println("dayOfMonth= "+calendar.get(Calendar.DAY_OF_MONTH));
        System.out.println("dayOfWeek= "+calendar.get(Calendar.DAY_OF_WEEK));
        System.out.println("weekOfYear= "+calendar.get(Calendar.WEEK_OF_YEAR));
        System.out.println("weekOfMonth= "+calendar.get(Calendar.WEEK_OF_MONTH));

        System.out.println("hour= "+calendar.get(Calendar.HOUR));
        System.out.println("hourOfDay= "+calendar.get(Calendar.HOUR_OF_DAY));
        System.out.println("minute= "+calendar.get(Calendar.MINUTE));
        System.out.println("second= "+calendar.get(Calendar.SECOND));
        System.out.println("millisecond= "+calendar.get(Calendar.MILLISECOND));
    }
```

运行时，时间为2018-06-19 14:8:30.416

输出结果
```
Year= 2018
month= 5
dayOfMonth= 19
dayOfWeek= 3
weekOfYear= 25
weekOfMonth= 4
hour= 2
hourOfDay= 14
minute= 8
second= 30
millisecond= 416
```

从结果我们可以看出公历的月份，索引值从0开始，即为JANUARY。这里6月份值为5。

公历的月份的周，索引值从1(SUNDAY)开始到7(SATURDAY), 这里周二值为3.

下面我可以根据不同的field时间段，来设置set，增加add方法来设置日历。

```
    public static void setCalender(){
        Calendar calendar = new GregorianCalendar();
        //set date to last day of 2009
        calendar.set(Calendar.YEAR, 2009);
        calendar.set(Calendar.MONTH, 11); // 11 = december
        calendar.set(Calendar.DAY_OF_MONTH, 31); // new years eve
        //add one day
        calendar.add(Calendar.DAY_OF_MONTH, 1);
        //date is now jan. 1st 2010
        System.out.println("Year= "+calendar.get(Calendar.YEAR)); // now 2010
        System.out.println("month= "+calendar.get(Calendar.MONTH));  // now 0 (Jan = 0)
        System.out.println("dayOfMonth= "+calendar.get(Calendar.DAY_OF_MONTH)); // now 1
    }
```

输出结果
```
Year= 2010
month= 0    (Jan = 0)
dayOfMonth= 1
```





