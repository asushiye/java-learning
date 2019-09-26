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
