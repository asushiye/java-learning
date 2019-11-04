# Java 8 Date Time API

		日期类
			Instant - 瞬间时间值
			Duration  - 两个瞬间时间的差值
			LocalDateTime, LocalDate,LocalTime - 不带时区的本地日期时间类
			ZonedDateTime - 时区日期时间
			ZonedId - 时区类
			DateTimeFormatter - 日期时间格式化器
		最佳实践

## 日期类

在java 8 提供全新的date time api，这些api存放在java.time包中，属于java8标准包

主要变化是时间现在由1970-01-01日以来的不单用单个毫秒数表示，还可以用**纳秒**表示

**java.time** 包也包含一套子包提供不能工具集

例如
1. **java.time.chrono** 为不同地区提供日历的类
2. **java.time.format** 包拥有解析和格式日期的类

相关日期类有：
|类 | 描述|
|-|-|
|Instant | 从1970-01-01(GMT)开始到未来的时间线上的某点的瞬间时间|
|Duration | 表示持续时间，例如两个瞬间之间的时间|
|LocalDate,LocalDateTime,LocalTime| 没有时区信息的日期或时间 - 例如生日，官方假期等|
|ZonedDateTime | 包含时区的日期时间|
|DateTimeFormatter | 日期时间对象转换字符串，为 ZonedDateTime or a LocalDateTime|

### Instant - 瞬间时间值

The Instant class 表示一个时间线上的某点的瞬间时间数，支持秒和纳秒

从Jan. 1st 1970 - 00:00 - Greenwhich mean time (GMT).时间来统计

从Instant获取以秒为单位的数值，也就是10位的时间戳

若当前的13位毫秒的时间戳 1568959153951,通过`java.Util.Date.getTime或System.currentTimeMillis()`获取
```java
  Instant instant = Instant.now();
  System.out.println(instant.getEpochSecond()); //1568959153  10位秒时间戳
  System.out.println(instant.getNano());        //951000000        纳秒位单数值
  System.out.println(instant.toString());       //2019-09-20T05:57:55.592Z
	System.out.println(DateTimeFormatter.ISO_INSTANT.format(instant));   //2019-09-20T05:57:55.592Z
  System.out.println(instant.toEpochMilli());   //1568959153951 获取毫秒时间抽
```
利用instant函数，很容易实现对时间戳操作
* plusSeconds()
* plusMillis()
* plusNanos()
* minusSeconds()
* minusMillis()
* minusNanos()
```java
Instant now     = Instant.now();
Instant later   = now.plusSeconds(3);   //新增3秒
Instant earlier = now.minusSeconds(3);  //减少3秒
```

### Duration  - 两个瞬间时间的差值

一个Duration对象代表两个Instant对象之间的一段时间值

```java
	Instant instant = Instant.now();
  Instant instant1 = instant.plusSeconds(1*24*60*60).plusNanos(23*1000000);
  Duration duration = Duration.between(instant, instant1);
  System.out.println(duration.getSeconds());   //86400  获取秒值
  System.out.println(duration.getNano());      //23000000 获取纳秒值
  System.out.println(duration.toDays());			 //1 long       转换天数
  System.out.println(duration.toHours());			 // 24 long     转换为小时数
  System.out.println(duration.toMinutes());    //1440 long    转换为分钟
  System.out.println(duration.toMillis());     //86400023 long  转换为毫秒
```

利用Duration函数，很容易实现对时间戳操作
```
		plusNanos()
		plusMillis()
		plusSeconds()
		plusMinutes()
		plusHours()
		plusDays()
		minusNanos()
		minusMillis()
		minusSeconds()
		minusMinutes()
		minusHours()
		minusDays()
```

```java
Duration added      = duration.plusDays(3);  //加3天
Duration subtracted = duration.minusDays(3); //减少3天
```

### LocalDateTime, LocalDate,LocalTime - 不带时区的本地日期时间类

不带时区的本地日期时间类

