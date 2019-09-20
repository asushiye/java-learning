# Java7 date and time api
		日期
			java7提供日期工具类
			最佳实践
		日历 - 操作日期
			实例1-获取不同段
			实例2-操作不同段

## 日期

### java7提供日期工具类

```
System.currentTimeMillis()   毫秒的时间戳
java.util.Date  一般用的日期类
java.sql.Date
java.sql.Timestamp
java.util.Calendar  用于操作日期的日历抽象类
java.util.GregorianCalendar  公历
java.util.TimeZone
java.text.SimpleDateFormat  日期格式工具
```

### 最佳实践

下面通过自定义类，来实现三大功能
 * 获取日期 - getDate()
 * 获取日期字符串 - getDateString()
 * 获取时间戳 - getTimestamp()

```java
package com.grains.study.system.common.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author : zhenyun.su
 * @comment : 日期类java.util.Date
 * @since : 2019/9/6
 * 主要提供三大类方法，
 *      一类，获取日期 - getDate()
 *      二类，获取日期字符串 - getDateString()
 *      三类，获取时间戳 - getTimestamp()
 * 日期格式format:
 * yyyy-MM-dd
 * HH:mm:ss
 * yyyy-MM-dd HH:mm:ss
 * yyyyMMdd
 * HHmmss
 * getDateString("yyyy-MM-dd")
 * getDateString(1436624630, "yyyy-MM-dd HH:mm:ss")  2015-07-11 22:23:50
 */

public final class DateUtils {
    /**
     * @comment : 获取当前日期
     */
    public static Date getDate() {
        return new Date();
    }

    /**
     * @comment : 时间戳转换日期
     */
    public static Date getDate(Long timestamp) {
        return new Date(timestamp);
    }

    /**
     * @comment : 时间戳转换日期
     */
    public static Date getDate(long timestamp) {
        return new Date(Long.valueOf(timestamp));
    }

    /**
     * @comment : 字符串转换日期
     */
    public static Date getDate(final String sdate, String format) throws ParseException {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(format);
        return simpleDateFormat.parse(sdate);
    }

    /**
     * @comment : 按格式获取当前日期字符串
     */
    public static String getDateString(String format) {
        return (new SimpleDateFormat(format)).format(new Date());
    }

    /**
     * @comment : 按格式和日期，获取日期字符串
     */
    public static String getDateString(Date date, String format) {
        return (new SimpleDateFormat(format)).format(date);
    }

    /**
     * @comment :  10位秒-时间戳转换日期字符串
     */
    public static String getDateStringBySecond(Long timestamp, String format) {
        return (new SimpleDateFormat(format)).format(getDate(Long.valueOf(timestamp + "000")));
    }

    /**
     * @comment :  13位毫秒-时间戳转换日期字符串
     */
    public static String getDateStringByMillisecond(Long timestamp, String format) {
        return (new SimpleDateFormat(format)).format(getDate(timestamp));
    }

    /**
     * @comment : 获取当前的时间戳，精确到毫秒，总共13位，前10位秒. 从1970.1.1 00:00:00 GMT 开始统计
     */
    public static Long getTimestamp() {
        return System.currentTimeMillis();
    }

    /**
     * @comment : 获取日期的时间戳，精确到毫秒,总共13位，前10位秒. 从1970.1.1 00:00:00 GMT 开始统计
     */
    public static Long getTimestamp(Date date) {
        return date.getTime();
    }

    /**
     * @comment : 获取以天为单位的时间戳，精确到毫秒，-1 表示前1天的时间抽，1 表示后1天的时间戳
     */
    public static Long getTimestampByDay(int day) {
        return DateUtils.getTimestamp() + day * 24 * 60 * 60 * 1000;
    }

    /**
     * @comment : 获取以小时为单位的时间戳，精确到毫秒，-1 表示前1小时的时间抽，1 表示后1小时的时间戳
     */
    public static Long getTimestampByHour(int hour) {
        return DateUtils.getTimestamp() + hour * 60 * 60 * 1000;
    }

    /**
     * @comment : 获取以分钟为单位的时间戳，精确到毫秒，-1 表示前1分钟的时间抽，1 表示后1分钟的时间戳
     */
    public static Long getTimestampByMinute(int minute) {
        return DateUtils.getTimestamp() + minute * 60 * 1000;
    }

    /**
     * @comment : 获取以秒为单位的时间戳，精确到毫秒，-1 表示前1秒的时间抽，1 表示后1秒的时间戳
     */
    public static Long getTimestampBySecond(int minute) {
        return DateUtils.getTimestamp() + minute * 60 * 1000;
    }
}
```

测试代码
```java
public class DateUtilsTest {
    public static void main(String[] args) throws Exception {
        System.out.println(DateUtils.getDate());
        System.out.println(DateUtils.getDate(System.currentTimeMillis()));
        System.out.println(DateUtils.getDate(Long.valueOf(System.currentTimeMillis())));
        System.out.println(DateUtils.getDate("2019-01-01 13:00:3","yyyy-MM-dd HH:mm:ss"));

        Date date = DateUtils.getDate("2019-01-01 13:00:3","yyyy-MM-dd HH:mm:ss");
        Long timestamp = DateUtils.getTimestamp(date);

        System.out.println(timestamp);
        System.out.println(DateUtils.getTimestamp(date));
        System.out.println(DateUtils.getTimestampByDay(timestamp, 1));
        System.out.println(DateUtils.getTimestampByHour(timestamp, 1));
        System.out.println(DateUtils.getTimestampByMinute(timestamp, 1));
        System.out.println(DateUtils.getTimestampBySecond(timestamp, 1));
        System.out.println(DateUtils.getTimestampBySecond(timestamp, -1));

        System.out.println(DateUtils.getDateString("yyyy-MM-dd HH:mm:ss"));
        System.out.println(DateUtils.getDateString(date,"yyyy-MM-dd HH:mm:ss"));
        System.out.println(DateUtils.getDateStringByMillisecond(timestamp,"yyyy-MM-dd HH:mm:ss"));

    }
}
```

## 日历 - 操作日期

我们可以使用Date来获取日期，但是需要调整日期，进行运算时，比如前一天，后一天等操作时，

需要使用java7提供Calendar实现类来操作

日历主要分为公历（格里高利日历或阳历），和农历（阴历）

每个国家，在不同时期使用日历，也不一样，目前多数包括中国都是格里高利日历

java.util.Calender 表示抽象类。不能进行实例化的，原因是世界上有很多不同日历。

一般我们公历使用 **GregorianCalendar** 实现类

Calendar为日期，提供很多field，比如YEAR, MONTH, DAY_OF_MONTH 等待，请看下面实例

### 实例1-获取不同段

```java
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

### 实例2-操作不同段

```java
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
