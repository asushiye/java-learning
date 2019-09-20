package date;

import java.util.Date;

/**
 * @author : zhenyun.su
 * @comment :
 * @since : 2019/9/20
 */

public class DateUtils7Test {
    public static void main(String[] args) throws Exception {
        System.out.println(DateUtils7.getDate());
        System.out.println(DateUtils7.getDate(System.currentTimeMillis()));
        System.out.println(DateUtils7.getDate(Long.valueOf(System.currentTimeMillis())));
        System.out.println(DateUtils7.getDate("2019-01-01 13:00:3","yyyy-MM-dd HH:mm:ss"));

        Date date = DateUtils7.getDate("2019-01-01 13:00:3","yyyy-MM-dd HH:mm:ss");
        Long timestamp = DateUtils7.getTimestamp(date);

        System.out.println(timestamp);
        System.out.println(DateUtils7.getTimestamp(date));
        System.out.println(DateUtils7.getTimestampByDay(timestamp, 1));
        System.out.println(DateUtils7.getTimestampByHour(timestamp, 1));
        System.out.println(DateUtils7.getTimestampByMinute(timestamp, 1));
        System.out.println(DateUtils7.getTimestampBySecond(timestamp, 1));
        System.out.println(DateUtils7.getTimestampBySecond(timestamp, -1));

        System.out.println(DateUtils7.getDateString("yyyy-MM-dd HH:mm:ss"));
        System.out.println(DateUtils7.getDateString(date,"yyyy-MM-dd HH:mm:ss"));
        System.out.println(DateUtils7.getDateStringByMillisecond(timestamp,"yyyy-MM-dd HH:mm:ss"));
    }
}