```java
  LocalDateTime localDateTime3 = LocalDateTime.now();
  LocalDateTime localDateTime2 = LocalDateTime.ofInstant(Instant.now(),zoneId);
  LocalDateTime localDateTime1 = LocalDateTime.of(2019, 01, 01, 13,0,3,23000000);

  System.out.println(localDateTime1.toString()); //2019-01-01T13:00:03.023
	System.out.println(localDateTime1.format(DateTimeFormatter.ISO_LOCAL_DATE_TIME)); //2019-01-01T13:00:03.023

  System.out.println(localDateTime1.getYear());  //2019
  System.out.println(localDateTime1.getMonth());  // JANUARY
  System.out.println(localDateTime1.getMonth().getValue());  //1
  System.out.println(localDateTime1.getDayOfMonth()); //1
  System.out.println(localDateTime1.getHour());   //13
  System.out.println(localDateTime1.getMinute());  //0
  System.out.println(localDateTime1.getSecond());  //3
  System.out.println(localDateTime1.getNano());    //23000000
  System.out.println(localDateTime1.getDayOfYear()); //1
  System.out.println(localDateTime1.getDayOfWeek()); //TUESDAY
	System.out.println(localDateTime1.getDayOfWeek().getValue()); //2

  System.out.println(localDateTime1.toLocalDate().toString()); //2019-01-01
  System.out.println(localDateTime1.toLocalTime().toString()); //13:00:3.023

  ZonedDateTime zonedDateTime = localDateTime1.atZone(zoneId); //按时区转换为 ZonedDateTime 1546318803023
  System.out.println(zonedDateTime.toInstant().toEpochMilli()); //1546318803023

  localDateTime1 = localDateTime1.plusHours(12); //2019-01-02T01:00:03.023
  localDateTime1 = localDateTime1.minusHours(12); //2019-01-01T13:00:03.023

```
利用Duration函数，很容易实现对时间戳操作
```
		plusDays()
		plusWeeks()
		plusMonths()
		plusYears()
		minusDays()
		minusWeeks()
		minusMonths()
		minusYears()
```

### ZonedDateTime - 时区日期时间

带时区的日期时间类

```java
  ZonedDateTime zonedDateTime1 = ZonedDateTime.of(2019, 01, 01, 13, 0, 3, 23000000, zoneId);
  ZonedDateTime zonedDateTime2 = ZonedDateTime.now(zoneId);
  ZonedDateTime zonedDateTime3 = ZonedDateTime.ofInstant(Instant.now(), zoneId);
  System.out.println(zonedDateTime1.toString()); // 2019-01-01T13:00:03.023+08:00[Asia/Shanghai]
  System.out.println(zonedDateTime2.toString()); //2019-09-20T15:56:01.588+08:00[Asia/Shanghai]
  System.out.println(zonedDateTime3.toString()); //2019-09-20T15:56:01.588+08:00[Asia/Shanghai]

  System.out.println(zonedDateTime1.format(DateTimeFormatter.ISO_DATE_TIME)); //2019-01-01T13:00:03.023+08:00[Asia/Shanghai]
  System.out.println(zonedDateTime1.format(DateTimeFormatter.ISO_ZONED_DATE_TIME)); //2019-01-01T13:00:03.023+08:00[Asia/Shanghai]

  Instant instant2= zonedDateTime1.toInstant();
  System.out.println(zonedDateTime1.toLocalDateTime().toString()); //2019-01-01T13:00:03.023
  System.out.println(instant2.toEpochMilli());  //1546318803023
  ZoneId zoneId1 = zonedDateTime1.getZone();
  System.out.println(zonedDateTime1.getYear());  //2019
```

### Accessing Date and Time of a ZonedDateTime
You can access the date and time fields of a ZonedDateTime instance using one of the following methods:
```
	getYear()
	getMonth()
	getDayOfMonth()
	getDayOfWeek()
	getDayOfYear()
	getHour()
	getMinute()
	getSecond()
	getNano()
```

Here is an example accessing the year of a ZonedDateTime:

`int year = ZonedDateTime.now().getYear();`
Some of these methods return an enum, and others return an int. From the enums you can return an int representation of their value using their getValue() methods. For instance:

int month = ZonedDateTime.now()
              .getMonth().getValue();


### Date and Time Calculations
The ZonedDateTime class contains a set of methods used for date time calculations. Some of these methods are:

```
	plusYears()
	plusMonths()
	plusDays()
	plusHours()
	plusMinutes
	plusSeconds
	plusNanos()
	minusYears()
	minusMonths()
	minusDays()
	minusHours()
	minusMinutes
	minusSeconds
	minusNanos()
```


### ZonedId - 时区类

