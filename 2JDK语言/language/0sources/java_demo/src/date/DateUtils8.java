package date;

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

public final class DateUtils8 {
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
}