java使用ZonedId来，代表时区，以 **格林威治(UTC)** 为基准，进行偏移来代表其他时区，

比如亚洲/上海的时区，**UTC+8**， 可以正向偏移，也可以负向偏移 UTC-8，最大值18，最小值-18

也可以使用固定字符串来代表时区，比如 **Asia/Shanghai**

实例如下
```java
        ZoneId zoneId = ZoneId.systemDefault();
        ZoneId zoneId1 = ZoneId.of("Asia/Shanghai");
        ZoneId zoneId2 = ZoneId.of("UTC+9");
        System.out.println(zoneId.toString());
        System.out.println(zoneId1.toString());
        System.out.println(zoneId2.toString());
```
输出结果
```
Asia/Shanghai
Asia/Shanghai
UTC+09:00
```

应用于ZonedDateTime时区日期时间类，``
```java
	//创建ZonedDateTime，需要使用zonedId
  ZonedDateTime zonedDateTime1 = ZonedDateTime.of(2019, 01, 01, 13, 0, 3, 23000000, zoneId);
  ZonedDateTime zonedDateTime2 = ZonedDateTime.now(zoneId);
  ZonedDateTime zonedDateTime3 = ZonedDateTime.ofInstant(Instant.now(), zoneId);
	ZoneId zoneId1 = zonedDateTime1.getZone();
```

### DateTimeFormatter - 日期时间格式化器

* 默认格式化 - DateTimeFormatter实例
* 自定义格式化 - ofPattern

用于解析和格式ZonedDateTime, LocalDateTime, LocalDate, LocalTime

#### 默认格式化 - DateTimeFormatter实例
```java
  DateTimeFormatter.ISO_INSTANT.format(instant); //Instant专用 2019-09-20T08:23:39.992Z
  zonedDateTime1.format(DateTimeFormatter.ISO_ZONED_DATE_TIME)); //ZonedDateTime专用 2019-01-01T13:00:03.023+08:00[Asia/Shanghai]

  localDateTime1.format(DateTimeFormatter.BASIC_ISO_DATE)); //20190101
  localDateTime1.format(DateTimeFormatter.ISO_DATE_TIME));  //2019-01-01T13:00:03.023
  localDateTime1.format(DateTimeFormatter.ISO_LOCAL_DATE_TIME)); // 2019-01-01T13:00:03.023

  zonedDateTime1.format(DateTimeFormatter.BASIC_ISO_DATE)); //20190101+0800
  zonedDateTime1.format(DateTimeFormatter.ISO_DATE_TIME));  // 2019-01-01T13:00:03.023+08:00[Asia/Shanghai]
  zonedDateTime1.format(DateTimeFormatter.ISO_LOCAL_DATE_TIME)); //2019-01-01T13:00:03.023
```
记住这五个就够了 ISO_INSTANT， ISO_ZONED_DATE_TIME , BASIC_ISO_DATE, ISO_DATE_TIME, ISO_LOCAL_DATE_TIME

#### 自定义格式化 - ofPattern

```java
  System.out.println("------------------ofPattern---------------------");
  DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:s.SSS");
  System.out.println(localDateTime1.format(dateFormatter)); //2019-01-01 13:00:3.023
  System.out.println(zonedDateTime1.format(dateFormatter)); //2019-01-01 13:00:3.023

  LocalDateTime localDateTime = LocalDateTime.parse("2019-01-01 14:01:25.100", dateFormatter);
  System.out.println(localDateTime.toString()); //2019-01-01T14:01:25.100

  ZonedDateTime zonedDateTime4 = ZonedDateTime.parse("2019-01-01 14:01:25.100", dateFormatter);
  System.out.println(zonedDateTime4.toString()); //报错java.time.format.DateTimeParseException: Text '2019-01-01 14:01:25.100' could not be parsed: Unable to obtain Z

```

## 最佳实践

```java
package date;

import java.time.*;
import java.time.format.DateTimeFormatter;

/**
 * @author : zhenyun.su
 * @comment : 日期类java.time.LocalDateTime
 * @since : 2019/9/26
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

public final class DateUtils8 {
    /**
     * @comment : 获取当前日期
     */
    public static LocalDateTime getDate() {
        return LocalDateTime.now();
    }

    /**
     * @comment : 时间戳转换日期
     */
    public static LocalDateTime getDate(Long timestamp) {
        Instant instant =  Instant.ofEpochMilli(timestamp);
        return LocalDateTime.ofInstant(instant, ZoneId.systemDefault());
    }

    /**
     * @comment : 字符串转换日期
     */
    public static LocalDateTime getDate(final String sDate, final String format) {
        return LocalDateTime.parse(sDate, DateTimeFormatter.ofPattern(format));
    }

    /**
     * @comment : 按格式获取当前日期字符串
     */
    public static String getDateString(String format) {
        return getDateString(getDate(), format);
    }

    /**
     * @comment : 按格式和日期，获取日期字符串
     */
    public static String getDateString(LocalDateTime date, String format) {
        return date.format( DateTimeFormatter.ofPattern(format));
    }

    /**
     * @comment :  10位秒-时间戳转换日期字符串
     */
    public static String getDateStringBySecond(Long timestamp, String format) {
        return getDateStringByMillisecond(Long.valueOf(timestamp + "000"), format);
    }

    /**
     * @comment :  13位毫秒-时间戳转换日期字符串
     */
    public static String getDateStringByMillisecond(Long timestamp, String format) {
        return getDate(timestamp).format(DateTimeFormatter.ofPattern(format));
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
    public static Long getTimestamp(LocalDateTime date) {
        Instant instant = date.atZone(ZoneId.systemDefault()).toInstant();
        return instant.toEpochMilli();
    }

    /**
     * @comment : 获取以天为单位的时间戳，精确到毫秒，-1 表示前1天的时间抽，1 表示后1天的时间戳
     */
    public static Long getTimestampByDay(Long timestamp,int day) {
        return timestamp + day * 24 * 60 * 60 * 1000;
    }

    /**
     * @comment : 获取以小时为单位的时间戳，精确到毫秒，-1 表示前1小时的时间抽，1 表示后1小时的时间戳
     */
    public static Long getTimestampByHour(Long timestamp, int hour) {
        return timestamp + hour * 60 * 60 * 1000;
    }

    /**
     * @comment : 获取以分钟为单位的时间戳，精确到毫秒，-1 表示前1分钟的时间抽，1 表示后1分钟的时间戳
     */
    public static Long getTimestampByMinute(Long timestamp, int minute) {
        return timestamp + minute * 60 * 1000;
    }

    /**
     * @comment : 获取以秒为单位的时间戳，精确到毫秒，-1 表示前1秒的时间抽，1 表示后1秒的时间戳
     */
    public static Long getTimestampBySecond(Long timestamp, int second) {
        return timestamp + second * 1000;
    }

    public static void main(String[] args) {
        //2019-01-01 12:10:2.235 为例 1546315802235
        System.out.println(getDate());
        System.out.println(getDate(1546315802235L));
        LocalDateTime localDateTime = getDate("2019-01-01 12:10:02.235", "yyyy-MM-dd HH:mm:ss.SSS");
        System.out.println(localDateTime);
        System.out.println(localDateTime.toLocalDate());
        System.out.println(localDateTime.toLocalTime());
        System.out.println(localDateTime.getYear());
        System.out.println(getDate("2019-01-01 12:10:02", "yyyy-MM-dd HH:mm:ss"));

        String today = getDateString("yyyy-MM-dd HH:mm:ss");
        String sdate = getDateString(localDateTime, "yyyy-MM-dd HH:mm:ss");
        System.out.println("today= "+today+"; sdate= "+sdate);
        System.out.println(getDateStringBySecond(1546315802L,"yyyy-MM-dd HH:mm:ss"));
        System.out.println(getDateStringByMillisecond(1546315802235L,"yyyy-MM-dd HH:mm:ss.SSS"));

        System.out.println(getTimestamp());
        Long timestamp = getTimestamp(localDateTime);
        System.out.println(timestamp);
        System.out.println(getTimestampByDay(timestamp, 2));
        System.out.println(getTimestampByDay(timestamp, -2));
        System.out.println(getTimestampByHour(timestamp, 2));
        System.out.println(getTimestampByHour(timestamp, -2));
        System.out.println(getTimestampByMinute(timestamp, 2));
        System.out.println(getTimestampByMinute(timestamp, -2));
        System.out.println(getTimestampBySecond(timestamp, 2));
        System.out.println(getTimestampBySecond(timestamp, -2));
    }
}
```

使用时，可删除main()方法
